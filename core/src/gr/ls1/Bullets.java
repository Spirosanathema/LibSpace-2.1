package gr.ls1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Bullets {
	private Sprite bulletSprite;
	
	public Bullets(Sprite sprite){
		bulletSprite = new Sprite(new Texture(Gdx.files.internal("bullet.png")));
		
		bulletSprite.setPosition(sprite.getX() + (sprite.getWidth()/2-6), sprite.getY() + (sprite.getHeight()/2+20));
	}
	
	public void bulletsUpdate(float dt){
		getBulletsRemoveFlag();
		
		if(getBulletsRemoveFlag() == false)
		bulletSprite.translate(0, dt * 800);
		
		bulletRect();
	}
	
	public boolean getBulletsRemoveFlag(){
		if(bulletSprite.getY() > 600 + bulletSprite.getHeight()){
			
			return true;
		}			
		else
			return false;
	}
	
	public Rectangle bulletRect() {
		
		return new Rectangle(bulletSprite.getX(), bulletSprite.getY(), bulletSprite.getWidth(), bulletSprite.getHeight());
	}
	
	/////////////////////////// GETTERS - SETTERS ///////////////////////////////////
	
	public Sprite getBulletSprite() { return bulletSprite; }
		
}// END CLASS
