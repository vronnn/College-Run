package ui;

import static utilz.Constants.UI.LaunchButtons.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;

public class LaunchButton {

	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = LB_WIDTH / 2;
	private Gamestate state;
	private BufferedImage[] imgs;
	public boolean mouseOver, mousePressed;
	private Rectangle bounds;
	
	public LaunchButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state; 
		loadImgs();
		initBounds();
	}
	
	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, LB_WIDTH, LB_HEIGHT);
	}
	
	private void loadImgs() {
		imgs = new BufferedImage[3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.LAUNCH_BUTTONS);
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * LB_WIDTH_DEFAULT, rowIndex * LB_HEIGHT_DEFAULT, LB_WIDTH_DEFAULT, LB_HEIGHT_DEFAULT);
			
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, LB_WIDTH, LB_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
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
	
	public void applyGamestate() {
		Gamestate.state = state;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
}
