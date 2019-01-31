package creaturesim.display;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainMenu extends JPanel{
	public MainMenu(){
		super();
	}
	
	/**
	 * Overridden paintComponent method from JPanel. Is called when the repaint()
	 * method is called.
	 * 
	 * Paints the background green
	 * 
	 * @param g Graphics context which can be painted on
	 * @see JPanel
	 */
	public void paintComponent(Graphics g){
		g.setColor(Color.green);
		g.fillRect(0, 0, 1280, 720);
	}
}
