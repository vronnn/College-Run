package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class Bus extends SuperObject{
	
	public Bus(){
		
		name = "bus";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/bus/Bus1.png"));		
			img2 = ImageIO.read(getClass().getResourceAsStream("/bus/Bus2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/bus/Bus3.png"));	
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Game game){
		game.getPlaying().ball.initSpeedX = (float)(((game.getPlaying().ball.speed + 2) * Math.cos(Math.toRadians(60))));
		game.getPlaying().ball.initSpeedY = (float)(-(game.getPlaying().ball.speed + 2) * (float)Math.sin(Math.toRadians(60)));
	}

}
