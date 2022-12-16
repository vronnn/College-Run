package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import ui.Fonts;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 60;
	private final int UPS_SET = 60;

	public Playing playing;
	public Menu menu;

	public int defaultTileSize = 16;
	public int SCALE = 3;
	public int TILES_IN_WIDTH = 24;
	public int TILES_IN_HEIGHT = 12;
	public int tileSize = defaultTileSize * SCALE;
	public int GAME_WIDTH = tileSize * TILES_IN_WIDTH;
	public int GAME_HEIGHT = tileSize * TILES_IN_HEIGHT;
	
	public final int maxWorldCol = 200;
	public final int maxWorldRow = 12;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	public long startTime;
	public long timeElapsed;
	
	private void initSetup() {
		menu = new Menu(this);
		playing = new Playing(this);
		playing.aSetter.setObject();
	}
	
	public Game() {
		initSetup();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startTime = System.currentTimeMillis();
		startGameLoop();

	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		switch (Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case QUIT:
		default:
			System.exit(0);
			break;

		}
	}

	public void render(Graphics g) {
		switch (Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:{
			playing.draw(g);
			drawScore(g);
			playing.powerUp.draw(g);
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}
	
	public void drawScore(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.setFont(Fonts.nonpixel.deriveFont(Font.BOLD, 20F));
		g2.drawString("SCORE =  " + (int) (playing.ball.x / 96 - 1), GAME_WIDTH - 160, 40);
	}

	public void windowFocusLost() {
		
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}
}