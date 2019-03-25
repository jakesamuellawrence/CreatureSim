package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import creaturesim.logic.CompetitionManager;
import creaturesim.logic.Generation;

/**
 * Custom Panel class for displaying a graph of creature statistics.
 * 
 * Extends the JPanel class so it is automatically repainted
 * 
 * @see JPanel
 * @author jakesamuellawrence
 */
public class CSGraph extends JPanel{
	
	int margin = 20;
	int axis_thickness = 50;
	
	Font axis_label_font = new Font("Arial", Font.BOLD, 20);
	Font numbers_font = new Font("Arial", Font.PLAIN, 14);
	
	/**
	 * Draws A graph using statistics about the creatures and previous generations.
	 * 
	 * @param g Graphics Graphics context automatically passed in by AWT.
	 */
	@Override
	public void paintComponent(Graphics g){
		// Draw graph background
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Get generation information
		ArrayList<Generation> generations = CompetitionManager.generations;
		int[] highest_times = new int[generations.size()];
		int max_highest_time = generations.get(0).highest_survival_time;
		for(int i = 0; i < generations.size(); i++){
			highest_times[i] = generations.get(i).highest_survival_time;
			if(highest_times[i] > max_highest_time){
				max_highest_time = highest_times[i];
			}
		}
		int number_of_generations = CompetitionManager.generation_number;
		
		// Draw Y axis line and label
		g.setColor(Color.black);
		g.setFont(axis_label_font);
		FontMetrics fm = g.getFontMetrics();
		String y_label = "Survival Time (ticks)";
		int label_height = fm.getAscent();
		g.drawString(y_label, margin, margin+label_height);
		int graph_height = getHeight() - 2*margin - label_height - axis_thickness;
		int graph_start_y = 2*margin+label_height;
		g.drawLine(axis_thickness, 
				   graph_start_y, 
				   axis_thickness, 
				   graph_start_y+graph_height);
		// Draw Y Axis numbers
		double pixels_per_tick = graph_height;
		if(max_highest_time != 0){
			pixels_per_tick = (double)graph_height/(double)max_highest_time;
		}
		g.setFont(numbers_font);
		fm = g.getFontMetrics();
		int step_y = 100;
		while((double)pixels_per_tick*(double)step_y < 30){
			step_y += 100;
		}
		for(int i = 0; i < max_highest_time; i += step_y){
			int height_on_y_axis = (graph_start_y+graph_height)-(int)Math.round(i*pixels_per_tick);
			g.drawLine(axis_thickness-5, 
					   height_on_y_axis, 
					   axis_thickness,
					   height_on_y_axis);
			String number = Integer.toString(i);
			int number_width = fm.stringWidth(number);
			int number_height = fm.getAscent();
			g.drawString(number, axis_thickness-10-number_width, height_on_y_axis+number_height/2);
		}
		
		// Draw X axis line and label
		g.setColor(Color.black);
		g.setFont(axis_label_font);
		fm = g.getFontMetrics();
		String x_label = "Generations";
		int label_width = fm.stringWidth(x_label);
		int graph_width = getWidth() - 2*margin - label_width - axis_thickness;
		int graph_start_x = axis_thickness;
		g.drawString(x_label, graph_start_x+graph_width+margin, getHeight()-axis_thickness+label_height/2);
		g.drawLine(graph_start_x, 
				   getHeight()-axis_thickness, 
				   graph_start_x+graph_width, 
				   getHeight()-axis_thickness);
		// Draw Y Axis numbers
		double pixels_per_gen = graph_width;
		if(number_of_generations != 0){
			 pixels_per_gen = (double)graph_width/(double)number_of_generations;
		}
		g.setFont(numbers_font);
		fm = g.getFontMetrics();
		int step_x = 1;
		while((double)pixels_per_gen*(double)step_x < 30){
			step_x *= 10;
		}
		for(int i = 0; i < number_of_generations; i += step_x){
			int position_on_x_axis = (graph_start_x)+(int)Math.round(i*pixels_per_gen);
			g.drawLine(position_on_x_axis, 
					   getHeight()-axis_thickness+5, 
					   position_on_x_axis,
					   getHeight()-axis_thickness);
			String number = Integer.toString(i);
			int number_width = fm.stringWidth(number);
			int number_height = fm.getAscent();
			g.drawString(number, position_on_x_axis-number_width/2, getHeight()-axis_thickness+5+number_height);
		}
		
		// Draw the line graph
		g.setColor(Color.red);
		for(int i = 1; i < number_of_generations; i++){
			g.drawLine((int)Math.round(graph_start_x + (i-1)*pixels_per_gen),
					   (int)Math.round((graph_start_y+graph_height) - highest_times[i-1]*pixels_per_tick),
					   (int)Math.round(graph_start_x + (i)*pixels_per_gen),
					   (int)Math.round((graph_start_y+graph_height) - highest_times[i]*pixels_per_tick));
		}
	}
}
