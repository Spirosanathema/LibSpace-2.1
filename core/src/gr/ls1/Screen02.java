package gr.ls1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Screen02 extends GraniteScreen{
	private Texture texture;
	
	public Screen02(MyScreenManager msm){
		super(msm);
		texture = new Texture(Gdx.files.internal("cbr.jpg"));
		
	}

	@Override
	public void dispose() {
		texture.dispose();
		System.out.println("Screen 2 Disposed");
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void render() {
		sb.begin();
		sb.draw(texture, 0, 0);
		sb.end();
		
	}
	
	
	
	
}
