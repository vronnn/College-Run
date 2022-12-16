package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.PowerUps.*;

public class PowerUp {

	private int xPos, yPos;
	public int index = 3;
	private BufferedImage[] imgs;

	public PowerUp(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		loadImgs();
	}
	

	private void loadImgs() {
		imgs = new BufferedImage[4];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.POWER_UP);
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * PU_WIDTH_DEFAULT, 0, PU_WIDTH_DEFAULT, PU_HEIGHT_DEFAULT);
			
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], xPos , yPos, PU_WIDTH, PU_HEIGHT, null);
	}
	
	public void update() {
		if(index > 0)
			index--;
	}
}
