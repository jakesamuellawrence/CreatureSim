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
	
	double x;
	double y;
	double radius = 0.2;
	
	public Color color = Color.GREEN;
	
	/**
	 * Constructor for FoodPellet. Moves the food to a random location
	 */
	public FoodPellet(){
		spawnInRandomLocation();
	}
	
	/**
	 * Keeps putting the food in random spots until one is found which is not near other food pellets
	 */
	void spawnInRandomLocation(){
		x = Math.random()*CompetitionManager.spawn_area.width + CompetitionManager.spawn_area.x;
		y = Math.random()*CompetitionManager.spawn_area.height + CompetitionManager.spawn_area.y;
		while(isNearFood()){
			x = Math.random()*CompetitionManager.spawn_area.width + CompetitionManager.spawn_area.x;
			y = Math.random()*CompetitionManager.spawn_area.height + CompetitionManager.spawn_area.y;
		}
	}
	
	/**
	 * Returns whether or not this food pellet is near other pellets of food
	 * @return
	 */
	boolean isNearFood(){
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(distanceTo(food.get(i)) < 5){
				return(true);
			}
		}
		return(false);
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
