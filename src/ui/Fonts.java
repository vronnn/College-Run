package ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import main.Game;

public class Fonts {

	Game game;
	Graphics2D g2;
	public static Font nonpixel;
	public static Font pixel;
	
	public Fonts(Game game) {
		this.game = game;
		getFont();
	}
	
	public void getFont() {
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/Righteous-Regular.ttf");
			nonpixel = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/AGoblinAppears-o2aV.ttf");
			pixel = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
