package gamestates;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entity.Ball;
import main.AssetSetter;
import main.Camera;
import main.Game;
import object.SuperObject;
import tile.Map;
import ui.DrawScore;
import ui.Fonts;
import ui.GameFinishedOverlay;
import ui.GameOverOverlay;
import ui.LaunchButton;
import ui.PowerBar;
import ui.PowerUp;

public class Playing extends State implements Statemethods{

	public Camera cam;
	public Ball ball;
	public Map gameMap;
	public GameOverOverlay gameOverOverlay;
	public GameFinishedOverlay gameFinishedOverlay;
	public AssetSetter aSetter = new AssetSetter(game);
	public SuperObject[] obj = new SuperObject[10];
	public LaunchButton launchButton;
	public PowerBar powerBar;
	public PowerUp powerUp;
	public Fonts fonts;
	public DrawScore drawScore;
	public long timePressed;
	public int playState = 0;
	public boolean gameOver;
	public boolean gameFinished;
	public boolean setCam;
	public int finishDistance = 37800;
	
	public Playing(Game game) {
		super(game);
		initSetup();
	}
	
	private void initSetup() {

		gameMap = new Map(game);
		ball = new Ball(game);
		cam = new Camera(0, 0, game, ball);
		launchButton = new LaunchButton(game.GAME_WIDTH / 2, (int) (270), 0, Gamestate.LAUNCH);
		powerBar = new PowerBar(game.GAME_WIDTH / 2, (int) (50), 0);
		fonts = new Fonts(game);
		powerUp = new PowerUp();
		drawScore = new DrawScore(this);
		gameOverOverlay = new GameOverOverlay(game);
		gameFinishedOverlay = new GameFinishedOverlay(game);
	}

	@Override
	public void update() {

		if(gameFinished) {
			gameFinishedOverlay.update();
		}
		else if(gameOver) {
			gameOverOverlay.update();
		}
		else {
			if(playState == 0) {
				launchButton.update();
				if(launchButton.mousePressed) {
					powerBar.update();
				}
			}
			else {
				game.timeElapsed = System.currentTimeMillis() - game.startTime;
				for(int i = 0; i < obj.length; i++) {
					if(obj[i] != null) {
						if(obj[i].drawed) {
							obj[i].reset();
							aSetter.resetObject(obj[i],i);
							obj[i].drawed = false;
						}
						else
							obj[i].update(game);
					}
				}
				ball.update();
				cam.update();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		gameMap.draw(g2);
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, game);
			}
		}
		drawScore.draw(g);
		g2.translate(cam.getX(), cam.getY());
		ball.draw(g2);
		g2.translate(-cam.getX(), -cam.getY());
		powerUp.draw(g);
		
		if(gameOver) {
			gameOverOverlay.draw(g);
		}
		else if(gameFinished) {
			gameFinishedOverlay.draw(g);
		}
		else if(playState == 0) {
			launchButton.draw(g);
			powerBar.draw(g);
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(gameFinished) {
			gameFinishedOverlay.mousePressed(e);
		}
		else if(gameOver) {
			gameOverOverlay.mousePressed(e);
		}
		else {
			if (isIn1(e, launchButton)) {
				launchButton.setMousePressed(true);
				timePressed = System.nanoTime();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(gameFinished) {
			gameFinishedOverlay.mouseReleased(e);
		}
		else if(gameOver) {
			gameOverOverlay.mouseReleased(e);
		}
		else {
			if (isIn1(e, launchButton)) {
				if (launchButton.isMousePressed()) {
					playState = 1;
					ball.initSpeedX = (float)((powerBar.index *2 * Math.cos(Math.toRadians(45)))*5/6);
					ball.initSpeedY = (float)(-powerBar.index * (float)Math.sin(Math.toRadians(60)));
				}
			}
			powerBar.index = 0;
		}
		resetButtons();
	}
	
	private void resetButtons() {
		launchButton.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(gameFinished) {
			gameFinishedOverlay.mouseMoved(e);
		}
		else if(gameOver) {
			gameOverOverlay.mouseMoved(e);
		}
		else {
			launchButton.setMouseOver(false);
			if (isIn1(e, launchButton)) {
				launchButton.setMouseOver(true);
			}
		}
	}
	
	public void resetAll() {
		// TODO: reset ball, map, etc
		gameOver = false;
		gameFinished = false;
		playState = 0;
		powerUp.index = 3;
		for(SuperObject so : obj) {
			if(so != null) {
				so.powerCount = 0;
				so.reset();
			}	
		}
		aSetter.reset();
		ball.reset();
		cam.reset();
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver) {
			gameOverOverlay.keyPressed(e);
		}
		else {
			switch (e.getKeyCode()) {		
			case KeyEvent.VK_SPACE: {
				if(playState == 1 && powerUp.index != 0) {
					game.startTime += game.timeElapsed;
					game.timeElapsed = 1000;
					ball.initSpeedX += (float)(((game.getPlaying().ball.speed) * Math.cos(Math.toRadians(45))));
					ball.initSpeedY += (float)(-(game.getPlaying().ball.speed) * (float)Math.sin(Math.toRadians(45)));
					powerUp.update();
				}
				break;
			}
			default:
				break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!gameOver) {
		switch (e.getKeyCode()) {		
			case KeyEvent.VK_SPACE: {

				break;
			}
		}
		}
	}
}
