package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class DancingLecturer extends SuperObject{

	public DancingLecturer() {
		name = "dancingLecturer";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer1.png"));		
			img2 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer3.png"));	
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Game game){
		if(game.getPlaying().ball.initSpeedX > 1) {
			game.getPlaying().ball.initSpeedX = 1;
		}else if(game.getPlaying().ball.initSpeedX <= 1) {
			game.getPlaying().ball.initSpeedX = 0;
		}
		
		if(game.getPlaying().ball.initSpeedY > 0) {
			game.getPlaying().ball.initSpeedY = 1;
		}
	}
}
