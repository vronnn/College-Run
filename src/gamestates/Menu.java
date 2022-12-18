package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods{

	private MenuButton[] buttons = new MenuButton[2];
	private BufferedImage backgroundMenu1,backgroundMenu2, backgroundImg;
	private int menuX1, menuY1, menuWIdth1, menuHeight1;
	private int menuX2, menuY2, menuWIdth2, menuHeight2;
	
	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND);
	}
	
	private void loadBackground() {
		backgroundMenu1 = LoadSave.GetSpriteAtlas(LoadSave.COLLEGE);
		backgroundMenu2 = LoadSave.GetSpriteAtlas(LoadSave.RUN);
		menuWIdth1 = (int) (backgroundMenu1.getWidth() * 5);
		menuHeight1 = (int) (backgroundMenu1.getHeight() * 5);
		menuX1 = game.GAME_WIDTH / 2 - menuWIdth1 / 2;
		menuY1 = (int) 109;
		menuWIdth2 = (int) (backgroundMenu2.getWidth() * 4.5);
		menuHeight2 = (int) (backgroundMenu2.getHeight() * 4.5);
		menuX2 = game.GAME_WIDTH / 2 - menuWIdth2 / 2;
		menuY2 = (int) 170;
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(game.GAME_WIDTH / 2, (int) (260), 0, Gamestate.PLAYING);
		buttons[1] = new MenuButton(game.GAME_WIDTH / 2, (int) (325), 1, Gamestate.QUIT);
	}

	@Override
	public void update() {
		for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(backgroundImg, 0, 0, game.GAME_WIDTH, game.GAME_HEIGHT, null);
		g.drawImage(backgroundMenu1, menuX1, menuY1, menuWIdth1, menuHeight1, null);
		g.drawImage(backgroundMenu2, menuX2, menuY2, menuWIdth2, menuHeight2, null);
		
		for (MenuButton mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}

		resetButtons();
	}
	
	private void resetButtons() {
		for (MenuButton mb : buttons)
			mb.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
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
