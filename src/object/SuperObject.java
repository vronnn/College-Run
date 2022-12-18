package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entity.Ball;
import gamestates.Playing;
import main.Game;
import static utilz.Constants.SpriteImg.*;

public class SuperObject {

	BufferedImage img1,img2,img3,img4,image;
	public String fase;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int powerCount = 0;
	public Rectangle bounds;
	
	public void initBounds(int x, int y) {
		
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void update(Game game) {

	}
	
	public boolean isIn(Ball ball) {
		return false;
	}
	
	
	public void draw(Graphics2D g2, Game game) {

	}
	
	public void power(Playing playing) {
		
	}
}
