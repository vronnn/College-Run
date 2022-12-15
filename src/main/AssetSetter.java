package main;

import java.util.Random;

import object.Book;

public class AssetSetter {

	Game game;
	
	public AssetSetter(Game game) {
		this.game = game;
	}
	
	public void setObject() {
		
		Random rand = new Random();
		
		game.playing.obj[0] = new Book();
		game.playing.obj[0].worldX = (rand.nextInt(180 - 5) + 5) * game.tileSize;
		game.playing.obj[0].worldY = 8 * game.tileSize + (game.tileSize/4);
	}
}
