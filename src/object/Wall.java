package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import gamestates.Playing;
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
	
	public void power(Playing playing){
		if(powerCount == 0) {
			System.out.println("kena lecturer");
			playing.ball.initSpeedX -= 1;
			playing.ball.initSpeedY += 1;
			playing.ball.fase = "hit";
			powerCount++;
		}
	}

}
