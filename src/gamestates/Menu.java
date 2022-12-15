package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import main.GamePanel;
import ui.LaunchButton;
import ui.MenuButton;
import ui.QuitButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods{

	public MenuButton play;
	public QuitButton quit;
	private BufferedImage backgroundMenu, backgroundImg;
	private int menuX, menuY, menuWIdth, menuHeight;
	
	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND);
	}
	
	private void loadBackground() {
		backgroundMenu = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWIdth = (int) (backgroundMenu.getWidth() * 1.5);
		menuHeight = (int) (backgroundMenu.getHeight() * 1.5);
		menuX = game.GAME_WIDTH / 2 - menuWIdth / 2;
		menuY = (int) 70;
	}

	private void loadButtons() {
		//buttons[0] = new MenuButton(game.GAME_WIDTH / 2, (int) (270), 0, Gamestate.PLAYING);
		play = new MenuButton(game.GAME_WIDTH / 2, (int) (270), 0, Gamestate.PLAYING);
		quit = new QuitButton(game.GAME_WIDTH / 2, (int) (340), 0, Gamestate.QUIT);
	}

	@Override
	public void update() {
//		for (MenuButton mb : buttons)
//			mb.update();
		play.update();
		quit.update();
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(backgroundImg, 0, 0, game.GAME_WIDTH, game.GAME_HEIGHT, null);
		g.drawImage(backgroundMenu, menuX, menuY, menuWIdth, menuHeight, null);
		
		play.draw(g);
		quit.draw(g);
//		for (MenuButton mb : buttons)
//			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		for (MenuButton mb : buttons) {
//			if (isIn(e, mb)) {
//				mb.setMousePressed(true);
//			}
//		}
		if (isIn(e, play)) {
			play.setMousePressed(true);
		}
		else if (isIn(e, quit)) {
			quit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		for (MenuButton mb : buttons) {
//			if (isIn(e, mb)) {
//				if (mb.isMousePressed())
//					mb.applyGamestate();
//				break;
//			}
//		}
		if (isIn(e, play)) {
			if (play.isMousePressed())
				play.applyGamestate();
		}
		else if (isIn(e, quit)) {
			if (quit.isMousePressed())
				quit.applyGamestate();
		}

		resetButtons();
	}
	
	private void resetButtons() {
//		for (MenuButton mb : buttons)
//			mb.resetBools();

		play.resetBools();
		quit.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		for (MenuButton mb : buttons)
//			mb.setMouseOver(false);
//
//		for (MenuButton mb : buttons)
//			if (isIn(e, mb)) {
//				mb.setMouseOver(true);
//				break;
//			}
		
		play.setMouseOver(false);
		quit.setMouseOver(false);
		if (isIn(e, play)) {
			play.setMouseOver(true);
		}
		else if (isIn(e, quit)) {
			quit.setMouseOver(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			Gamestate.state = Gamestate.PLAYING;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
