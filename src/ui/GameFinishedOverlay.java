package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.RHButtons.*;

public class GameFinishedOverlay {

	Game game;
	private RhButton retry, home;
	private BufferedImage img;
	private int bgX, bgY, bgW, bgH;
		
	public GameFinishedOverlay(Game game) {
		this.game = game;
		
		loadImg();
		loadButtons();
	}

	private void loadImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.ENDING_BACKGROUND);
		bgW = (int) (img.getWidth() * (game.SCALE+0.5));
		bgH = (int) (img.getHeight() * (game.SCALE+0.5));
		bgX = game.GAME_WIDTH/2 - bgW/2;
		bgY = (int) (120);
	}
	
	private void loadButtons() {
		int homeX = (int)(game.GAME_WIDTH/2 - 1.2*RHB_SIZE);
		int retryX = (int)(game.GAME_WIDTH/2 - RHB_SIZE + RHB_SIZE*1.2);
		int y = (int)(game.GAME_HEIGHT/2 + 7);
		retry = new RhButton(retryX, y, RHB_SIZE, RHB_SIZE, 0);
		home = new RhButton(homeX, y, RHB_SIZE, RHB_SIZE, 1);
	}
	
		
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
		g.drawImage(img, bgX, bgY, bgW, bgH, null);
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

}
