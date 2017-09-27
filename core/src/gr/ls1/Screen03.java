package gr.ls1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Screen03 extends GraniteScreen{
	private SpriteBatch sb;
	private BitmapFont bitmapFont;
	private String mytext;
	private GlyphLayout glyph;
	private TextureRegion tr;
	
	public Screen03(MyScreenManager msm){
		super(msm);
		sb = new SpriteBatch();
		bitmapFont = new BitmapFont(Gdx.files.internal("myfonts.fnt"));
		bitmapFont.getData().setScale(.5f);
		mytext = "012t456789";
		glyph = new GlyphLayout();
		glyph.setText(bitmapFont, mytext);
		tr = new TextureRegion(new Texture("spritesheet.png"));
		tr.flip(false, false);
	}

	@Override
	public void dispose() {
		bitmapFont.dispose();
		tr.getTexture().dispose();
		System.out.println("Screen 03 dispose");
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public void render() {
		sb.begin();
		//bitmapFont.draw(sb, glyph, 400-glyph.width/2, 300-glyph.height/2);
		//bitmapFont.draw(sb, glyph, 195, 600);
		sb.draw(tr, 0, 0);
		sb.end();
	}
	
	
	
}
