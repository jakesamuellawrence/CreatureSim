package creaturesim.display;

import javax.swing.JFrame;

import creaturesim.logic.CSActionListener;

/**
 * Acts as a window into which other components can be added
 * 
 * @author jakesamuellawrence
 * @see JFrame
 */
public class CSFrame extends JFrame{
	
	CSPanel canvas;
	
	/**
	 * Constructor for CSFrame. Calls the parent JFrame constructor,
	 * then sets some customisation options.
	 */
	public CSFrame(){
		super("CreatureSim!"); 		// Calls the superclass's constructor, which sets the name of the frame
		
		canvas = new CSPanel();										
		this.add(canvas);
		this.pack();				// Forces window to be an appropriate size for all contained components
		
		this.setResizable(true); 						
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 	
		this.setVisible(true);
	}
}
