package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import utilz.LoadSave;

public class Building {

	private int x,y;
	private Playing playing;
	private BufferedImage img;
	
	public Building(int x, Playing playing) {
		this.x = x;
		this.y = -170;
		this.playing = playing;
		img = LoadSave.GetSpriteAtlas(LoadSave.BUILDING);
	}
	
	public void draw(Graphics g) {
//		int screenX = (int) (x - playing.ball.x + playing.ball.screenX);
//		int screenY = y;
//		
//		if(x + BUILDING_SIZE >= playing.ball.x - playing.ball.screenX &&
//				x - BUILDING_SIZE < playing.ball.x + (1008)) {
//			g.drawImage(img, screenX, screenY, BUILDING_SIZE, BUILDING_SIZE, null);
//		}
	}
}
