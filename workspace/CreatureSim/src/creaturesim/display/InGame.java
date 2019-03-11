package creaturesim.display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import creaturesim.logic.Creature;
import creaturesim.logic.FoodPellet;
import creaturesim.logic.CompetitionManager;

/**
 * Panel to be displayed by CSPanel's CardLayout.
 * 
 * Represents what is displayed in-game. I.e, creatures, (creature scores?),
 * fast forwards controls, and a button to exit
 * 
 * @author jakesamuellawrence
 *
 */
public class InGame extends JPanel{
	
	double x = 0;
	double y = 0;
	
	double pixels_per_metre = 25;
	
	public InGame(){
		super();
	}
	
	/**
	 * Overidden paintComponent method from JPanel. Is called when the repaint()
	 * method is called.
	 * 
	 * Paints the background black.
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
						   getRelativeRadius(2*creatures[i].getRadius()), 
						   getRelativeRadius(2*creatures[i].getRadius()));
				// Draw outline
				Graphics2D g2d = (Graphics2D)g;
				g2d.setColor(Color.white);
				g2d.setStroke(new BasicStroke(3));
				g2d.drawOval(getXRelativeToCamera(creatures[i].getX() - creatures[i].getRadius()),
						     getYRelativeToCamera(creatures[i].getY() - creatures[i].getRadius()), 
						     getRelativeRadius(2*creatures[i].getRadius()), 
						     getRelativeRadius(2*creatures[i].getRadius()));
			}
		}
		
		// Draw food pellets
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			// Draw pellet
			g.setColor(Color.green);
			g.fillOval(getXRelativeToCamera(food.get(i).getX() - food.get(i).getRadius()), 
					   getYRelativeToCamera(food.get(i).getY() - food.get(i).getRadius()), 
					   getRelativeRadius(2*food.get(i).getRadius()), 
					   getRelativeRadius(2*food.get(i).getRadius()));
		}
	}
	
	public int getXRelativeToCamera(double x){
		double relative_x_metres = x - this.x;
		int relative_x_pixels = (getWidth()/2) + (int)Math.round(relative_x_metres*pixels_per_metre);
		return(relative_x_pixels);
	}
	public int getYRelativeToCamera(double y){
		double relative_y_metres = y - this.y;
		int relative_y_pixels = (getHeight()/2) + (int)Math.round(relative_y_metres*pixels_per_metre);
		return(relative_y_pixels);
	}
	public int getRelativeRadius(double radius){
		return((int)Math.round(radius*pixels_per_metre));
	}
}
