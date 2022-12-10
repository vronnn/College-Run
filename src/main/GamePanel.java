package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import Entity.Ball;
import object.Book;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48X48 tile;
	public final int maxScreenCol = 24;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //1152 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	public final int maxWorldCol = 200;
	public final int maxWorldRow = 12;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	int FPS = 90;
	
	public long startTime;
	public long timeElapsed;
	public long timetemp;
	
	public boolean start = false;
	
	public Camera cam;
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	public AssetSetter aSetter = new AssetSetter(this);
	public Ball ball = new Ball(this);
	KeyHandler keyH = new KeyHandler(this);
	MouseHandler mouseH = new MouseHandler(ball, this);
	public SuperObject obj[] = new SuperObject[10];
	
	public GamePanel() {
		cam = new Camera(0, 0, this, ball);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);;
		this.addMouseListener(mouseH);
	}
	
	public void setupGame() {
		aSetter.setObject();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		startTime = System.currentTimeMillis();
		gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();			
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				//System.out.println("FPS:"+ drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
//	public void run() {
//		while(true) {
//			//System.out.println(System.currentTimeMillis());
//			update();
//			repaint();
//			try {
//				Thread.sleep(1000 / FPS);
//			} catch (InterruptedException ex) {}
//		}
//		
//	}
	
	public void update() {
		
		if(start) {
			this.timeElapsed = System.currentTimeMillis() - this.startTime;
			ball.update();
			cam.update();
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].update();
				}
			}
		}
		else {
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.translate(cam.getX(), cam.getY());
		
		tileM.draw(g2);
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		ball.draw(g2);
		
		g2.translate(-cam.getX(), -cam.getY());
		
		g2.dispose();
	}
	
}
