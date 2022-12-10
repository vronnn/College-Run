package main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entity.Ball;

public class MouseHandler implements MouseListener{

	Ball ball;
	GamePanel gp;
	Graphics g;
	
	public MouseHandler(Ball ball, GamePanel gp) {
		
		this.ball = ball;
		this.gp = gp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		gp.start = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
