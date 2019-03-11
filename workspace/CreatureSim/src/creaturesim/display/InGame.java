package creaturesim.display;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import creaturesim.Main;
import creaturesim.logic.Creature;

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
	
	Creature creature = Main.creature;
	
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
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.green);
		g.fillOval(getXRelativeToCamera(creature.getX()-creature.getRadius()),
				   getYRelativeToCamera(creature.getY()-creature.getRadius()), 
				   getRelativeRadius(2*creature.getRadius()), 
				   getRelativeRadius(2*creature.getRadius()));
		g.drawLine(getXRelativeToCamera(creature.getX()),
				   getYRelativeToCamera(creature.getY()),
				   getXRelativeToCamera(creature.getX() + 2*Math.cos(creature.getBearing())), 
				   getYRelativeToCamera(creature.getY() + 2*Math.sin(creature.getBearing())));
		
		g.setColor(Color.red);
		g.drawRect(getXRelativeToCamera(-25), 
				   getYRelativeToCamera(-12.5),
				   getRelativeRadius(50), 
				   getRelativeRadius(25));
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
