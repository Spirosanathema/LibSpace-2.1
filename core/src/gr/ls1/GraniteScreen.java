package gr.ls1;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GraniteScreen {
	private MyGame game;
	public OrthographicCamera cam, hudCam;
	public Viewport vp;
	public SpriteBatch sb;
	
	public GraniteScreen(MyScreenManager msm){
		this.game = msm.getGame();
		sb = game.getSpriteBatch();
		cam = game.getCam();
		hudCam = game.getHudCam();
		vp = game.getMyViewport();
	}
	
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
