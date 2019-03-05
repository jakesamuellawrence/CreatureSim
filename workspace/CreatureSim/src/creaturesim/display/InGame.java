package creaturesim.display;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

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
	}
}
