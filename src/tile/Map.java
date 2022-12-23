package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Game;

public class Map {

	Game game;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public Map(Game game) {
		
		this.game = game;
		
		tile = new Tile[25];
		mapTileNum = new int[game.maxWorldCol][game.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/collegerun.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sky.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree2.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree3.png"));
		
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree4.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/land.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/end_land.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_line.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/end_area.png"));
			
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud1.png"));
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud2.png"));
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud3.png"));
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud4.png"));
			
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud5.png"));
			
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud6.png"));
			
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud7.png"));
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud8.png"));
			
			tile[21] = new Tile();
			tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud9.png"));
			
			tile[22] = new Tile();
			tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud10.png"));
			
			tile[23] = new Tile();
			tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud11.png"));
			
			tile[24] = new Tile();
			tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud12.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
				
			while(col < game.maxWorldCol && row < game.maxWorldRow) {
				String lineString;
				lineString = br.readLine();
				while(col < game.maxWorldCol) {
					String numbers[] = lineString.split(" ");
					int num = Integer.parseInt(numbers[col]);
				
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == game.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		
		while(col < game.maxWorldCol && row < game.maxWorldRow) {
			
			int tileNum = mapTileNum[col][row];
			
			int worldX = col * game.tileSize;
			int screenX = (int) (worldX - game.getPlaying().ball.x + game.getPlaying().ball.screenX);
			int screenY = row * game.tileSize;
			
			if(worldX + 2*game.tileSize >= game.getPlaying().ball.x - game.getPlaying().ball.screenX &&
					worldX - 2*game.tileSize < game.getPlaying().ball.x + (21*game.tileSize)) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, game.tileSize, game.tileSize, null);
			}
			
			col++;

			if(col == game.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
}
