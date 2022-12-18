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
	
	public Rectangle bounds;
	public int powerCount = 0;
	public int moveCounter = 0;
	public boolean hit;
	
	@Override
	public void initBounds(int x, int yPos) {
		bounds = new Rectangle(x, yPos - BUS_HEIGHT, BUS_WIDTH, BUS_HEIGHT);
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
			if(playing.ball.initSpeedY > -6 && playing.ball.initSpeedX < 6) {
				System.out.println("kena bus");
				playing.ball.initSpeedX += 5;
				playing.ball.initSpeedY -= 5;
				playing.ball.fase = "hit";
				powerCount++;
			}
		}
	//		playing.ball.initSpeedX = (float)(((playing.ball.speed + 2) * Math.cos(Math.toRadians(60))));
	//		playing.ball.initSpeedY = (float)(-(playing.ball.speed + 2) * (float)Math.sin(Math.toRadians(60)));
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
		leftAnimation();
		
		if(isIn(game.getPlaying().ball)) {;
			this.power(game.getPlaying());
			hit = true;
		}
		game.getPlaying().ball.fase = "move";
	}
	
	@Override
	public void draw(Graphics2D g2, Game game) {
		//System.out.println("gambar bus");
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = game.GAME_HEIGHT - 45 - BUS_HEIGHT;
		
		if(worldX + 4*game.tileSize>= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - 4*game.tileSize < game.getPlaying().ball.x + (21*game.tileSize)) {
			switch (fase){
			case "diam": {
				g2.drawImage(img1, screenX, screenY, BUS_WIDTH, BUS_HEIGHT, null);
				//g2.drawRect(screenX, screenY, BUS_WIDTH, BUS_HEIGHT);
			}
			case "hit": {
				g2.drawImage(img2, screenX, screenY, BUS_WIDTH, BUS_HEIGHT, null);
			}
			default:
				break;
			}
		}
	}
}
