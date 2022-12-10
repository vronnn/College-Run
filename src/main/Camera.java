package main;

import Entity.Ball;

public class Camera {

	double x,y;
	GamePanel gp;
	Ball ball;
	
	public Camera(double x, double y, GamePanel gp, Ball ball) {
		this.x = x;
		this.y = y;
		this.gp = gp;
		this.ball = ball;
	}
	
	public void update() {
		x -= ball.initSpeedX;
		//System.out.println(x + " " + y);
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
