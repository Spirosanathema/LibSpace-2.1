package gr.ls1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gr.ls1.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGame.G_WIDTH;
		config.height = MyGame.G_HEIGHT;
		config.title = MyGame.G_TITLE;
		new LwjglApplication(new MyGame(), config);
	}
}
