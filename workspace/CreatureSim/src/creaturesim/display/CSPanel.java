package creaturesim.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CSPanel extends JPanel{
	int default_x_resolution = 1280;
	int default_y_resolution = 720;
	
	/**
	 * Constructor for CSPanel. Calls parent constructor from JPanel,
	 * then sets the size.
	 */
	public CSPanel(){
		super();
		this.setPreferredSize(new Dimension(default_x_resolution, default_y_resolution)); // Sets the size of the window
	}
	
	/**
	 * Overidden paintComponent method from JPanel. Is called when the repaint()
	 * method is called
	 * 
	 * @param g Graphics context which can be painted on
	 * @see JPanel
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g; // downcast g to subtype Graphics2D, so that more methods are available
		
		// Driver code to test canvas
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.red);
		g.fillOval(620, 340, 40, 40);
	}
}
