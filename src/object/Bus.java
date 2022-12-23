package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Ball;
import gamestates.Playing;
import main.Game;
import static utilz.Constants.SpriteImg.Buses.*;

public class Bus extends SuperObject{
	
	public int x, y;
	public int xb,yb;
	public Rectangle bounds;
	public int powerCount = 0;
	public boolean hit;
	
	@Override
	public void initBounds(int x, int yPos) {
		bounds = new Rectangle(x - 45, yPos - BUS_HEIGHT - 20, BUS_WIDTH + 45, BUS_HEIGHT - 20);
		this.x = x;
		this.y = yPos - BUS_HEIGHT - 3; 
		this.xb = x;
		this.yb = y;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Bus(){
		
		fase = "diam";
		name = "bus";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/bus/bigbus1.png"));		
			img2 = ImageIO.read(getClass().getResourceAsStream("/bus/bigbus2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/bus/bigbus3.png"));		
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Playing playing){
		if(powerCount == 0) {
			if(playing.ball.initSpeedY > -7 && playing.ball.initSpeedX < 7) {
				System.out.println("kena bus");
				playing.ball.initSpeedX += 5;
				playing.ball.initSpeedY -= 5;
				playing.ball.fase = "hit";
				playing.ball.counterLimit--;
				fase = "hit";
				powerCount++;
			}
		}
	}
	
	public boolean isIn(Ball ball) {
		return bounds.contains(ball.getX(),ball.getY());
	}
	
	public void leftAnimation() {
		if(hit) {
			worldX += 15;
		}
	}
	
	@Override
	public void update(Game game) {	
		
		if(worldX + BUS_WIDTH < game.getPlaying().ball.x - game.getPlaying().ball.screenX) {
			drawed = true;
		}
		
		if(hit && worldX - BUS_WIDTH > game.getPlaying().ball.x + (21*game.tileSize)) {
			drawed = true;
			hit = false;
		}
		
		leftAnimation();
		
		if(isIn(game.getPlaying().ball)) {;
			this.power(game.getPlaying());
			hit = true;
		}
		game.getPlaying().ball.fase = "move";
	}
	
	@Override
	public void draw(Graphics2D g2, Game game) {
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = game.GAME_HEIGHT - 45 - BUS_HEIGHT;
		
		if(worldX + BUS_WIDTH >= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - BUS_WIDTH < game.getPlaying().ball.x + (21*game.tileSize)) {
			switch (fase){
			case "diam": {
				g2.drawImage(img1, screenX, screenY, BUS_WIDTH, BUS_HEIGHT, null);
				break;
			}
			case "hit": {
				g2.drawImage(img2, screenX, screenY, BUS_WIDTH, BUS_HEIGHT, null);
			}
			default:
				break;
			}
		}
	}
	
	public void reset() {
		fase = "diam";
		powerCount = 0;
		hit = false;
	}
}
