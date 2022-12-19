package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Ball;
import gamestates.Playing;
import main.Game;

import static utilz.Constants.SpriteImg.Batters.*;

public class Batter extends SuperObject{
	
	public int x,y;
	public Rectangle bounds;
	public int powercount = 0;
	
	public void initBounds(int x, int yPos) {
		bounds = new Rectangle(x, yPos - BATTER_SIZE - 5, BATTER_SIZE, BATTER_SIZE + 5);
		this.x = x;
		this.y = yPos - BATTER_SIZE;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	public Batter() {
		fase = "diam";
		name = "batter";
		
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/batter/batter1.png"));	
			img2 = ImageIO.read(getClass().getResourceAsStream("/batter/batter2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/batter/batter3.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Playing playing){
		if(powerCount == 0) {
			System.out.println("kena batter");
			playing.ball.initSpeedX = 1;
			playing.ball.initSpeedY = 0;
			playing.ball.fase = "hit";
			fase = "hit";
			powerCount++;
		}
	}
	
	public boolean isIn(Ball ball) {
		return bounds.contains(ball.getX(),ball.getY());
	}
	
	
	@Override
	public void update(Game game) {
		
		if(worldX + BATTER_SIZE < game.getPlaying().ball.x - game.getPlaying().ball.screenX) {
			drawed = true;
		}
		
		switch (fase) {
		case "hit": {
			spriteCounter++;
			if(spriteCounter > 1) {
				if(spriteNum == 1)
					spriteNum = 2;
				else if(spriteNum == 2)
					spriteNum = 3;
				else if(spriteNum == 3)
					spriteNum = 3;
				spriteCounter = 0;
			}
			break;
		}
		default:
			break;
		}
		
		if(isIn(game.getPlaying().ball)) {;
			this.power(game.getPlaying());
		}
		game.getPlaying().ball.fase = "move";
	}
	
	public void draw(Graphics2D g2, Game game) {
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = game.GAME_HEIGHT - game.tileSize - BATTER_SIZE + 1;
		
		if(worldX + BATTER_SIZE >= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - BATTER_SIZE < game.getPlaying().ball.x + (21*game.tileSize)) {
			switch (fase){
			case "diam": {
				g2.drawImage(img1, screenX, screenY, BATTER_SIZE, BATTER_SIZE, null);
				break;
			}
			case "hit": {
				if(spriteNum == 1) {
					image = img1;
				}
				if(spriteNum == 2) {
					image = img2;
				}
				if(spriteNum == 3) {
					image = img3;
				}
				
				g2.drawImage(image, screenX, screenY, BATTER_SIZE, BATTER_SIZE, null);
				
				break;
			}
			default:
				break;
			}
		}
	}
	
	public void reset() {
		fase = "diam";
		spriteNum = 1;
		spriteCounter = 0;
	}
}
