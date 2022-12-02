package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Ball extends Object{

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public double initSpeedX, initSpeedY;
	private int radius;
	private Color color;
	public double gravity = 5;
	public int startDegree = 45;
	public int speed = 6;
	public double speedGrav;
	public int counterLimit = 3;
	
	
	public Ball(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = 3 * gp.tileSize;
		screenY = gp.screenHeight - 2* gp.tileSize;
		
		
		this.initSpeedX = (float)((speed * Math.cos(Math.toRadians(startDegree)))*3/4);
		this.initSpeedY = (float)(-speed * (float)Math.sin(Math.toRadians(startDegree))) + ((float)(-speed * (float)Math.sin(Math.toRadians(startDegree)))/2);		
		
		setDefaultValues();
		getPlayerImage();
		
		
	}
	
	public void setDefaultValues() {
		
		this.x = 3 * gp.tileSize;
		this.y = gp.screenHeight - gp.tileSize;
		//this.y = gp.worldHeight - (gp.worldHeight/4);
		
		this.radius = gp.tileSize - 16;
		fase = "diam";
	}
	
	public void getPlayerImage(){
		
		try {
			
			ball1 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody1.png"));			
			ball2 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody2.png"));			
			ball3 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody3.png"));			
			ball4 = ImageIO.read(getClass().getResourceAsStream("/ball/headbody4.png"));			

			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(GamePanel gp) {
		
		fase = "diam";
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
		
		
		int limitY = gp.screenHeight - gp.tileSize;
		
		x += initSpeedX;
		speedGrav = (gravity*((double)(gp.timeElapsed*gp.timeElapsed)/1000000)*0.5);
		y += initSpeedY + speedGrav;
		
//		System.out.println(initSpeedX + " " + initSpeedY);
//		lastSpeedY = initSpeedY - (gravity * ((double)(gp.timeElapsed/1000)));
		
		if(y > limitY) {
			y = limitY;
			gp.startTime += gp.timeElapsed;
			gp.timeElapsed = 1000;
			initSpeedY = initSpeedY * 8 / 10;
			initSpeedX = initSpeedX * 8 / 10;
			counterLimit++;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(fase){
		case "diam":
			if(spriteNum == 1) {
				image = ball1;
			}
			if(spriteNum == 2) {
				image = ball2;
			}
			if(spriteNum == 3) {
				image = ball3;
			}
			if(spriteNum == 4) {
				image = ball4;
			}
			break;
		
		}
		
		g2.drawImage(image, (int) (x),(int) (y - gp.tileSize), gp.tileSize, gp.tileSize, null);
		
	}
	
}
