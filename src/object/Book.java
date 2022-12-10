package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.security.auth.x500.X500Principal;

import main.Camera;
import main.GamePanel;

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
}
