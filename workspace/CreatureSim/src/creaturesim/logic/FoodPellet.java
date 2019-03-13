package creaturesim.logic;

import java.awt.Color;

/**
 * Represents a pellet of food in the comepetition, which can be eaten by a creature.
 * 
 * Has a position, radius, and colour.
 * 
 * @author jakesamuellawrence
 */
public class FoodPellet{
	
	double x = Math.random()*CompetitionManager.spawn_area.width + CompetitionManager.spawn_area.x;
	double y = Math.random()*CompetitionManager.spawn_area.height + CompetitionManager.spawn_area.y;
	double radius = 0.2;
	
	public Color color = Color.GREEN;
	
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
