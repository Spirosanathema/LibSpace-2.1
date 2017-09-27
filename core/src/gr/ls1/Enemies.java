package gr.ls1;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Enemies {
	
	public Texture enemyTex;
	public Sprite enemySprite;
	public float enemyPosX, enemyPosY;
	public float enemySpeed;
	private int prosimo;
	
	
	public Enemies(Sprite spaceCraftSprite) {
		
		enemyTex = new Texture(Gdx.files.internal("enemies/enemy45.png"));
		enemySprite = new Sprite(enemyTex);
		enemySprite.setPosition((ThreadLocalRandom.current().nextInt(2, 601)) + (enemySprite.getWidth()/2), ThreadLocalRandom.current().nextInt(350, 550));
		
		prosimo = ThreadLocalRandom.current().nextInt(2, 9 + 1);  // RANDOM NUMBER GENERATOR
		if((prosimo % 2) == 0)
			prosimo = 1;
		else {
			prosimo = -1;
		}
				
		enemySpeed = ThreadLocalRandom.current().nextInt(100, 350 + 1) * prosimo; // RANDOMISE ENEMY SPEED
	}
	
	public void enemyupdate(float dt) {
		enemySprite.translate(enemySpeed * dt, 0);
		if(enemySprite.getX() >= (800 - enemySprite.getWidth()))
			enemySpeed *= -1;
		if(enemySprite.getX() <= (0))
			enemySpeed *= -1;
	}

	public Rectangle enemyRect() {
		return new Rectangle(enemySprite.getX(), enemySprite.getY(), enemySprite.getWidth(), enemySprite.getHeight());
	}
	
	public void dispose() {
		enemyTex.dispose();
	}
	
	
	
}
