package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Custom Panel class for displaying a title and smaller subtitle below it
 * 
 * @author jakesamuellawrence
 */
public class CSTitle extends JPanel{
	
	int preferred_x;
	int preferred_y;
	int margin = 0;
	int subtitle_offset = 10;
	int title_size = 95;
	int subtitle_size = 35;
	
	Font title_font;
	Font subtitle_font;
	
	String title;
	String subtitle;
	
	/**
	 * Constructor for CSTitle. Sets the title variable to the passed in string,
	 * and the subtitle variable to the other passed in String.
	 * 
	 * @param title String. The large title to be displayed
	 * @param subtitle String. The smaller subtitle to be displayed
	 */
	public CSTitle(String title, String subtitle){
		this.title = title;
		this.subtitle = subtitle;
		
		title_font = new Font("Arial", Font.BOLD, title_size);
		subtitle_font = new Font("Arial", Font.BOLD, subtitle_size);
		
		this.setOpaque(false);
	}
	
	/**
	 * Overridden paintComponent method from JPanel. Is called when repaint() is called 
	 * 
	 * Draws a large title and a smaller subtitle below
	 * 
	 * @param g Graphics Graphics context that gets automatically passed in by AWT.
	 */
	@Override
	public void paintComponent(Graphics g){
		// Draw title
		g.setFont(title_font);
		FontMetrics fm = g.getFontMetrics();
		int line_bottom = margin + fm.getAscent();
		g.setColor(Color.decode("#f2a805"));
		g.drawString(title, margin, line_bottom);
		
		// Draw subtitle
		g.setFont(subtitle_font);
		fm = g.getFontMetrics();
		line_bottom += fm.getAscent();
		g.drawString(subtitle, margin+subtitle_offset, line_bottom);
	}
}
