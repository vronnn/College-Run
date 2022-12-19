package object;

import static utilz.Constants.SpriteImg.Buses.BUS_HEIGHT;
import static utilz.Constants.SpriteImg.Buses.BUS_WIDTH;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import gamestates.Playing;
import main.Game;

public class DancingLecturer extends SuperObject{

	public Rectangle bounds;
	public int powerCount = 0;
	
	public void initBounds(int x, int y) {
		bounds = new Rectangle(x, y, BUS_WIDTH, BUS_HEIGHT);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public DancingLecturer() {
		
		fase = "diam";
		name = "dancingLecturer";
		try {
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer1.png"));		
			img2 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer2.png"));			
			img3 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer3.png"));	
			img4 = ImageIO.read(getClass().getResourceAsStream("/dancingLecturer/dancingLecturer4.png"));	
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void power(Playing playing){
		if(powerCount == 0) {
			System.out.println("kena dl");
			playing.ball.initSpeedX = 1;
			playing.ball.initSpeedY = 0;
			playing.ball.fase = "hit";
			powerCount++;
		}
//		if(game.getPlaying().ball.initSpeedX > 1) {
//			game.getPlaying().ball.initSpeedX = 1;
//		}else if(game.getPlaying().ball.initSpeedX <= 1) {
//			game.getPlaying().ball.initSpeedX = 0;
//		}
//		
//		if(game.getPlaying().ball.initSpeedY > 0) {
//			game.getPlaying().ball.initSpeedY = 1;
//		}
	}
	
	public void draw(Graphics2D g2, Game game) {
		//System.out.println("gambar lecturer");
		int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
		int screenY = game.GAME_HEIGHT - game.tileSize - BUS_HEIGHT;
		
		if(worldX + 4*game.tileSize>= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
				worldX - 4*game.tileSize < game.getPlaying().ball.x + (21*game.tileSize)) {
			switch (fase){
			case "diam": {
				g2.drawImage(img1, screenX, screenY, BUS_WIDTH, BUS_HEIGHT, null);
			}
			case "hit": {
				g2.drawImage(img2, screenX, screenY, BUS_WIDTH, BUS_HEIGHT, null);
			}
			default:
				break;
			}
		}
	}
	
	public void reset() {
		
	}
}
