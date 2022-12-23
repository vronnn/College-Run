package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import utilz.LoadSave;

public class Ending {

	private Playing playing;
	private int x = 38190;
	private int y = 0;
	private int width = (int)(54*2);
	private int height = (int)(286*2);
	private BufferedImage img;
	
	public Ending(Playing playing) {
		this.playing = playing;
		img = LoadSave.GetSpriteAtlas(LoadSave.ENDING_LIGHT);
	}
	
	public void draw(Graphics g) {
		int screenX = (int) (x - playing.ball.x + playing.ball.screenX);
		int screenY = y;
		
		if(x + 568 >= playing.ball.x - playing.ball.screenX &&
				x - 568 < playing.ball.x + (1008)) {
			g.drawImage(img, screenX, screenY, width, height, null);
		}
	}
}
