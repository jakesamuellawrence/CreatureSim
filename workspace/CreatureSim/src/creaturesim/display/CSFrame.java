package creaturesim.display;

import javax.swing.JFrame;

public class CSFrame extends JFrame{
	
	
	CSPanel canvas;
	
	/**
	 * Constructor for CSFrame. Calls the parent JFrame constructor,
	 * then sets some customisation options.
	 */
	public CSFrame(){
		super("CreatureSim!"); 										// Calls the superclass's constructor, which sets the name of the frame
		
		canvas = new CSPanel();										// Creates a new object of the CSPanel class
		this.add(canvas);											// Adds the canvas to the frame so that it can be painted on
		this.pack();												// Forces window to be an appropriate size for all contained components
		
		this.setResizable(true); 									// Makes the window un-resizable, so that layout stays consistent;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 				// Sets the window to be destroyed when the X is pressed
		this.setVisible(true); 										// Makes the window visible
	}
}
