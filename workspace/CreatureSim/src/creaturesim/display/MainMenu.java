package creaturesim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class MainMenu extends JPanel{
	
	static GridBagConstraints constraints = new GridBagConstraints();
	
	public MainMenu(){
		super();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		
		constraints.fill = GridBagConstraints.BOTH;
		
		addButton(4, 0, 1, 1, 5, "Slow Generation");
		addButton(4, 1, 1, 1, 5, "Quick Generation");
		addButton(4, 2, 1, 1, 5, "View Previous Generation");
		addButton(4, 12, 5, 1, 5, "Start Fresh");
		addButton(0, 12, 1, 1, 5, "Exit To Desktop");
		
		addTitle(0, 0, 2, 2, "CreatureSim", "By Jake Lawrence");
		
		addGraph(0, 3, 3, 9);
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
	public void addButton(int x, int y, int width, int height, int insets, String action){
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		CSButton button = new CSButton(action);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.insets = new Insets(insets, insets, insets, insets);
		this.add(button, constraints);
	}
	public void addTitle(int x, int y, int width, int height, String title, String subtitle){
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		CSTitle cstitle = new CSTitle(title, subtitle);
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
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
}
