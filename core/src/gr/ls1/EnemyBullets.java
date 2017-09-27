package gr.ls1;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBullets {
	public Texture enemyBulletTex;
	public Sprite enemyBulletSprite;
	private int enemyBulletsSpeed = ThreadLocalRandom.current().nextInt(-200, -80);
	
	
	public EnemyBullets(Sprite sp) {		
		enemyBulletTex = new Texture(Gdx.files.internal("enemyBullet.png"));
		enemyBulletSprite = new Sprite(enemyBulletTex);
		enemyBulletSprite.setPosition((sp.getX() + sp.getWidth()/2), (sp.getY() - sp.getHeight()/2));
	}
	
	public void enemyBulletsUpdate(float dt) {
		enemyBulletsToRemove();
		if(!enemyBulletsToRemove()) {
			enemyBulletSprite.translate(0, enemyBulletsSpeed * dt);
		}
		
	}
	
	public boolean enemyBulletsToRemove() {
		if(enemyBulletSprite.getY() <= 0) { return true; }
		else
			return false;
	}
	
	public void dispose() {
		enemyBulletTex.dispose();
	}
	
	
	

}
