package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Ball;
import gamestates.Playing;
import main.Game;
import static utilz.Constants.SpriteImg.Books.*;

public class Book extends SuperObject{
	
	public int x,y;
	public Rectangle bounds;
	public int powerCount = 0;
	
	@Override
	public void initBounds(int x, int yPos) {
		bounds = new Rectangle(x, yPos - BOOK_HEIGHT, BOOK_WIDTH, BOOK_HEIGHT);
		this.x = x;
		this.y = yPos - BOOK_HEIGHT + 5;
	}
	
	@Override
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Book() {
	
		fase = "diam";
		name = "book";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/book/books1.png"));		
			img2 = ImageIO.read(getClass().getResourceAsStream("/book/books2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/book/books3.png"));		
			img4 = ImageIO.read(getClass().getResourceAsStream("/book/books4.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Playing playing){
		if(playing.powerUp.index < 3 && powerCount == 0) {
			System.out.println("kena book");
			playing.powerUp.index++;
			playing.ball.fase = "hit";
			fase = "hit";
			powerCount++;
		}
	}
	
	public boolean isIn(Ball ball) {
		return bounds.contains(ball.getX(),ball.getY());
	}
	
	public void update(Game game) {	
		
		if(worldX + BOOK_WIDTH < game.getPlaying().ball.x - game.getPlaying().ball.screenX) {
			drawed = true;
		}
		
		if(isIn(game.getPlaying().ball)) {
			this.power(game.getPlaying());
		}
		game.getPlaying().ball.fase = "move";
	}
	
	@Override
	public void draw(Graphics2D g2, Game game) {
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = game.GAME_HEIGHT - game.tileSize - BOOK_HEIGHT + 5;
		
		if(worldX + BOOK_WIDTH >= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - BOOK_WIDTH < game.getPlaying().ball.x + (21*game.tileSize)) {
			switch (fase){
			case "diam": {
				g2.drawImage(img1, screenX, screenY, BOOK_WIDTH, BOOK_HEIGHT, null);
				break;
			}
			case "hit": {
				g2.drawImage(img4, screenX, screenY, BOOK_WIDTH, BOOK_HEIGHT, null);
				break;
			}
			default:
				break;
			}
		}
	}
	
	public void reset() {
		fase = "diam";
	}
}
