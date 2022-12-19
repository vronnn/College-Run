package main;

import Entity.Ball;

public class Camera {

	double x,y;
	Game game;
	Ball ball;
	
	public Camera(double x, double y, Game game, Ball ball) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.ball = ball;
	}
	
	public void update() {
		if(ball.x < 37000) {
			if(ball.screenX != 3 * game.tileSize) {
				System.out.println("masuk cam");
				x -= (ball.initSpeedX + 2);
				ball.screenX -= 2;
			}
			else
				x -= ball.initSpeedX;
		}
		//System.out.println(x + " " + y);
	}
	
	public void reset() {
		x = 0;
		y = 0;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
}
