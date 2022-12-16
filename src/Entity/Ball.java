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
	public double gravity = 5;
	public int startDegree = 45;
	public int speed = 7;
	public double speedGrav;
	public int counterLimit = 3;
	
	private BufferedImage[] imgs;
	public int index = 2;
	public int PU_WIDTH_DEFAULT = 58;
	public int PU_HEIGHT_DEFAULT = 25;
	public int PU_WIDTH = PU_WIDTH_DEFAULT * 2;
	public int PU_HEIGHT = PU_HEIGHT_DEFAULT * 2;
	public int PU_X = (int) x + 20;
	public int PU_Y = 20;
	
	public Ball(Game game) {
		
		this.game = game;
		
		screenX = 3 * game.tileSize;
		screenY = game.GAME_HEIGHT - 2* game.tileSize;
		
		
//		this.initSpeedX = (float)((speed * Math.cos(Math.toRadians(startDegree)))*5/6);
//		this.initSpeedY = (float)(-speed * (float)Math.sin(Math.toRadians(startDegree))) + ((float)(-speed * (float)Math.sin(Math.toRadians(startDegree)))/3);		
		
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
		
		imgs = new BufferedImage[3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.POWER_UP);
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * PU_WIDTH_DEFAULT, 0, PU_WIDTH_DEFAULT, PU_HEIGHT_DEFAULT);
			
		}
	}
	
	public void update() {
		
		fase = "move";
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
		
		PU_X = (int)(x-screenX) + 20;
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
		
		}
		
		g2.drawImage(image, (int) (x),(int) (y), game.tileSize, game.tileSize, null);
		g2.drawImage(imgs[index], PU_X, PU_Y, PU_WIDTH, PU_HEIGHT, null);
	}
	
}
