package creaturesim.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * /**
 * Custom TextBlock class for rendering consistently styled text blocks
 * 
 * Extends JPanel so that it will be automatically painted.
 * 
 * @author jakesamuellawrence
 * 
 * @author jakesamuellawrence
 *
 */
public class CSTextBlock extends JPanel{
	
	String text;
	String[] words;
	
	int line_spacing = 2;
	int margin = 5;
	
	public CSTextBlock(String text){
		this.text = text;
		words = text.split(" ");
		this.setOpaque(false);
//		this.setPreferredSize(new Dimension(100, 200));
	}
	
	@Override
	public void paintComponent(Graphics g){
		// Set Font
		Font button_font = new Font("Arial", Font.BOLD, 14);
		this.setFont(button_font);
		FontMetrics fm = g.getFontMetrics();
		
		drawLine(0, fm.getAscent(), fm, g);
		
		// Draw outline
//		g.setColor(Color.white);
//		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
	public void drawLine(int startingWord, int line_y, FontMetrics fm, Graphics g){
		// Check for exit to recursion
		if(startingWord >= words.length){
			return;
		}
		
		// Loop to find last word that will fit in the line
		int endingWord;
		String to_draw = "";
		for(endingWord = startingWord; endingWord < words.length; endingWord++){
			if(fm.stringWidth(to_draw+words[endingWord]+" ") < getWidth()-margin){
				to_draw += words[endingWord] + " ";
			}
			else{
				break;
			}
		}
		
		// Draw the string
		g.setColor(Color.decode("#faebd7"));
		g.setColor(Color.black);
		g.drawString(to_draw, margin, line_y);
		
		// Draw next line recursively
		if(endingWord != startingWord){
			drawLine(endingWord, line_y+fm.getAscent()+line_spacing, fm, g);
		}
		else{
			System.out.println("ERROR: Not enough space!");
		}
	}
	
	public String wordsToString(int startingIndex, int endingIndex){
		String to_return = "";
		for(int i = startingIndex; i < endingIndex; i++){
			to_return = to_return + words[i] + " ";
		}
		return(to_return);
	}	
}
