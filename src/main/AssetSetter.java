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
		
		//Kalau Map nya udah seribu nanti MAX N nya ubah jadi 100
		for(int n = 1; n < 10; n++) {
			objectShape(n);
			//game.getPlaying().obj[n] = new Book();
			game.getPlaying().obj[n].worldX = (n * 32 * 100) + 150;
			game.getPlaying().obj[n].initBounds(game.getPlaying().obj[n].worldX, game.GAME_HEIGHT - game.tileSize);
		}
	}
	
	public void objectShape(int n) {
		int m = rand.nextInt(4);
		if(m == 0) {
			game.getPlaying().obj[n] = new Book();
		}else if(m == 1) {
			game.getPlaying().obj[n] = new Lecturer();
		}else if(m == 2) {
			game.getPlaying().obj[n] = new DancingLecturer();
		}else if(m == 3) {
			game.getPlaying().obj[n] = new Bus();
		}
	}
}
