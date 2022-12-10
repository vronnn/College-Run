package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Ball extends Object{

	GamePanel gp;
	
	public final int screenX;
	public final int screenY;
	public double lastX, lastY;
	public double initSpeedX, initSpeedY;
	public double gravity = 5;
	public int startDegree = 45;
	public int speed = 7;
	public double speedGrav;
	public int counterLimit = 3;
	
	
	public Ball(GamePanel gp) {
		
		this.gp = gp;
		
		screenX = 3 * gp.tileSize;
		screenY = gp.screenHeight - 2* gp.tileSize;
		
		
		this.initSpeedX = (float)((speed * Math.cos(Math.toRadians(startDegree)))*5/6);
		this.initSpeedY = (float)(-speed * (float)Math.sin(Math.toRadians(startDegree))) + ((float)(-speed * (float)Math.sin(Math.toRadians(startDegree)))/3);		
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		this.x = 3 * gp.tileSize;
		this.y = gp.screenHeight - 2* gp.tileSize;
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
		
		
		int limitY = gp.screenHeight - 2*gp.tileSize;
		
		x += initSpeedX;
		speedGrav = (gravity*((double)(gp.timeElapsed*gp.timeElapsed)/1000000)*0.5);
		y += initSpeedY + speedGrav;
		
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
		
		g2.drawImage(image, (int) (x),(int) (y), gp.tileSize, gp.tileSize, null);
		
	}
	
}
