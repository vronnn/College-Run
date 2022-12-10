package main;

import java.util.Random;

import object.Book;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		Random rand = new Random();
		
		gp.obj[0] = new Book();
		gp.obj[0].worldX = (rand.nextInt(180 - 5) + 5) * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize + (gp.tileSize/4);
		
		gp.obj[1] = new Book();
		gp.obj[1].worldX = (rand.nextInt(180 - 5) + 5) * gp.tileSize;
		gp.obj[1].worldY = 8 * gp.tileSize + (gp.tileSize/4);
		
		gp.obj[2] = new Book();
		gp.obj[2].worldX = (rand.nextInt(180 - 5) + 5) * gp.tileSize;
		gp.obj[2].worldY = 8 * gp.tileSize + (gp.tileSize/4);
		
		gp.obj[3] = new Book();
		gp.obj[3].worldX = (rand.nextInt(180 - 5) + 5) * gp.tileSize;
		gp.obj[3].worldY = 8 * gp.tileSize + (gp.tileSize/4);
		
		gp.obj[4] = new Book();
		gp.obj[4].worldX = (rand.nextInt(180 - 5) + 5) * gp.tileSize;
		gp.obj[4].worldY = 8 * gp.tileSize + (gp.tileSize/4);
	}
}
