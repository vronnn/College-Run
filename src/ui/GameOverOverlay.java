package ui;

import static utilz.Constants.UI.RHButtons.RHB_SIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import gamestates.Gamestate;
import main.Game;

public class GameOverOverlay {

	Game game;
	private RhButton retry, home;
	
	public GameOverOverlay(Game game) {
		this.game = game;
		loadButtons();
	}
	
	private void loadButtons() {
		int homeX = (int)(game.GAME_WIDTH/2 - 1.2*RHB_SIZE);
		int retryX = (int)(game.GAME_WIDTH/2 - RHB_SIZE + RHB_SIZE*1.2);
		int y = (int)(game.GAME_HEIGHT/2 + 7);
		retry = new RhButton(retryX, y, RHB_SIZE, RHB_SIZE, 0);
		home = new RhButton(homeX, y, RHB_SIZE, RHB_SIZE, 1);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
		
		g.setColor(Color.white);
		g2.setFont(Fonts.pixel.deriveFont(Font.BOLD, 30F));
		g.drawString("GAME OVER!", 435, 200);
		
		retry.draw(g);
		home.draw(g);
	}
	
	public void update() {
		retry.update();
		home.update();
	}
	
	public boolean isIn(RhButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(),e.getY());
	}
	
	public void mouseMoved(MouseEvent e) {
		retry.setMouseOver(false);
		home.setMouseOver(false);
		
		if(isIn(home, e))
			home.setMouseOver(true);
		else if(isIn(retry, e))
			retry.setMouseOver(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(isIn(home, e)) {
			if(home.isMousePressed()) {
				game.getPlaying().resetAll();
				Gamestate.state = Gamestate.MENU;
			}
		}
		else if(isIn(retry, e)) {
			if(retry.isMousePressed()) {
				game.getPlaying().resetAll();
				Gamestate.state = Gamestate.PLAYING;
			}
		}
		
		home.resetBools();
		retry.resetBools();
	}
	
	public void mousePressed(MouseEvent e) {
		if(isIn(home, e))
			home.setMousePressed(true);
		else if(isIn(retry, e))
			retry.setMousePressed(true);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.getPlaying().resetAll();
			Gamestate.state = Gamestate.MENU;
		}
	}
}
