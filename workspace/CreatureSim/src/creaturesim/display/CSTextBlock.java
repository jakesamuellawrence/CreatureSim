package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

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
	
	Font font = new Font("Arial", Font.BOLD, 14);
	
	String text;
	String[] words;
	ArrayList<String> lines;
	
	int line_spacing = 2;
	int margin = 5;
	
	public CSTextBlock(String text){
		this.text = text;
		this.setOpaque(true);
	}
	
	public void convertTextToWords(){
		words = text.split(" ");
	}
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
	
	@Override
	public void paintComponent(Graphics g){
		// Set Font
		this.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		
		// Break text into lines
		lines = new ArrayList<String>();		
		convertTextToWords();
		convertWordsToLines(0, fm);
		
		System.out.println(lines.size());
		
		// Display lines one at a time, vertically centred
		int total_height = (int)(lines.size()*fm.getAscent() + lines.size()*line_spacing);
		int line_y = getHeight()/2-total_height/2+fm.getAscent();
		for(int i = 0; i < lines.size(); i++){
			g.drawString(lines.get(i), margin, line_y);
			line_y += fm.getAscent()+line_spacing;
		}
		
		// Draw outline
//		g.setColor(Color.white);
//		g.drawRect(0, 0, getWidth()-1, getHeight()-1); 
	}
}
