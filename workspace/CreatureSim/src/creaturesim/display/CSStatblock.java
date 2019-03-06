package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CSStatblock extends JPanel{
	int border_thickness = 3;
	int margin = 5; 
	
	@Override
	public void paintComponent(Graphics g){
		// Draw Black border
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw orange background
		g.setColor(Color.decode("#f2a805"));
		g.fillRect(border_thickness, border_thickness, getWidth()-2*border_thickness, getHeight()-2*border_thickness);
		
		// Draw stats
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		FontMetrics fm = g.getFontMetrics();
		int text_height = fm.getAscent();
		g.drawString("Current Generation: XX", margin, getHeight()*2/7);
		g.drawString("Generation Size: XX", margin, getHeight()*4/7);
		g.drawString("Best Creature: XXX", margin, getHeight()*6/7);
		g.drawString("Average Survival Time: XX", getWidth()/2, getHeight()*2/7);
		g.drawString("Highest Survival Time: XX", getWidth()/2, getHeight()*4/7);
		g.drawString("Lowest Survival Time: XX", getWidth()/2, getHeight()*6/7);
	}
}
