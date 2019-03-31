package creaturesim.logic;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a pellet of food in the competition, which can be eaten by a creature.
 * 
 * Has a position, radius, and colour.
 * 
 * @author jakesamuellawrence
 */
public class FoodPellet{
	
	boolean overcrowded = false;
	double overcrowding_distance = 1;
	
	double x;
	double y;
	
	public static double standard_radius = 0.2; 
	
	double radius = standard_radius;
	
	public Color color = Color.GREEN;
	
	/**
	 * Constructor for FoodPellet. Moves the food to a random location
	 */
	public FoodPellet(){
		spawnInRandomLocation();
	}
	
	/**
	 * Puts the food in a random spot. If the food is too near other pieces of food, marks it as overcrowded, 
	 * so it will not be added to the competition.
	 */
	void spawnInRandomLocation(){
		x = Math.random()*CompetitionManager.spawn_area.width + CompetitionManager.spawn_area.x;
		y = Math.random()*CompetitionManager.spawn_area.height + CompetitionManager.spawn_area.y;
		if(isNearFood() || isNearCreature()){
			overcrowded = true;
		}
	}
	
	/**
	 * Returns whether or not this food pellet is near other pellets of food
	 * @return
	 */
	boolean isNearFood(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(distanceTo(food.get(i)) < overcrowding_distance+food.get(i).radius+radius){
				return(true);
			}
		}
		return(false);
	}
	
	/**
	 * checks whether the food pellet is close to any creatures in the current generation
	 * 
	 * @return whether or not they are nearby
	 */
	boolean isNearCreature(){
		Creature[] creatures = CompetitionManager.getCurrentGeneration().creatures;
		for(int i = 0; i < creatures.length; i++){
			if(distanceTo(creatures[i]) < overcrowding_distance+radius+creatures[i].radius){
				return(true);
			}
		}
		return(false);
	}
	
	/**
	 * returns the distance between this food pellet and another food pellet
	 * 
	 * @param target FoodPellet, the food pellet to which the distance is being found.
	 * @return the distance between this food pellet and the target food pellet
	 */
	double distanceTo(FoodPellet target){
		return(Math.hypot(target.getX() - x, target.getY() - y));
	}
	
	/**
	 * returns the distance between this food pellet and a given creature.
	 * 
	 * @param target Creature, the creature to which the distance is being found.
	 * @return the distance between this food pellet and the target creature
	 */
	double distanceTo(Creature target){
		return(Math.hypot(target.getX() - x, target.getY() - y));
	}
	
	/**
	 * Returns the x-coordinate of the food pellet, in metres.
	 * 
	 * @return the x-coordinate, in metres
	 */
	public double getX(){
		return(x);
	}
	
	/**
	 * returns the y-coordinate of the food pellet, in metres.
	 * 
	 * @return the y-coordinate in metres
	 */
	public double getY(){
		return(y);
	}
	
	/**
	 * returns the radius of the food pellet, in metres
	 * 
	 * @return the radius in metres.
	 */
	public double getRadius(){
		return(radius);
	}
}