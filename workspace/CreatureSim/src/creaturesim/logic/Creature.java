package creaturesim.logic;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Represents a competitor competing for food.
 * 
 * Has a position, size, Color, and a neural net for making decisions.
 * 
 * @author jakesamuellawrence
 */
public class Creature{
	
	double speed_multiplier = 0.1;
	double turning_multiplier = 0.05;
	
	int survival_time = 0;
	
	double x;
	double y;
	
	double distance_to_object;
	double size_of_object;
	double bearing = Math.random()*2*Math.PI - Math.PI;
	double radius = 1;
	double movement_speed = speed_multiplier/radius;
	
	Color color;
	
	double rotation_next_tick;
	boolean move_forwards_next_tick;
	
	boolean alive = true;
	
	Brain brain;
	
	public Creature(){
		this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		this.brain = new Brain();
	}
	
	public void spawnInRandomLocation(){
		Rectangle spawn_area = CompetitionManager.spawn_area;
		x = Math.random()*spawn_area.width + spawn_area.x;
		y = Math.random()*spawn_area.height + spawn_area.y;
		while(isNearFood() || isNearCreature()){
			x = Math.random()*spawn_area.width + spawn_area.x;
			y = Math.random()*spawn_area.height + spawn_area.y;
		}
	}
	
	public void revive(){
		radius = 1;
		alive = true;
	}
	
	public Creature makeClone(){
		Creature clone = new Creature();
		clone.color = this.color;
		clone.brain = this.brain;
		return(clone);
	}
	public Creature makeChild(){
		Creature child = new Creature();
		child.color = this.color;
		child.brain = this.brain;
		child.brain.mutate();
		return(child);
	}
	
	boolean isNearFood(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(distanceTo(food.get(i)) < 2){
				return(true);
			}
		}
		return(false);
	}
	
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
		rotate();
		move();
		checkForEating();
		loseEnergy();
		runThroughNetwork();
	}
	
	/**
	 * Rotates the creature, then normalises it's bearing to within the range -PI to PI
	 */
	void rotate(){
		bearing += rotation_next_tick;
		if(bearing > Math.PI){
			bearing = bearing - 2*Math.PI;
		}
		else if(bearing < -Math.PI){
			bearing = bearing + 2*Math.PI;
		}
	}
	
	/**
	 * Moves the creature in the direction of it's bearing
	 */
	void move(){
		if(move_forwards_next_tick){
			x += movement_speed*Math.cos(bearing);
			y += movement_speed*Math.sin(bearing);
		}
		else{
			x -= movement_speed*Math.cos(bearing);
			y -= movement_speed*Math.sin(bearing);
		}
	}
	
	/**
	 * Checks to see if any creatures are on top of food pellets. If they are, they eat the food pellet
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
	 * If the creature's radius is too small, it dies.
	 */
	public void loseEnergy(){
		radius -= CompetitionManager.energy_loss_rate;
		movement_speed = speed_multiplier/radius;
		if(radius < 0.5){
			die();
		}
		else{
			survival_time++;
		}
	}
	
	/**
	 * Gives values to the input nodes, then gets output from the output nodes, to see
	 * how the creature should move next turn.
	 */
	void runThroughNetwork(){
		if(bearing >= -Math.PI/2 && bearing <= Math.PI/2){
			checkForFoodRightSide();
		}
		else{
			checkForFoodLeftSide();
		}
		
		brain.loadValues(distance_to_object, size_of_object, radius, movement_speed);
		move_forwards_next_tick = brain.getMovement() == 1;
		rotation_next_tick = brain.getTurning()*turning_multiplier; // multiplier so creatures turn in less tight circles
	}
	
	/**
	 * Checks to see if any of the food pellets to the right of the creature are in the 
	 * creature's line of sight. This is required as the method for checking
	 * whether a creature is in the line of sight detects creatures to both the left
	 * and the right. If a creature is found, inputs the distance to it and it's radius into
	 * the neural network
	 */
	void checkForFoodRightSide(){
		FoodPellet nearest_creature = null;
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(food.get(i).getX() >= this.x){
				if(inLineWith(food.get(i))){
					if(nearest_creature == null){
						nearest_creature = food.get(i);
					}
					else if(distanceTo(food.get(i)) < distanceTo(nearest_creature)){
						nearest_creature = food.get(i);
					}
				}
			}
		}
		if(nearest_creature != null){
			distance_to_object = distanceTo(nearest_creature);
			size_of_object = nearest_creature.getRadius();
		}
	}
	
	/**
	 * Checks to see if any food pellets to the left of the creature are in
	 * the creature's line of sight. This is required as the method for checking
	 * whether a creature is in the line of sight detects creatures to both the left
	 * and the right. If a creature is found, inputs the distance to it and it's radius into
	 * the neural network
	 */
	void checkForFoodLeftSide(){
		FoodPellet nearest_creature = null;
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(food.get(i).getX() < this.x){
				if(inLineWith(food.get(i))){
					if(nearest_creature == null){
						nearest_creature = food.get(i);
					}
					else if(distanceTo(food.get(i)) < distanceTo(nearest_creature)){
						nearest_creature = food.get(i);
					}
				}
			}
		}
		if(nearest_creature != null){
			distance_to_object = distanceTo(nearest_creature);
			size_of_object = nearest_creature.getRadius();
		}
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
