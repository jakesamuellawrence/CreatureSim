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
 * @see JButton
 * @author jakesamuellawrence
 */
public class CSButton extends JButton{
	
	Font font = new Font("Arial", Font.PLAIN, 20);
	
	public String button_text; 
	
	public final int preferred_width = 275;
	public final int preferred_height = 45;
	
	public final int border_thickness = 3;
	
	/**
	 * Sets the action and button text, and makes the button opaque.
	 * 
	 * @param action String. trigger word that determines what happens when the button is pressed. 
	 * also used to determine what is displayed on the button
	 */
	public CSButton(String action){
		this.button_text = action;
		this.setActionCommand(action);
		this.setOpaque(false);
	}
	
	/**
	 * Overridden paintComponent method from JPanel. Is called when the built-in repaint()
	 * method is called.
	 * 
	 * Displays an orange box with a black border and writes button_text in the centre.
	 * 
	 * @param g Graphics Graphics context automatically passed in by AWT.
	 */
	@Override
	public void paintComponent(Graphics g){
		// Draw Border
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw Button
		g.setColor(Color.decode("#ff2a805"));
		g.fillRect(border_thickness, border_thickness, getWidth()-2*border_thickness, getHeight()-2*border_thickness);
		
		// Draw Text
		g.setColor(Color.black);
		this.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		int text_width = fm.stringWidth(button_text);
		int text_height = fm.getAscent();
		g.drawString(button_text, getWidth()/2 - text_width/2, getHeight()/2 + (text_height/2));
	}
}
