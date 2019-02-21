package creaturesim.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * Custom button class for rendering consistently styled buttons
 * 
 * Extends JButton class to use super methods for detecting button
 * presses
 * 
 * @author jakesamuellawrence
 */
public class CSButton extends JButton{
	
	public String button_text; 
	
	public final int preferred_x = 250;
	public final int preferred_y = 35;
	
	public final int border_thickness = 3;
	
	/**
	 * Sets the action and bounds of the button
	 * 
	 * @param action string trigger word that determines what happens when the button is pressed
	 * @param bounds array of ints for the bounds of button: x,y,width,height
	 */
	public CSButton(String action){
		this.button_text = action;
		this.setActionCommand(action);
		this.setPreferredSize(new Dimension(preferred_x, preferred_y));
	}
	
	/**
	 * Displays an orange box with a black border and writes button_text in the centre.
	 * 
	 * Overridden paintComponent method from JPanel. Is called when the repaint()
	 * method is called.
	 */
	@Override
	public void paintComponent(Graphics g){
		// Draw Border
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Draw Button
		g.setColor(Color.decode("#f2a805"));
		g.fillRect(border_thickness, border_thickness, 
				   this.getWidth() - 2*border_thickness, this.getHeight() - 2*border_thickness);
		// Draw Text
		g.setColor(Color.black);
		Font button_font = new Font("Arial", Font.BOLD, 18);
		this.setFont(button_font);
		FontMetrics fm = g.getFontMetrics();
		int text_width = fm.stringWidth(button_text);
		int text_height = fm.getAscent();
		g.drawString(button_text, this.getWidth()/2 - text_width/2, this.getHeight()/2 + (text_height/2));
	}
}
