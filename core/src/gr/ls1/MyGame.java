package gr.ls1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {
	public static final int G_WIDTH = 800;
	public static final int G_HEIGHT = 600;
	public static final String G_TITLE = "LibCraft";
	public static final float STEP = 1/60f;
	public static boolean restartTheGame;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	private Viewport vp;
	private MyScreenManager msm;
	private Hud hud;
	private float accum;
	
	
	@Override
	public void create () {
		cam = new OrthographicCamera();
		hud = new Hud();
		sb = new SpriteBatch();
		msm = new MyScreenManager(this);
		vp = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
		restartTheGame = false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(restartTheGame) {
			msm = new MyScreenManager(this);
			hud = new Hud();
			restartTheGame = false;
			
		}
			
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP){
			accum -= STEP;
			msm.update(STEP);
			hud.update(STEP);
		}
		
		msm.render();
		hud.render();
	}
	
	@Override
	public void dispose () {
		hud.dispose();
		msm.dispose();
		//sb.dispose();
		System.out.println("My game dispose");
	}
	
	@Override
	   public void resize(int width, int height){
	      //vp.update(width,height);
	      //cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
	   }
	
	/////////////////////// GETTERS ///////////////////////////////////////////
	public OrthographicCamera getCam() { return cam; }
	public OrthographicCamera getHudCam() { return hudCam; }
	public SpriteBatch getSpriteBatch() { return sb; }
	public Viewport getMyViewport() { return vp; }
}
