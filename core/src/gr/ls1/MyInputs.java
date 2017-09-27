package gr.ls1;

public class MyInputs {
	public static final int NUM_OF_KEYS = 6;
	public static boolean[] keys, pkeys;
	public static final int UP_MYKEY = 0;
	public static final int DOWN_MYKEY = 1;
	public static final int LEFT_MYKEY = 2;
	public static final int RIGHT_MYKEY = 3;
	public static final int SPACE_MYKEY = 4;
	public static final int ESC_MYKEY = 5;
	
	static{
		keys = new boolean[NUM_OF_KEYS];
		pkeys = new boolean[NUM_OF_KEYS];
	}
	
	///////////////// 4 methods of MY Input Keys control ////////////////////////////////
	
	public static void myInputUpdate(){
		for(int i=0; i<NUM_OF_KEYS; i++){
			pkeys[i] = keys[i];
		}
	}
	
	public static boolean isMyKeyPressed(int k){
		return (keys[k] && !pkeys[k]);
	}
	
	public static boolean isMyKeyDown(int k){
		return keys[k];
	}
	
	public static void setMyKey(int k, boolean b){
		keys[k] = b;
	}
	
}
