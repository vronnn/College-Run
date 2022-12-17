package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;
import main.GamePanel;

public class SuperObject {

	BufferedImage img1,img2,img3,image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int powerCount = 0;
	
	public void update(Game game) {
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = worldY;
		
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
		
		if(game.getPlaying().ball.x >= screenX - (game.tileSize*2/3) && game.getPlaying().ball.x <= screenX + (game.tileSize*2/3) && game.getPlaying().ball.y >= screenY - game.tileSize && game.getPlaying().ball.y <= screenY + game.tileSize) {
			collision = true;
			powerCount++;
		}
		if(collision == true && powerCount == 1) {
			power(game);
			collision = false;
		}
	}
	
	
	public void draw(Graphics2D g2, Game game) {
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = worldY;
		
		if(worldX + game.tileSize >= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - game.tileSize < 2*game.getPlaying().ball.x + (21*game.tileSize)) {
			if(spriteNum == 1) {
				image = img1;
			}
			if(spriteNum == 2) {
				image = img2;
			}
			if(spriteNum == 3) {
				image = img3;
			}
			
			g2.drawImage(image, screenX, screenY, 3*game.tileSize, 3*game.tileSize, null);
		}
	}
	
	public void power(Game game) {}
}
