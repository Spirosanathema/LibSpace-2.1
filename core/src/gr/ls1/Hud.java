package gr.ls1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud {
	private OrthographicCamera hc;
	private SpriteBatch sb;
	private Texture tex1, tex2;
	private BitmapFont myfont, enemyFont, gameOverFont;
	private GlyphLayout gl, glEnemies, glGameOver;
	private int myCount;
	private float sccore;
	private float gameOverScale = 0.05f;
	public static boolean startExit;
	
	
	public Hud(){
		hc = new OrthographicCamera();
		hc.setToOrtho(false, 800, 600);
		sb = new SpriteBatch();
		tex1 = new Texture("startbutton.png");
		tex2 = new Texture("exitbutton.png");
		myfont = new BitmapFont(Gdx.files.internal("font2.fnt"));
		enemyFont = new BitmapFont(Gdx.files.internal("enemyfonts/enemies.fnt"));
		enemyFont = new BitmapFont(Gdx.files.internal("enemyfonts/enemies.fnt"));
		gameOverFont = new BitmapFont(Gdx.files.internal("gameoverfont/gemaOverFont.fnt"));
		myfont.getData().setScale(.4f);
		enemyFont.getData().setScale(.3f);
		gameOverFont.getData().setScale(gameOverScale);
		myCount = 0;
		sccore = 0;
		gl = new GlyphLayout();
		glEnemies = new GlyphLayout();
		glGameOver = new GlyphLayout();
		startExit = false;
	}
	
	public void update(float dt){
		countUp(dt);
		hc.update();
		
	}
	
	public void render(){		
		sb.setProjectionMatrix(hc.combined);
		
		sb.begin();
		//sb.draw(tex, 0, 0);
		myfont.draw(sb, gl, 10, 580);
		enemyFont.draw(sb, glEnemies, 550, 580);
		gameOverFont.draw(sb, glGameOver, ((MyGame.G_WIDTH - glGameOver.width)/2), ((MyGame.G_HEIGHT + glGameOver.height)/2));
		if(startExit) {
			//sb.draw(tex, (MyGame.G_WIDTH - tex.getWidth())/2, (MyGame.G_HEIGHT + tex.getHeight())/2 - 200);
			//sb.draw(tex, ((Gdx.graphics.getWidth()/2) - ), ());
			sb.draw(tex1, ((Gdx.graphics.getWidth()*3.5f/8) - tex1.getWidth()), (Gdx.graphics.getHeight()*2.5f/8) - tex1.getHeight());
			sb.draw(tex2, ((Gdx.graphics.getWidth()*5.7f/8) - tex2.getWidth()), (Gdx.graphics.getHeight()*2.5f/8) - tex2.getHeight());
		}
		sb.end();
	}
	
	public void dispose(){
		sb.dispose();
		tex1.dispose();
		tex2.dispose();
		myfont.dispose();
		enemyFont.dispose();
	}
	
	public String toString(){
		return "Den eimai disposed!!!";
	}
	
	private void countUp(float dt){
		sccore += dt;
		
		if(Screen01.getEnemiesNumber() == 0) {
			gameOverHud();
			if(gameOverScale <= .8f) {
				gameOverScale += .05f;
				
				startExit = true;
				gameOverFont.getData().setScale(gameOverScale);
			}
			return;
		}
		
		gl.setText(myfont, ""+myCount);
		glEnemies.setText(enemyFont, "enemies: " + Screen01.getEnemiesNumber());
		if(sccore >= 1f){
			
			myCount++;
			sccore = 0;
		}
		
	}
	
	public void gameOverHud() {
		gl.setText(myfont, "");
		glEnemies.setText(enemyFont, "");
		glGameOver.setText(gameOverFont, "Game Over");
		
	}
	
	public boolean getStartExitBool() { return startExit; }
	public Texture getStartButton() { return tex1; }
	public Texture getExitButton() { return tex2; }
	
}
