package Entity;

import java.awt.image.BufferedImage;

public class Object {

	public double x, y;
	public BufferedImage img1, img2, img3, img4;
	public String fase;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
