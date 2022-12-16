package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import gamestates.Gamestate;
import main.Game;

public class GameOverOverlay {

	Game game;
	
	public GameOverOverlay(Game game) {
		this.game = game;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
		
		g.setColor(Color.white);
		g2.setFont(Fonts.pixel.deriveFont(Font.BOLD, 30F));
		g.drawString("GAME OVER", 440, 200);
		g2.setFont(Fonts.nonpixel.deriveFont(Font.BOLD, 15F));
		g.drawString("Press esc to enter Main Menu!", 470, 300);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.getPlaying().resetAll();
			Gamestate.state = Gamestate.MENU;
		}
	}
}
