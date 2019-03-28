package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Custom JPanel class for rendering consistently styled text blocks
 * 
 * Extends JPanel so that it will be automatically painted.
 * 
 * @author jakesamuellawrence
 *
 */
public class CSTextBlock extends JPanel{
	
	Font font = new Font("Arial", Font.BOLD, 14);
	
	String text;
	String[] words;
	ArrayList<String> lines;
	
	int line_spacing = 2;
	int margin = 5;
	
	/**
	 * Constructor for CSTextBlock. Assigns the passed in text to text variable, then
	 * makes the panel non-opaque
	 * 
	 * @param text the text to be displayed
	 */
	public CSTextBlock(String text){
		this.text = text;
		this.setOpaque(false);
	}
	
	/**
	 * Turns the instance variable text into an array of Strings, using a space character as a delimiter.
	 */
	public void convertTextToWords(){
		words = text.split(" ");
	}
	
	/**
	 * Turns the instance variable words into an ArrayList of Strings, recursively, depending on how big the area for the
	 * text to be displayed is.
	 * 
	 * @see ArrayList
	 * @param startingWord String. The word at the beggining of the line being converted
	 * @param fm FontMetrics Holds data about the current font such as sizes of strings
	 */
	public void convertWordsToLines(int startingWord, FontMetrics fm){
		// Check for exit to recursion
		if(startingWord >= words.length){
			return;
		}
		
		// Loop to find last word that will fit in the line
		int endingWord;
		String line = "";
		for(endingWord = startingWord; endingWord < words.length; endingWord++){
			if(fm.stringWidth(line+words[endingWord]+" ") < getWidth()-margin){
				line += words[endingWord] + " ";
			}
			else{
				break;
			}
		}
		
		// Add line to lines
		lines.add(line);
		
		// Add next line recursively
		if(endingWord != startingWord){
			convertWordsToLines(endingWord, fm);
		}
		else{
			System.out.println("ERROR: Not enough space!");
		}
	}
	
	/**
	 * Overridden paintComponent method from JPanel. Is called when repaint() is called 
	 * 
	 * Breaks text into lines and then displays them
	 * in the centre of the component.
	 * 
	 * @param g Graphics. Graphics context passed automatically by AWT.
	 */
	@Override
	public void paintComponent(Graphics g){
		// Set Font
		this.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		
		// Break text into lines
		lines = new ArrayList<String>();		
		convertTextToWords();
		convertWordsToLines(0, fm);
		
		// Display lines one at a time, vertically centred
		g.setColor(Color.black);
		int total_height = (int)(lines.size()*fm.getAscent() + lines.size()*line_spacing);
		int line_y = getHeight()/2-total_height/2+fm.getAscent();
		for(int i = 0; i < lines.size(); i++){
			int line_width = fm.stringWidth(lines.get(i));
			g.drawString(lines.get(i), getWidth()/2 - line_width/2, line_y);
			line_y += fm.getAscent()+line_spacing;
		}
	}
}
