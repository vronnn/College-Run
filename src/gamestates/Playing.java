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
import tile.TileManager;
import ui.LaunchButton;
import ui.PowerBar;

public class Playing extends State implements Statemethods{

	public Camera cam;
	public Ball ball;
	TileManager tileM;
	public AssetSetter aSetter = new AssetSetter(game);
	public SuperObject[] obj = new SuperObject[10];
	public LaunchButton launchButton;
	public PowerBar powerBar;
	public long timePressed;
	public int playState = 0;
	
	public Playing(Game game) {
		super(game);
		initSetup();
	}
	
	private void initSetup() {

		tileM = new TileManager(game);
		ball = new Ball(game);
		cam = new Camera(0, 0, game, ball);
		launchButton = new LaunchButton(game.GAME_WIDTH / 2, (int) (270), 0, Gamestate.LAUNCH);
		powerBar = new PowerBar(game.GAME_WIDTH / 2, (int) (100), 0);

	}

	@Override
	public void update() {
		
		if(playState == 0) {
			launchButton.update();
			System.out.println(launchButton.isMousePressed());
			if(launchButton.mousePressed) {
				powerBar.update();
			}
		}
		else {
			game.timeElapsed = System.currentTimeMillis() - game.startTime;
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].update();
				}
			}
			ball.update();
			cam.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		if(playState == 0) {
			tileM.draw(g2);
			launchButton.draw(g);
			powerBar.draw(g);
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, game);
				}
			}
			ball.draw(g2);
		}
		else {
			g2.translate(cam.getX(), cam.getY());
			tileM.draw(g2);
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, game);
				}
			}
			ball.draw(g2);
			g2.translate(-cam.getX(), -cam.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isIn1(e, launchButton)) {
			launchButton.setMousePressed(true);
			timePressed = System.nanoTime();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isIn1(e, launchButton)) {
			if (launchButton.isMousePressed()) {
				playState = 1;
				ball.initSpeedX = (float)((powerBar.index * Math.cos(Math.toRadians(45)))*5/6);
				ball.initSpeedY = (float)(-powerBar.index * (float)Math.sin(Math.toRadians(45)));
			}
		}
		powerBar.index = 0;
		resetButtons();
	}
	
	private void resetButtons() {
//		for (MenuButton mb : buttons)
//			mb.resetBools();

		launchButton.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		launchButton.setMouseOver(false);
		if (isIn1(e, launchButton)) {
			launchButton.setMouseOver(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {		
			case KeyEvent.VK_SPACE: {
				if(ball.index > 0) {
					ball.lastX = ball.x;
					ball.lastY = ball.y;
					ball.initSpeedX = 0;
					ball.initSpeedY = 0;
					game.startTime += game.timeElapsed;
					game.timeElapsed = 1000;
					ball.index--;
				}
				else {
					ball.index = 0;
				}
				break;
			}
			case KeyEvent.VK_BACK_SPACE: {
				Gamestate.state = Gamestate.MENU;
				break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {		
			case KeyEvent.VK_SPACE: {
				ball.x = ball.lastX;
				ball.y = ball.lastY;
				ball.initSpeedX = (float)((ball.speed * Math.cos(Math.toRadians(ball.startDegree)))*2/3);
				ball.initSpeedY = (float)(-ball.speed * (float)Math.sin(Math.toRadians(ball.startDegree))) * 7/8;
				break;
			}
		}
	}
}
