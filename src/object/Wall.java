package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class Wall extends SuperObject{
	
	public Wall() {
		
		name = "wall";
//		try {
//			
//			img1 = ImageIO.read(getClass().getResourceAsStream("/wall/wall1.png"));		
//			img2 = ImageIO.read(getClass().getResourceAsStream("/wall/wall2.png"));			
//			img3 = ImageIO.read(getClass().getResourceAsStream("/wall/wall3.png"));	
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public void power(Game game){
		if(game.getPlaying().ball.initSpeedX > 1) {
			game.getPlaying().ball.initSpeedX = 1;
		}else if(game.getPlaying().ball.initSpeedX <= 1) {
			game.getPlaying().ball.initSpeedX = 0;
		}
		
		if(game.getPlaying().ball.initSpeedY > 1) {
			game.getPlaying().ball.initSpeedY = 1;
		}else if(game.getPlaying().ball.initSpeedY <= 1) {
			game.getPlaying().ball.initSpeedY = 1;
		}
	}

}
