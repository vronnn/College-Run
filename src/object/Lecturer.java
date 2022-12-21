package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Ball;
import gamestates.Playing;
import main.Game;

import static utilz.Constants.SpriteImg.Batters.BATTER_SIZE;
import static utilz.Constants.SpriteImg.Lecturers.*;

public class Lecturer extends SuperObject{
	
	public int x,y;
	public Rectangle bounds;
	public int powerCount = 0;
	
	public void initBounds(int x, int yPos) {
		bounds = new Rectangle(x - 5, yPos - LECTURER_SIZE -5, LECTURER_SIZE + 5, LECTURER_SIZE + 5);
		this.x = x;
		this.y = yPos - LECTURER_SIZE;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Lecturer() {
		
		fase = "diam";
		name = "lecturer";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/lecturer/lecturer1.png"));	
			img2 = ImageIO.read(getClass().getResourceAsStream("/lecturer/lecturer2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/lecturer/lecturer3.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Playing playing){
		if(powerCount == 0) {
			System.out.println("kena lecturer");
			playing.ball.initSpeedX -= 1;
			playing.ball.initSpeedY += 1;
			playing.ball.fase = "hit";
			fase = "hit";
			powerCount++;
		}
	}
	
	public boolean isIn(Ball ball) {
		return bounds.contains(ball.getX(),ball.getY());
	}
	
	@Override
	public void update(Game game) {	
		
		if(worldX + BATTER_SIZE < game.getPlaying().ball.x - game.getPlaying().ball.screenX) {
			drawed = true;
		}
		
		if(isIn(game.getPlaying().ball)) {;
			this.power(game.getPlaying());
		}
		game.getPlaying().ball.fase = "move";
	}
	
	public void draw(Graphics2D g2, Game game) {
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = game.GAME_HEIGHT - game.tileSize - LECTURER_SIZE;
		
		if(worldX + LECTURER_SIZE >= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - LECTURER_SIZE < game.getPlaying().ball.x + (21*game.tileSize)) {
			switch (fase){
			case "diam": {
				g2.drawImage(img1, screenX, screenY, LECTURER_SIZE, LECTURER_SIZE, null);
				break;
			}
			case "hit": {
				g2.drawImage(img3, screenX, screenY, LECTURER_SIZE, LECTURER_SIZE, null);
				break;
			}
			default:
				break;
			}
		}
	}
	
	public void reset() {
		powerCount = 0;
		fase = "diam";
	}
}



