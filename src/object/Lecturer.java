package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class Lecturer extends SuperObject{
	
	public Lecturer() {
		name = "lecturer";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/lecturer/lecturer1.png"));	
			img2 = ImageIO.read(getClass().getResourceAsStream("/lecturer/lecturer2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/lecturer/lecturer3.png"));	
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Game game){
		if(game.getPlaying().ball.initSpeedX >= 1) {
			game.getPlaying().ball.initSpeedX = (float)(((game.getPlaying().ball.speed/5) * Math.cos(Math.toRadians(45))));
//		}else if(game.getPlaying().ball.initSpeedX < 5) {
//			game.getPlaying().ball.initSpeedX = 1;
//			game.getPlaying().ball.initSpeedY = 1;
		}else if(game.getPlaying().ball.initSpeedX <= 1) {
			game.getPlaying().ball.initSpeedX = 0;
			game.getPlaying().ball.initSpeedY = 1;

		}
	}
}



