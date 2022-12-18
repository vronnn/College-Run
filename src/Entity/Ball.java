package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import utilz.LoadSave;

public class Ball extends Object{

	Game game;
	
	public final int screenX;
	public final int screenY;
	public double lastX, lastY;
	public double initSpeedX, initSpeedY;
	public double lastSpeedX, lastSpeedY;
	public double gravity = 5;
	public int startDegree = 45;
	public int speed = 7;
	public double speedGrav;
	public int counterLimit = 3;
	
	public Ball(Game game) {
		
		this.game = game;
		
		screenX = 3 * game.tileSize;
		screenY = game.GAME_HEIGHT - 2* game.tileSize;	
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		this.x = 3 * game.tileSize;
		this.y = game.GAME_HEIGHT - 2* game.tileSize;
		fase = "move";
	}
	
	public void getPlayerImage(){
		
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody1.png"));			
			img2 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody3.png"));			
			img4 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody4.png"));			

			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if(x > 8730 && game.getPlaying().gameFinished == false) {
			game.getPlaying().setGameFinished(true);
			return;
		}
		if(initSpeedX <= 0.001 && game.getPlaying().gameOver == false && game.getPlaying().playState == 1) {
			game.getPlaying().setGameOver(true);
			return;
		}
		spriteCounter++;
		if(spriteCounter > counterLimit) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 4;
			}
			else if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		
		int limitY = game.GAME_HEIGHT - 2*game.tileSize;
		
		x += initSpeedX;
		speedGrav = (gravity*((double)(game.timeElapsed*game.timeElapsed)/1000000)*0.5);
		y += initSpeedY + speedGrav;
	
		
		if(y > limitY) {
			y = limitY;
			game.startTime += game.timeElapsed;
			game.timeElapsed = 1000;
			initSpeedY = initSpeedY * 8 / 10;
			initSpeedX = initSpeedX * 8 / 10;
			counterLimit++;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(fase){
		case "move":
			if(spriteNum == 1) {
				image = img1;
			}
			if(spriteNum == 2) {
				image = img2;
			}
			if(spriteNum == 3) {
				image = img3;
			}
			if(spriteNum == 4) {
				image = img4;
			}
			break;
		case "hit":
			if(spriteNum == 1) {
				image = img1;
			}
			if(spriteNum == 2) {
				image = img1;
			}
			if(spriteNum == 3) {
				image = img1;
			}
			if(spriteNum == 4) {
				image = img1;
			}
			break;
		
		}
		
		g2.drawImage(image, (int) (x),(int) (y), game.tileSize, game.tileSize, null);
	}

	public void resetAll() {
		spriteNum = 1;
		setDefaultValues();
		getPlayerImage();
	}
	
}
