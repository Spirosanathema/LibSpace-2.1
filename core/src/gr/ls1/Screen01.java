package gr.ls1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class Screen01 extends GraniteScreen{
	private Texture texture, spacecraftTexture;
	private Sprite sprite;
	private ArrayList<Bullets> bulletsList, bulletsListRemove;
	private ArrayList<Enemies> enemyListToRemove;
	private static ArrayList<Enemies> enemyList;
	private float camX, camY;	// CAMERAS VIEWPORTS ACCORDING TO MY SCALE
	private Vector3 camPoint;	// THE POINT THAT CAMERA FOLLOWS EVERYTIME
	private float myViewportScale;	// MY SCALE ACCORDING TO ORIGINAL WINDOW SIZE
	private float lerp;
	private int enemyNumber;
	
	//Enemy Bullets
	private ArrayList<EnemyBullets> enemyBulletsList;
	
	public Screen01(MyScreenManager msm){
		super(msm);
		texture = new Texture("spacebk.jpg");
		spacecraftTexture = new Texture("spacecraft.png");		
		sprite = new Sprite(spacecraftTexture);
		sprite.setPosition((MyGame.G_WIDTH - sprite.getWidth()) / 2, 15);		//SPACECRAFT STARTING POSITION
		bulletsList = new ArrayList<Bullets>();
		enemyList = new ArrayList<Enemies>();
		enemyListToRemove = new ArrayList<Enemies>();
		enemyBulletsList = new ArrayList<EnemyBullets>();
		enemyNumber = 2;									// NUMBER OF ENEMIES
		myViewportScale = 1f;
		lerp = .1f;
		camX = Gdx.graphics.getWidth() * myViewportScale;
		camY = Gdx.graphics.getHeight() * myViewportScale;
		camPoint = new Vector3();
		//vp = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
		//vp.apply();		
		cam.setToOrtho(false, camX, camY);
		
		for(int i=0; i<enemyNumber; i++) {
			enemyList.add(new Enemies(sprite));
			enemyBulletsList.add(new EnemyBullets(enemyList.get(i).enemySprite));
		}
	}

	@Override
	public void dispose() {
		texture.dispose();
		spacecraftTexture.dispose();
		
		for(Enemies e:enemyList) {
			e.dispose();
		}
		for(EnemyBullets eb:enemyBulletsList)
			eb.dispose();
		System.out.println("Screen01 disposed");
	}

	@Override
	public void update(float dt) {
		
		ArrayList<Bullets> bulletsToRemove = new ArrayList<Bullets>(); // TRICK to remove elements from ArrayList while updating the list
		ArrayList<Enemies> enemyListToRemove = new ArrayList<Enemies>();
		ArrayList<Rectangle> rectListToRemove = new ArrayList<Rectangle>();
		ArrayList<EnemyBullets> enemyBulletsToRemoveList = new ArrayList<EnemyBullets>();
		
		
		for(Bullets b: bulletsList){
			b.bulletsUpdate(dt);				// Bullets Movement


			if(b.getBulletsRemoveFlag() == true){
				bulletsToRemove.add(b);
			}			
		}

		for(int i=0; i<enemyList.size(); i++) {
			enemyList.get(i).enemyupdate(dt);
			
			for(Bullets b:bulletsList) {
				if(enemyList.get(i).enemyRect().contains(b.bulletRect())) {
					enemyListToRemove.add(enemyList.get(i));
					bulletsToRemove.add(b);
				}				
			}			
		}
		
		for(EnemyBullets eb:enemyBulletsList) {
			eb.enemyBulletsUpdate(dt);
			if(eb.enemyBulletsToRemove()) {
				enemyBulletsToRemoveList.add(eb);
			}
		}
		
		
		bulletsList.removeAll(bulletsToRemove);
		enemyList.removeAll(enemyListToRemove);
		enemyBulletsList.removeAll(enemyBulletsToRemoveList);
		
		enemyBulletsReload();
		
		camPoint.set(sprite.getX() + sprite.getWidth()/2, sprite.getY() + sprite.getHeight()/2, 0);
		
		
//		cam.position.x = camPoint.x;
//		cam.position.y = camPoint.y;
		
//		lerp:
//			a + (b - a) * lerp;
//			a = curent camera's position
//			b = target's position
		
		cam.position.x += (camPoint.x - cam.position.x) * lerp;
		cam.position.y += (camPoint.y - cam.position.y) * lerp;
		
	// Logic behind camera following a target but NEVER gets out of boundaries
		if(cam.position.x <= camX/2)
			cam.position.x = camX/2;
		if(cam.position.x >= Gdx.graphics.getWidth() - camX/2)
			cam.position.x = Gdx.graphics.getWidth() - camX/2;
		if(cam.position.y <= camY/2)
			cam.position.y = camY/2;
		if(cam.position.y >= Gdx.graphics.getHeight() - camY/2)
			cam.position.y = Gdx.graphics.getHeight() - camY/2;
		
		
//		if(cam.position.x <= Gdx.graphics.getWidth()/2)
//			cam.position.x = Gdx.graphics.getWidth()/2;
//		if(cam.position.x >= Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/2)
//			cam.position.x = Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/2;
//		if(cam.position.y <= Gdx.graphics.getHeight()/2)
//			cam.position.y = Gdx.graphics.getHeight()/2;
//		if(cam.position.y >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/2)
//			cam.position.y = Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/2;
			
			
		cam.update();		// VERY IMPORTANTm
	}

	@Override
	public void render() {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(texture, 0, 0);
//		if(enemyFlag)
//			enemy.enemySprite.draw(sb);
		
		for(Bullets b: bulletsList){
			b.getBulletSprite().draw(sb);			
		}
		for(Enemies e:enemyList) {
			e.enemySprite.draw(sb);
		}
		for(EnemyBullets eb:enemyBulletsList){
			eb.enemyBulletSprite.draw(sb);
		}
		
		sprite.draw(sb);
		//sb.draw(enemy.enemyTex, cam.position.x + 50, cam.position.y + 50);
		
		sb.end();
	}
	
	public void createBullet(){
		bulletsList.add(new Bullets(sprite));
	}
	
	private void enemyBulletsReload() {
		if(enemyBulletsList.size() == 0) {
			for(int i=0; i<enemyList.size(); i++) {
				enemyBulletsList.add(new EnemyBullets(enemyList.get(i).enemySprite));
			}
		}
	}
	
	
	
	
	
	///////////////////// GETTERS - SETTERS //////////////////////////////////////////////////
	public Texture getSpacecraftTexture(){ return spacecraftTexture; }
	public Sprite getSprite() { return sprite; }
	public static int getEnemiesNumber() { return enemyList.size(); }

}
