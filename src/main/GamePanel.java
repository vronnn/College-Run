package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;


public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;

	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(game.GAME_WIDTH, game.GAME_HEIGHT);
		setPreferredSize(size);
	}

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}

}

//package main;
//
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//
//import javax.swing.JPanel;
//import gamestates.Gamestate;
//import gamestates.Menu;
//import gamestates.Playing;
//
//public class GamePanel extends JPanel implements Runnable{
//	
//	final int originalTileSize = 16;
//	final int scale = 3;
//	
//	public final int tileSize = originalTileSize * scale; //48X48 tile;
//	public final int maxScreenCol = 24;
//	public final int maxScreenRow = 12;
//	public final int screenWidth = tileSize * maxScreenCol; //1152 pixels
//	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
//	
//	public final int maxWorldCol = 200;
//	public final int maxWorldRow = 12;
//	public final int worldWidth = tileSize * maxWorldCol;
//	public final int worldHeight = tileSize * maxWorldRow;
//
//	int FPS = 90;
//	
//	public long startTime;
//	public long timeElapsed;
//	public long timetemp;
//	
//	public boolean start = false;
//	
//	private Playing playing;
//	private Menu menu;
//	
//	Thread gameThread;
//	KeyHandler keyH;
//	MouseHandler mouseH;
//	
//	public GamePanel() {
//		keyH = new KeyHandler(this);
//		mouseH = new MouseHandler(this);
//		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//		this.setDoubleBuffered(true);
//		this.addKeyListener(keyH);
//		this.setFocusable(true);;
//		this.addMouseListener(mouseH);
//		this.addMouseMotionListener(mouseH);
//	}
//	
//	public void setupGame() {
//		menu = new Menu(this);
//		playing = new Playing(this);
//	}
//	
//	public void startGameThread() {
//		
//		gameThread = new Thread(this);
//		startTime = System.currentTimeMillis();
//		gameThread.start();
//	}
//	
//	public void run() {
//		
//		double drawInterval = 1000000000 / FPS;
//		double delta = 0;
//		long lastTime = System.nanoTime();
//		long currentTime;
//		long timer = 0;
//		int drawCount = 0;
//		
//		while(gameThread != null) {
//			
//			currentTime = System.nanoTime();
//			delta += (currentTime - lastTime) / drawInterval;
//			timer += (currentTime - lastTime);
//			lastTime = currentTime;
//			
//			if(delta >= 1) {
//				update();			
//				repaint();
//				delta--;
//				drawCount++;
//			}
//			
//			if(timer >= 1000000000) {
//				//System.out.println("FPS:"+ drawCount);
//				drawCount = 0;
//				timer = 0;
//			}
//			
//		}
//	}
//	
//	public void update() {
//		
//		switch (Gamestate.state) {
//		case MENU:
//			menu.update();
//			break;
//		case PLAYING:
//			playing.update();
//			break;
//		case OPTIONS:
//		case QUIT:
//		default:
//			System.exit(0);
//			break;
//		
//		}
//	}
//	
//	public void paintComponent(Graphics g) {
//		
//		super.paintComponent(g);
//		switch (Gamestate.state) {
//		case MENU:
//			menu.draw(g);
//			break;
//		case PLAYING:
//			playing.draw(g);
//			break;
//		default:
//			break;
//		}
//	}
//
//	public Menu getMenu() {
//		return menu;
//	}
//	public Playing getPlaying() {
//		return playing;
//	}
//}
