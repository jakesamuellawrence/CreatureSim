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
public class CSCanvas extends JPanel{
	int default_x_resolution = 1280;
	int default_y_resolution = 720;
	
	CardLayout layout;
	JPanel main_menu;
	JPanel in_game;
	
	/**
	 * Constructor for CSPanel. Calls parent constructor for JPanel,
	 * then sets the size, adds a CardLayout, adds cards, and shows
	 * the main menu card
	 */
	public CSCanvas(){
		super(new CardLayout());		// Calls the constructor in the superclass, and passes in a layout to be applied
		layout = (CardLayout)this.getLayout();
		
		main_menu = new MainMenu();
		in_game = new InGame();
		
		this.add(main_menu, "Main Menu");
		this.add(in_game, "In Game");
		
		layout.show(this, "Main Menu");
		
		this.setPreferredSize(new Dimension(default_x_resolution, default_y_resolution));
	}
	
	/**
	 * Tells the layout manager to display a different card,
	 * so long as the card has been added to it. 
	 * 
	 * @param card the string identifying the card to be switched to.
	 */
	public void switchCard(String card){
		layout.show(this, card);
	}
}
