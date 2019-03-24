package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import creaturesim.logic.CompetitionManager;

/**
 * Custom Panel class for displaying numerical statistics about the current generation
 * 
 * Extends JPanel so it is automatically painted.
 * 
 * @see JPanel
 * @author jakesamuellawrence
 */
public class CSStatblock extends JPanel{
	
	Font font = new Font("Arial", Font.BOLD, 14);
	
	int border_thickness = 3;
	int margin = 10; 
	
	String gen_number = "XX";
	String gen_size = "XX";
	String best_creature = "XX";
	String av_survival_time = "XX";
	String high_survival_time = "XX";
	String low_survival_time = "XX";
	
	
	/**
	 * paintComponent method overridden from JPanel. Draws an orange rectangle
	 * with black border, then writes the stats in it.
	 * 
	 * @param g Graphics Graphics context that gets automatically passed in by AWT.
	 */
	@Override
	public void paintComponent(Graphics g){
		// Draw Black border
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw orange background
		g.setColor(Color.decode("#f2a805"));
		g.fillRect(border_thickness, border_thickness, getWidth()-2*border_thickness, getHeight()-2*border_thickness);
		
		// Draw stats
		gen_number = Integer.toString(CompetitionManager.generation_number);
		gen_size = Integer.toString(CompetitionManager.generation_size);
		
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString("Current Generation: " + gen_number, margin, getHeight()*2/7);
		g.drawString("Generation Size: " + gen_size, margin, getHeight()*4/7);
		g.drawString("Best Creature: XXX", margin, getHeight()*6/7);
		g.drawString("Average Survival Time: XX", getWidth()/2, getHeight()*2/7);
		g.drawString("Highest Survival Time: XX", getWidth()/2, getHeight()*4/7);
		g.drawString("Lowest Survival Time: XX", getWidth()/2, getHeight()*6/7);
	}
}
