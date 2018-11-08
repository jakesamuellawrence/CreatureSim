package creaturesim.display;

import javax.swing.JFrame;

public class CSFrame extends JFrame{
	int default_x_resolution = 1280;
	int default_y_resolution = 720;
	
	/**
	 * Constructor for CSFrame. Calls the parent JFrame constructor,
	 * then sets some customisation options.
	 */
	public CSFrame(){
		super("CreatureSim!"); 										// Calls the superclass's constructor, which sets the name of the frame
		this.setSize(default_x_resolution, default_y_resolution); 	// Sets the size of the window
		this.setResizable(false); 									// Makes the window un-resizable, so that layout stays consistent;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 				// Sets the window to be destroyed when the X is pressed
		this.setVisible(true); 										// Makes the window visible
	}
}
