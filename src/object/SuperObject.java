package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	BufferedImage img1,img2,img3,image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public void update() {
		spriteCounter++;
		if(spriteCounter > 30) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = (int) (worldX - gp.ball.x + gp.ball.screenX);
		int screenY = worldY;
		
		if(worldX + gp.tileSize >= gp.ball.x - gp.ball.screenX &&
				worldX - gp.tileSize < 2*gp.ball.x + (21*gp.tileSize)) {
			if(spriteNum == 1) {
				image = img1;
			}
			if(spriteNum == 2) {
				image = img2;
			}
			if(spriteNum == 3) {
				image = img3;
			}
			
			g2.drawImage(image, screenX, screenY, 3*gp.tileSize, 3*gp.tileSize, null);
		}
	}
}
