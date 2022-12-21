package main;

import java.util.Random;
import object.*;

public class AssetSetter {

	Game game;
	Random rand = new Random();
	
	public AssetSetter(Game game) {
		this.game = game;
	}
	
	public void setObject() {
		
		for(int n = 1; n < 10; n++) {
			
			addObject(n);
//			game.getPlaying().obj[n] = new Batter();
			game.getPlaying().obj[n].worldX = (n * 16 * 100);
			game.getPlaying().obj[n].initBounds(game.getPlaying().obj[n].worldX, game.GAME_HEIGHT - game.tileSize);
		}
	}
	
	public void addObject(int n) {
		int m = rand.nextInt(4);
		if(m == 0) {
			game.getPlaying().obj[n] = new Book();
		}else if(m == 1) {
			game.getPlaying().obj[n] = new Batter();
		}else if(m == 2) {
			game.getPlaying().obj[n] = new Lecturer();
		}else if(m == 3) {
			game.getPlaying().obj[n] = new Bus();
		}
	}
	
	public void resetObject(SuperObject so, int i) {
//		so.worldX = (int) (game.getPlaying().ball.x + (i * 1500));
		so.worldX = (int) (game.getPlaying().ball.x + ((i + 9) * 16 * 100));
		so.initBounds(so.worldX, game.GAME_HEIGHT - game.tileSize);
		so.powerCount = 0;
		so.fase = "diam";
	}
	
	public void reset() {
		setObject();
	}
}
