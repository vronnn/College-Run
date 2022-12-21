package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import utilz.LoadSave;

public class DrawScore {

	private int xPos = 1000;
	private int yPos = 40;
	private Playing playing;
	private BufferedImage house;
	private int xH = 10;
	private int yH = 262;
	private int widthH = (int)(173 * 2.5);
	private int heightH = (int)(108 * 2.5);
	
	public DrawScore(Playing playing) {
		this.playing = playing;
		house = LoadSave.GetSpriteAtlas(LoadSave.HOUSE);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		int screenX = (int) (xH - playing.ball.x + playing.ball.screenX);
		int screenY = yH;
		
		if(xH + 450 >= playing.ball.x - playing.ball.screenX &&
				xH - 192 < playing.ball.x + (1008)) {
			g.drawImage(house, screenX, screenY, widthH, heightH, null);
		}
		
		g2.setColor(Color.white);
		g2.setFont(Fonts.nonpixel.deriveFont(Font.BOLD, 20F));
		g2.drawString("SCORE =  " + (int) (playing.ball.x / 96 - 3), xPos, yPos);
	}
}
