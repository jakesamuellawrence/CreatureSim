package creaturesim.display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import creaturesim.logic.CompetitionManager;
import creaturesim.logic.Creature;
import creaturesim.logic.FoodPellet;

/**
 * Panel to be displayed by CSPanel's CardLayout.
 * 
 * Represents what is displayed in-game, i.e the creatures being competed
 * 
 * @author jakesamuellawrence
 *
 */
public class InGame extends JPanel{
	
	double x = 0;
	double y = 0;
	
	double pixels_per_metre = 25;
	
	/**
	 * Constructor for InGame. Calls the super constructor of JPanel
	 */
	public InGame(){
		super();
	}
	
	/**
	 * Overridden paintComponent method from JPanel. Is called when the repaint()
	 * method is called.
	 * 
	 * Paints the background black, then paints all creatures, then paints food pellets
	 * 
	 * @param g Graphics context which can be painted on
	 * @see JPanel
	 */
	public void paintComponent(Graphics g){
		// Draw Background
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw whole generation
		Creature[] creatures = CompetitionManager.getCurrentGeneration().getCreatures();
		for(int i = 0; i < creatures.length; i++){
			if(creatures[i].isAlive()){
				// Draw creature
				g.setColor(creatures[i].getColor());
				g.fillOval(getXRelativeToCamera(creatures[i].getX() - creatures[i].getRadius()),
						   getYRelativeToCamera(creatures[i].getY() - creatures[i].getRadius()), 
						   getRelativeDistance(2*creatures[i].getRadius()), 
						   getRelativeDistance(2*creatures[i].getRadius()));
				// Draw outline
				Graphics2D g2d = (Graphics2D)g;
				g2d.setColor(Color.white);
				g2d.setStroke(new BasicStroke(3));
				g2d.drawOval(getXRelativeToCamera(creatures[i].getX() - creatures[i].getRadius()),
						     getYRelativeToCamera(creatures[i].getY() - creatures[i].getRadius()), 
						     getRelativeDistance(2*creatures[i].getRadius()), 
						     getRelativeDistance(2*creatures[i].getRadius()));
				// Draw looking line
				g.setColor(Color.white);
				g.drawLine(getXRelativeToCamera(creatures[i].getX()), 
						   getYRelativeToCamera(creatures[i].getY()),
						   getXRelativeToCamera(creatures[i].getX() + creatures[i].getRadius()*Math.cos(creatures[i].getBearing())),
					       getYRelativeToCamera(creatures[i].getY() + creatures[i].getRadius()*Math.sin(creatures[i].getBearing())));
				// Draw names
				String creature_name = creatures[i].getName();
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				FontMetrics fm = g.getFontMetrics();
				g.setColor(Color.white);
				g.drawString(creature_name, 
						     getXRelativeToCamera(creatures[i].getX()) - fm.stringWidth(creature_name)/2, 
						     getYRelativeToCamera(creatures[i].getY()-creatures[i].getRadius()) - 10);
			}
		}
		
		// Draw food pellets
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			// Draw pellet
			g.setColor(food.get(i).color);
			g.fillOval(getXRelativeToCamera(food.get(i).getX() - food.get(i).getRadius()), 
					   getYRelativeToCamera(food.get(i).getY() - food.get(i).getRadius()), 
					   getRelativeDistance(2*food.get(i).getRadius()), 
					   getRelativeDistance(2*food.get(i).getRadius()));
		}
	}
	
	/**
	 * Converts global logic x-coordinates (metres) into a position relative to the camera position,
	 * and relative to the centre of the display area (pixels).
	 * 
	 * @param x double, the global coordinate, in metres, to be converted
	 * @return the relative coordinate in pixels
	 */
	public int getXRelativeToCamera(double x){
		double relative_x_metres = x - this.x;
		int relative_x_pixels = (getWidth()/2) + (int)Math.round(relative_x_metres*pixels_per_metre);
		return(relative_x_pixels);
	}
	
	/**
	 * Converts global logic y-coordinates (metres) into a position relative to the camera position,
	 * and relative to the centre of the display area (pixels).
	 * 
	 * @param y double, the global coordinate, in metres, to be converted
	 * @return the relative coordinate in pixels
	 */
	public int getYRelativeToCamera(double y){
		double relative_y_metres = y - this.y;
		int relative_y_pixels = (getHeight()/2) + (int)Math.round(relative_y_metres*pixels_per_metre);
		return(relative_y_pixels);
	}
	
	/**
	 * Converts a distance in metres to a distance in pixels.
	 * 
	 * @param distance the distance to be converted, in metres
	 * @return the calculated distance in pixels
	 */
	public int getRelativeDistance(double distance){
		return((int)Math.round(distance*pixels_per_metre));
	}
}
