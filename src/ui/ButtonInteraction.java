package ui;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface ButtonInteraction {

	public void initBounds();
	public void loadImgs();
	public void draw(Graphics g);
	public void update();
	public boolean isMouseOver();
	public void setMouseOver(boolean mouseOver);
	public boolean isMousePressed();
	public void setMousePressed(boolean mousePressed);
	public Rectangle getBounds();
	public void applyGamestate();
	public void resetBools();
}
