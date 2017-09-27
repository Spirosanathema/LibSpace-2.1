package gr.ls1;

import java.util.Stack;
import com.badlogic.gdx.Gdx;
import gr.ls1.GraniteScreen;
import gr.ls1.MyGame;
import gr.ls1.Screen01;
import gr.ls1.Screen02;
import gr.ls1.Screen03;

public class MyScreenManager {
	private final int SCREEN01 = 1;
	private final int SCREEN02 = 2;
	private final int SCREEN03 = 3;
	private final float TIME_TILL_NEXT_SHOOT = 0.2f;
	private int screenState;
	private float shootWaitAccum;
	private Screen01 sc01;
	
	private static MyGame game;
	private Stack<GraniteScreen> screenStack;
	
	public MyScreenManager(MyGame game){
		Gdx.input.setInputProcessor(new MyInputProcessor());
		MyScreenManager.game = game;
		shootWaitAccum = 0;
		screenState = 1;
		screenStack = new Stack<GraniteScreen>();
		sc01 = new Screen01(this);
		pushScreen(SCREEN01);
	}
	
	public static MyGame getGame() { return game; }
	
	public void update(float dt){
		//System.out.println(dt);
		screenStack.peek().update(dt);
		myInputsHandler(dt);
		MyInputs.myInputUpdate();
		screenStack.peek().cam.update();
	}
	
	public void render(){
		screenStack.peek().render();		
	}
	
	public void dispose(){
		screenStack.peek().dispose();
		System.out.println("My Screen Manager Disposed");
	}
	
	////////////////// Screen Manager 2 Methods //////////////////////////////
	
	private void popScreen(){
		GraniteScreen g = screenStack.pop();
		g.dispose();
		System.out.println("Screen Disposed from POP");
	}
	
	private void pushScreen(int state){
		if(state == SCREEN01){
			screenState = 1;
			if(screenStack.size()>0){
				popScreen();
			}
			screenStack.push(sc01);
		}
		else if(state == SCREEN02){
			screenState = 2;
			if(screenStack.size()>0){
				popScreen();
			}
			screenStack.push(new Screen02(this));
		}
		else if(state == SCREEN03){
			screenState = 3;
			if(screenStack.size() > 0){
				popScreen();
			}
			screenStack.push(new Screen03(this));
		}
		else{
			screenState = 1;
			if(screenStack.size()>0){
				popScreen();
			}
			screenStack.push(sc01);
		}
		
	}
	
	/////////////////////// Input Handler Method and Movement /////////////////////////////////////////
	private void myInputsHandler(float dt){
		int horizontalMovement = 0;
		int verticalMovement = 0;
		shootWaitAccum += dt;
		
		if(MyInputs.isMyKeyDown(MyInputs.UP_MYKEY) == true){
			verticalMovement += 1;
		}
		if(MyInputs.isMyKeyDown(MyInputs.DOWN_MYKEY) == true){
			verticalMovement -= 1;
		}
		if(MyInputs.isMyKeyDown(MyInputs.LEFT_MYKEY) == true){
			horizontalMovement -= 1;
		}
		if(MyInputs.isMyKeyDown(MyInputs.RIGHT_MYKEY) == true){
			horizontalMovement += 1;
		}
		if(MyInputs.isMyKeyDown(MyInputs.SPACE_MYKEY) == true && shootWaitAccum >= TIME_TILL_NEXT_SHOOT){	//TIMER TRICK!!!!!!!!!! shootWaitAccum >= .3f
			if(screenState == SCREEN01){
				sc01.createBullet();
				shootWaitAccum = 0;
			}
		}
		if(MyInputs.isMyKeyPressed(MyInputs.ESC_MYKEY) == true){		// MANAGE SCREENS
			
			if(screenState == 1) {
				screenState = 2;
				if(screenStack.size()>0){
					popScreen();
				}
				pushScreen(SCREEN02);
			}
			else if(screenState == 2) {
				screenState = 3;
				if(screenStack.size()>0){
					popScreen();
				}
				pushScreen(SCREEN03);
			}
			else {
				screenState = 1;
				if(screenStack.size()>0){
					popScreen();
				}
				sc01 = new Screen01(this);
				pushScreen(SCREEN01);
			}
		}
		
		if(screenState == SCREEN01){
			if(sc01.getSprite().getX() >= (800 - sc01.getSpacecraftTexture().getWidth())){
				horizontalMovement -= 1;
			}
			if(sc01.getSprite().getX() <= 0){
				horizontalMovement += 1;
			}
			if(sc01.getSprite().getY() >= (600 - sc01.getSpacecraftTexture().getHeight())){
				verticalMovement -= 1;
			}
			if(sc01.getSprite().getY() <= 0){
				verticalMovement += 1;
			}
			
			sc01.getSprite().translate(horizontalMovement * dt * 300, verticalMovement * dt * 300);
		}
		
	}
		

} // END CLASS