package main;

import object.Ball;

public class Camera {

	double x,y;
	GamePanel gp;
	
	public Camera(double x, double y, GamePanel gp) {
		this.x = x;
		this.y = y;
		this.gp = gp;
	}
	
	public void update(Ball ball) {
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
