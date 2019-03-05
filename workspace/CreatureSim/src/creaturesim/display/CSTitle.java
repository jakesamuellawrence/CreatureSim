package creaturesim.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CSTitle extends JPanel{
	int preferred_x;
	int preferred_y;
	int margin = 0;
	int subtitle_offset = 10;
	int title_size = 18;
	int subtitle_size = 8;
	
	Font title_font;
	Font subtitle_font;
	
	String title;
	String subtitle;
	
	public CSTitle(String title, String subtitle){
		this.title = title;
		this.subtitle = subtitle;
		
		title_font = new Font("Arial", Font.BOLD, 80);
		subtitle_font = new Font("Arial", Font.BOLD, 30);
		
		this.setOpaque(false);
	}
	
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
		System.out.println("BY JAKE LAWRENCE");
		
		// Draw outline
//		g.setColor(Color.white);
//		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
}
