package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.RHButtons.*;

public class RhButton {

	private BufferedImage[] imgs;
	int x, y, width, height, rowIndex, index;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;
	
	public RhButton(int x, int y, int width, int height, int rowIndex) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rowIndex = rowIndex;
		loadImgs();
		initBounds();
	}
	
	private void initBounds() {
		bounds = new Rectangle(x, y, RHB_SIZE, RHB_SIZE);
	}
	
	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.RH_BUTTONS);
		imgs = new BufferedImage[3];
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * RHB_DEFAULT_SIZE, rowIndex * RHB_DEFAULT_SIZE, RHB_DEFAULT_SIZE, RHB_DEFAULT_SIZE);
		}
	}

	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, RHB_SIZE, RHB_SIZE, null);
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
}
