package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entity.Ball;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			gp.ball.lastX = gp.ball.x;
			gp.ball.lastY = gp.ball.y;
			gp.ball.initSpeedX = 0;
			gp.ball.initSpeedY = 0;
			gp.startTime += gp.timeElapsed;
			gp.timeElapsed = 1000;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			gp.ball.x = gp.ball.lastX;
			gp.ball.y = gp.ball.lastY;
			gp.ball.initSpeedX = (float)((gp.ball.speed * Math.cos(Math.toRadians(gp.ball.startDegree)))*2/3);
			gp.ball.initSpeedY = (float)(-gp.ball.speed * (float)Math.sin(Math.toRadians(gp.ball.startDegree))) * 7/8;
			
		}
	}
	
	
}
