package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gamestates.Playing;

public class DrawScore {

	private int xPos, yPos;
	private Playing playing;
	
	public DrawScore(int xPos, int yPos, Playing playing) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.playing = playing;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.setFont(Fonts.nonpixel.deriveFont(Font.BOLD, 20F));
		g2.drawString("SCORE =  " + (int) (playing.ball.x / 96 - 1), xPos, yPos);
	}
}
