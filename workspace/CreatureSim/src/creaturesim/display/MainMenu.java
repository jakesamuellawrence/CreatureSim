package creaturesim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

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
	
	static GridBagConstraints constraints = new GridBagConstraints();
	
	public MainMenu(){
		super();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		
		constraints.fill = GridBagConstraints.BOTH;
		
<<<<<<< HEAD
		addButton(4, 0, 1, 1, "Slow Generation");
		addButton(4, 1, 1, 1, "Quick Generation");
		addButton(4, 2, 1, 1, "View Previous Generation");
		addButton(4, 12, 1, 1, "Start Fresh");
		addButton(0, 12, 1, 1, "Exit To Desktop");
		
		addTextBlock(4, 4, 1, 8, "The creatures in this simulation are powered by neural nets " +
								 "and evolutional learning. " +
				                 "A neural net, in essense, is just a collection of nodes, " +
				                 "each of which is connected by a weight, where each weight is " +
				                 "some number, typically between -1 and 1. When you wish to get " +
				                 "the net to compute something, you pass inputs into the begining " +
				                 "nodes of the net. Those inputs nodes then pass on values to the " +
				                 "nodes they're connected to, which in turn passes on their values to " +
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
		addTextBlock(2, 0, 2, 1, "Compete a generation in human-friendly time, and view the " +
								 "competition");
		addTextBlock(2, 1, 2, 1, "Compete a generation as quickly as possible, without viewing " +
				                 "the competition");
		addTextBlock(2, 2, 2, 1, "Re-view the previous generation's competition. NOTE: this will " +
				                 "cause all creatures to recompete, rather than replay the events. " +
				                 "This will not change the previously recorded scores, but what is " +
				                 "shown may vary slightly from the recorded results due to random " +
				                 "variation");
		addTextBlock(3, 12, 1, 1, "Discard all creatures and start simulation again from scratch");
		addTextBlock(1, 12, 1, 1, "WARNING: current set of generations will be lost when the " +
				                  "application is closed.");
=======
		addButton(4, 0, 1, 1, 5, "Slow Generation");
		addButton(4, 1, 1, 1, 5, "Quick Generation");
		addButton(4, 2, 1, 1, 5, "View Previous Generation");
		addButton(4, 12, 5, 1, 5, "Start Fresh");
		addButton(0, 12, 1, 1, 5, "Exit To Desktop");
		
		addTitle(0, 0, 2, 2, "CreatureSim", "By Jake Lawrence");
		
		addCSStatblock(0, 2, 3, 1);
		
		addGraph(0, 3, 3, 9);
>>>>>>> statblock
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
	 * layout.
	 * 
	 * @param x int, the gridx for the button. 0 is leftmost column
	 * @param y int, the gridy for the button. 0 is the topmost row
	 * @param width int, the gridwidth for the button, in number of columns
	 * @param height int, the gridheight for the button, in number of rows
	 * @param action String, the action associated with the button, to be checked by an ActionListener 
	 */
<<<<<<< HEAD
	public void addButton(int x, int y, int width, int height, String action){
		// set constraints true for all buttons
		constraints.weightx = 0.3;
		constraints.weighty = 0.1;
		// instantiate button and set specific constraints
=======
	public void addButton(int x, int y, int width, int height, int insets, String action){
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
>>>>>>> statblock
		CSButton button = new CSButton(action);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(button, constraints);
	}
<<<<<<< HEAD
	public void addTextBlock(int x, int y, int width, int height, String text){
		// set weights dynamically
		constraints.weightx = 0.3;
		constraints.weighty = text.length()/50;
		CSTextBlock textblock = new CSTextBlock(text);
=======
	public void addTitle(int x, int y, int width, int height, String title, String subtitle){
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		CSTitle cstitle = new CSTitle(title, subtitle);
>>>>>>> statblock
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
<<<<<<< HEAD
		this.add(textblock, constraints);
=======
		this.add(cstitle, constraints);
	}
	public void addGraph(int x, int y, int width, int height){
		constraints.weightx = 1;
		constraints.weighty = 1;
		CSGraph graph = new CSGraph();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(graph, constraints);
	}
	public void addCSStatblock(int x, int y, int width, int height){
		constraints.weightx = 0.1;
		constraints.weighty = 0.2;
		CSStatblock statblock = new CSStatblock();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(statblock, constraints);
>>>>>>> statblock
	}
}
