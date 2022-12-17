package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class Book extends SuperObject{
	
	public Book() {
		
		name = "book";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/book/books1.png"));		
			img2 = ImageIO.read(getClass().getResourceAsStream("/book/books2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/book/books3.png"));	
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Game game){
		if(game.getPlaying().powerUp.index < 3) {
			game.getPlaying().powerUp.index++;
		}
	}
}
