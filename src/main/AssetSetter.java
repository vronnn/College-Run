package main;

import java.util.Random;

import object.*;

public class AssetSetter {

	Game game;
	
	public AssetSetter(Game game) {
		this.game = game;
	}
	
	public void setObject() {
		
		for(int n = 1; n < 3; n++) {
			objectShape(n);
			game.playing.obj[n].worldX = n * 50 * game.tileSize;
			game.playing.obj[n].worldY = 8 * game.tileSize + (game.tileSize/4);
		}
	}
	
	public void objectShape(int n) {
		Random rand = new Random();
		int m = rand.nextInt(4);
		if(m == 0) {
			game.playing.obj[n] = new Book();
		}else if(m == 1) {
			game.playing.obj[n] = new Lecturer();
		}else if(m == 2) {
			game.playing.obj[n] = new Wall();
		}else if(m == 3) {
			game.playing.obj[n] = new Bus();
		}
	}
}
