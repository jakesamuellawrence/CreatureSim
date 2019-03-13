package creaturesim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import creaturesim.logic.CSActionListener;

/**
 * Panel to be displayed by CSPanel's CardLayout.
 * 
 * Represents the main menu, where a variety of buttons are present
 * which all carry put different functions.
 * 
 * @author jakesamuellawrence
 *
 */
public class MainMenu extends JPanel{
	
	static GridBagConstraints constraints = new GridBagConstraints(); // used to define how the components should be arranged
	
	CSActionListener actionlistener = new CSActionListener(); // Responds to button presses
	
	/**
	 * Constructor for MainMenu. Adds all the components that need to be displayed
	 */
	public MainMenu(){
		super();
		
		GridBagLayout layout = new GridBagLayout(); // Defines the method by which things should be laid out
		this.setLayout(layout);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(3, 3, 3, 3);
		
		addTitle				(0, 0, 2, 2, "CreatureSim", "By Jake Lawrence");
		addHorizontalTextBlock	(2, 0, 2, 1, "Compete a generation in human-friendly time, and view the " +
											 "competition");
		addButton				(4, 0, 1, 1, "Slow Generation");
		addHorizontalTextBlock	(2, 1, 2, 1, "Compete a generation as quickly as possible, without viewing " +
                                        	 "the competition");
		addButton				(4, 1, 1, 1, "Quick Generation");
		addCSStatblock			(0, 2, 2, 2);
		addHorizontalTextBlock	(2, 2, 2, 1, "Re-compete the previous generation. This will not change the recorded results for " +
										     "the previous generation");
		addButton		        (4, 2, 1, 1, "View Previous Generation");
		addGraph		        (0, 4, 4, 7);
		addVerticalTextBlock	(4, 4, 1, 8, "The creatures in this simulation are powered by neural nets " +
				 				         	 "and evolutional learning. " +
				 				 	         "A neural net, in essense, is just a collection of nodes, " +
				 				 	         "each of which is connected by a weight, where each weight is " +
				 				 	         "some number, typically between -1 and 1. When you wish to get " +
				 				 	         "the net to compute something, you pass inputs into the begining " +
				 				 	         "nodes of the net. Those inputs nodes then pass on values to the " +
				 				 	         "nodes they're connected to, which in turn pass on their values to " +
				 				 	         "more nodes, until eventually a value reaches the output. " +
				 				 	         "When a node passes on a value to another node, the value is " +
				 				 	         "multiplied by the weight which connects the two nodes. At first " +
				 				 	         "these weights are random, and so the output will be random too. " +
				 				 	         "However, after each generation competes, the nets which performed " +
				 				 	         "best will kept and slight mutations will be made from them. " +
				 				 	         "In this way, each generation, the weights become more and more " +
				 				 	         "refined to give good outputs based on the inputs. " +
				 				 	         "If you wish to learn more about neural nets, I suggest reading " +
											 "this book: http://hagan.okstate.edu/NNDesign.pdf");
		addButton				(0, 12, 1, 1, "Exit To Desktop");
		addHorizontalTextBlock	(1, 12, 1, 1, "WARNING: current set of generations will be lost when the " +
											  "application is closed.");
		addHorizontalTextBlock	(3, 12, 1, 1, "Discard all creatures and start simulation again from scratch");
		addButton				(4, 12, 1, 1, "Start Fresh");
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
		g.setColor(Color.decode("#649748"));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	/**
	 * 
	 * Adds a button to the panel, in accordance with gridbag
	 * layout. Sets standard weights for all buttons
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 * @param action String, the action associated with the button, to be checked by an ActionListener 
	 */
	public void addButton(int x, int y, int width, int height, String action){
		constraints.weightx = 0.8;
		constraints.weighty = 0.125;
		CSButton button = new CSButton(action);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(button, constraints);
		button.addActionListener(actionlistener);
	}
	
	/**
	 * 
	 * Adds a text block that is intended to be vertical to the panel, in accordance with gridbag
	 * layout. Sets standard weights for all vertical text blocks.
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 * @param text String, the text to be displayed
	 */
	public void addVerticalTextBlock(int x, int y, int width, int height, String text){
		// set weights dynamically
		constraints.weightx = 0;
		constraints.weighty = 1.1;
		CSTextBlock textblock = new CSTextBlock(text);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(textblock, constraints);
	}
	
	/**
	 * Adds a text block that is intended to be horizontal to the panel, in accordance with gridbag
	 * layout. Sets standard weights for all horizontal text blocks
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 * @param text String, the text to be displayed
	 */
	public void addHorizontalTextBlock(int x, int y, int width, int height, String text){
		// set weights dynamically
		constraints.weightx = 1;
		constraints.weighty = 0;
		CSTextBlock textblock = new CSTextBlock(text);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(textblock, constraints);
	}
	
	/**
	 * Adds a title to the panel, in accordance with gridbag
	 * layout. Sets standard weights for all titles
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 * @param title String, the larger text to be displayed
	 * @param subtitle String, the smaller text to be displayed 
	 */
	public void addTitle(int x, int y, int width, int height, String title, String subtitle){
		constraints.weightx = 0;
		constraints.weighty = 0;
		CSTitle cstitle = new CSTitle(title, subtitle);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(cstitle, constraints);
	}
	
	/**
	 * Adds a graph to the panel, in accordance with gridbag
	 * layout. Sets standard weights for all buttons
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 */
	public void addGraph(int x, int y, int width, int height){
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		CSGraph graph = new CSGraph();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(graph, constraints);
	}
	
	/**
	 * Adds a graph to the panel, in accordance with gridbag
	 * layout. Sets standard weights for all buttons
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 */
	public void addCSStatblock(int x, int y, int width, int height){
		constraints.weightx = 0;
		constraints.weighty = 0;
		CSStatblock statblock = new CSStatblock();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(statblock, constraints);
	}
}
