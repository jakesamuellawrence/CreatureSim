package creaturesim.display;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Acts as a canvas on which things can be drawn, and other components can be added
 * 
 * @author jakesamuellawrence
 * @see JPanel
 */
public class CSPanel extends JPanel{
	int default_x_resolution = 1280;
	int default_y_resolution = 720;
	
	CardLayout layout;
	JPanel main_menu;
	JPanel in_game;
	
	/**
	 * Constructor for CSPanel. Calls parent constructor from JPanel,
	 * then sets the size.
	 */
	public CSPanel(){
		super(new CardLayout());
		this.layout = (CardLayout) this.getLayout();
		
		main_menu = new MainMenu();
		in_game = new InGame();
		
		this.add(main_menu, "Main Menu");
		this.add(in_game, "In Game");
		
		layout.show(this, "Main Menu");
		
		this.setPreferredSize(new Dimension(default_x_resolution, default_y_resolution)); // Sets the size of the window
	}
}
