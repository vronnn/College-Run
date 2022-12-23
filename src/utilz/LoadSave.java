package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

	public static final String MENU_BUTTONS = "menu_button.png";
	public static final String LAUNCH_BUTTONS = "launch_button.png";
	public static final String POWER_BAR = "power_bar.png";
	public static final String POWER_UP = "power_up.png";
	public static final String BACKGROUND = "background_img.png";
	public static final String ENDING_BACKGROUND = "ending_screen.png";
	public static final String RH_BUTTONS = "rh_button.png";
	public static final String COLLEGE = "college.png";
	public static final String RUN = "run.png";
	public static final String HOUSE = "house.png";
	public static final String ENDING_LIGHT = "end_light.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
}
