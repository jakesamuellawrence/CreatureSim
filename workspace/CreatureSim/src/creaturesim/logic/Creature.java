package creaturesim.logic;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Represents a competitor competing for food.
 * 
 * Has a position, size, Color, and a neural net for making decisions.
 * Also has a first and last name, a track of how long it's been alive,
 * and a boolean stating whether or not it is alive
 * 
 * @author jakesamuellawrence
 */
public class Creature{
	
	String first_name;
	String last_name;
	
	double movespeed = 0.04;
	double turnspeed = 0.01;
	
	int survival_time = 0;
	
	double x;
	double y;
	
	double bearing = Math.random()*2*Math.PI - Math.PI;
	double radius = 1;
	
	Color color;
	
	boolean alive = true;
	
	Brain brain;
	
	boolean should_move_forwards;
	boolean should_move_backwards;
	boolean should_turn_left;
	boolean should_turn_right;
	
	/**
	 * Constructor for Creature.
	 * 
	 * Creates a completely random creature, with random colour, brain, and names
	 */
	public Creature(){
		this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		this.brain = new Brain();
		this.first_name = CompetitionManager.getRandomName();
		String suffix;
		double suffix_choice = Math.random();
		if(suffix_choice < 0.45){
			suffix = "son";
		}
		else if(suffix_choice < 0.9){
			suffix = "dottir";
		}
		else{
			suffix = "child";
		}
		this.last_name = "God" + suffix;
	}
	
	/**
	 * Moves the creature to a random unoccupied spot within the spawn area specified by CompetitionManager
	 */
	public void spawnInRandomLocation(){
		Rectangle spawn_area = CompetitionManager.spawn_area;
		x = Math.random()*spawn_area.width + spawn_area.x;
		y = Math.random()*spawn_area.height + spawn_area.y;
		while(isNearFood() || isNearCreature()){
			x = Math.random()*spawn_area.width + spawn_area.x;
			y = Math.random()*spawn_area.height + spawn_area.y;
		}
	}
	
	/**
	 * Makes the creature the default size, and sets their alive boolean to true
	 */
	public void revive(){
		radius = 1;
		alive = true;
	}
	
	/**
	 * Creates an exact copy of the creature in which this method is being run.
	 * 
	 * Generates a random creature, but then sets all their essential instance variables
	 * to be the same as the creature being cloned
	 * 
	 * @return the clone created.
	 */
	public Creature makeClone(){
		Creature clone = new Creature();
		clone.color = this.color;
		clone.brain = this.brain;
		clone.first_name = this.first_name;
		clone.last_name = this.last_name;
		return(clone);
	}
	
	/**
	 * Creates a child from the creature on which this method is being run
	 * 
	 * Creates a completely random creature, then sets it's colour to that of it's parent.
	 * Gives them a mutated version of their parent's brain, and a last name based on their
	 * parent's first name.
	 * 
	 * @return
	 */
	public Creature makeChild(){
		Creature child = new Creature();
		child.color = this.color;
		child.brain = this.brain;
		child.brain.mutate();
		String suffix;
		double suffix_choice = Math.random();
		if(suffix_choice < 0.4){
			suffix = "son";
		}
		else if(suffix_choice < 0.8){
			suffix = "dottir";
		}
		else{
			suffix = "child";
		}
		child.last_name = this.first_name + suffix;
		return(child);
	}
	
	/**
	 * checks whether the creature is close to any pellets of food
	 * 
	 * @return whether or not they are nearby
	 */
	boolean isNearFood(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(distanceTo(food.get(i)) < 2){
				return(true);
			}
		}
		return(false);
	}
	
	/**
	 * checks whether the creature is close to any other creatures in it's generation
	 * 
	 * @return whether or not they are nearby
	 */
	boolean isNearCreature(){
		Creature[] creatures = CompetitionManager.getCurrentGeneration().creatures;
		for(int i = 0; i < creatures.length; i++){
			if(creatures[i] != this && distanceTo(creatures[i]) < 2){
				return(true);
			}
		}
		return(false);
	}
	
	/**
	 * Ticks the logic of the creature. Calls other methods with further logic.
	 */
	public void tick(){
		runThroughNetwork();
		rotate();
		move();
		checkForEating();
		loseEnergy();
	}
	
	/**
	 * Gives values to the input nodes, then gets output from the output nodes, to see
	 * how the creature should move next turn.
	 */
	void runThroughNetwork(){
		boolean is_looking_at_food = false;
		if(bearing >= -Math.PI/2 && bearing <= Math.PI/2){
			is_looking_at_food = checkForFoodRightSide();
		}
		else{
			is_looking_at_food = checkForFoodLeftSide();
		}
		brain.loadValues(is_looking_at_food);
		should_move_forwards = brain.shouldMoveForwards();
		should_move_backwards = brain.shouldMoveBackwards();
		should_turn_left = brain.shouldTurnLeft();
		should_turn_right = brain.shouldTurnRight();
	}
	
	/**
	 * Rotates the creature based on the decisions it's made, 
	 * then normalises it's bearing to within the range -PI to PI
	 */
	void rotate(){
		if(should_turn_right){
			bearing += turnspeed;
		}
		if(should_turn_left){
			bearing -= turnspeed;
		}
		if(bearing > Math.PI){
			bearing = bearing - 2*Math.PI;
		}
		else if(bearing < -Math.PI){
			bearing = bearing + 2*Math.PI;
		}
	}
	
	/**
	 * Moves the creature in the direction of it's bearing, either
	 * forwards or backwards depending on the decisions it has made
	 */
	void move(){
		if(should_move_forwards){
			x += movespeed*Math.cos(bearing);
			y += movespeed*Math.sin(bearing);
		}
		if(should_move_backwards){
			x -= movespeed*Math.cos(bearing);
			y -= movespeed*Math.sin(bearing);
		}
	}
	
	/**
	 * Checks to see if this creature is on top of any food pellets. If they are, they eat the food pellet
	 */
	void checkForEating(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(distanceTo(food.get(i)) < radius + food.get(i).getRadius()){
				eat(food.get(i));
				food = CompetitionManager.food;
			}
		}
	}
	
	/**
	 * Decreases the radius of the creature, and recalculates it's movement speed.
	 * If the creature's radius is too small, it dies. If it's still alive, increment
	 * it's survival time
	 */
	public void loseEnergy(){
		radius -= CompetitionManager.energy_loss_rate;
		if(radius < 0.5){
			die();
		}
		else{
			survival_time++;
		}
	}
	
	/**
	 * Checks to see if any of the food pellets to the right of the creature are in the 
	 * creature's line of sight. This is required as the method for checking
	 * whether a creature is in the line of sight detects creatures to both the left
	 * and the right. If a food pellet is found, returns true, returns false otherwise
	 */
	boolean checkForFoodRightSide(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(food.get(i).getX() >= this.x){
				if(inLineWith(food.get(i))){
					return(true);
				}
			}
		}
		return(false);
	}
	
	/**
	 * Checks to see if any of the food pellets to the left of the creature are in the 
	 * creature's line of sight. This is required as the method for checking
	 * whether a creature is in the line of sight detects creatures to both the left
	 * and the right. If a food pellet is found, returns true, returns false otherwise
	 */
	boolean checkForFoodLeftSide(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(food.get(i).getX() <= this.x){
				if(inLineWith(food.get(i))){
					return(true);
				}
			}
		}
		return(false);
	}
	
	/**
	 * Eats a food pellet.
	 * 
	 * This creature gains the food pellet's radius, and the food pellet is removed.
	 * 
	 * @param food FoodPellet, the food pellet being eaten.
	 */
	void eat(FoodPellet food){
		radius += food.getRadius();
		CompetitionManager.removeFood(food);
	}
	
	/**
	 * Makes the creature no longer alive. This stops it's logic being ticked, stops it being displayed,
	 * and means it will be added to the list of dead creatures. Because creatures are added to this
	 * list as they die, it is automatically in sorted order.
	 */
	void die(){
		alive = false;
		CompetitionManager.kill(this);
	}
	
	/**
	 * returns the distance between a creature and a given food pellet.
	 * 
	 * @param target FoodPellet, the food pellet to which the distance is being found.
	 * @return the distance between this creature and the target food pellet
	 */
	double distanceTo(FoodPellet target){
		return(Math.hypot(target.getX() - x, target.getY() - y));
	}
	
	/**
	 * returns the distance between a creature and another creature
	 * 
	 * @param target Creature, the creature to which the distance is being found.
	 * @return the distance between this creature and the target creature
	 */
	double distanceTo(Creature target){
		return(Math.hypot(target.getX() - x, target.getY() - y));
	}
	
	/**
	 * returns whether or not the target food pellet lies along the line
	 * defined by the creature's centre and it's bearing.
	 * 
	 * @param target the food pellet being checked
	 * @return whether or not the food pellet lies on the line.
	 */
	boolean inLineWith(FoodPellet target){
		double m = Math.tan(bearing);
		double a = target.getX() - this.x;
		double b = target.getY() - this.y;
		double r = target.getRadius();
		double discriminant = Math.pow((-2*b*m - 2*a), 2) 
				              - 4*(Math.pow(m, 2)+1)*(Math.pow(b, 2)+Math.pow(a, 2)-Math.pow(r, 2));
		return(discriminant >= 0);
	}
	
	/**
	 * returns whether the creature is alive.
	 * 
	 * @return whether the creature is alive
	 */
	public boolean isAlive(){
		return(alive);
	}
	
	/**
	 * returns the x coordinate of the creature, in metres
	 * @return the x coordinate, in metres
	 */
	public double getX(){
		return(x);
	}
	
	/**
	 * returns the y coordinate of the creature, in metres
	 * 
	 * @return the y coordinate, in metres
	 */
	public double getY(){
		return(y);
	}
	
	/**
	 * returns the radius of the creature, in metres
	 * 
	 * @return the radius, in metres
	 */
	public double getRadius(){
		return(radius);
	}
	
	/**
	 * returns the current bearing of the creature, in radians.
	 * 
	 * @return the bearing, in radians
	 */
	public double getBearing(){
		return(bearing);
	}
	
	/**
	 * Returns the colour of the creature
	 * 
	 * @return the color
	 * @See java.awt.Color
	 */
	public Color getColor(){
		return(color);
	}
}
