package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CSGraph extends JPanel{
	@Override
	public void paintComponent(Graphics g){
		// Draw graph background
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw placeholder for graph
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		FontMetrics fm = g.getFontMetrics();
		String placeholder = "This is a placeholder for the graph, until other systems have been implemented";
		int placeholder_length = fm.stringWidth(placeholder);
		g.drawString(placeholder, getWidth()/2-placeholder_length/2, getHeight()/2);
	}
}
