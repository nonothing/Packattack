package com.dose.packattack.model;

import com.dose.packattack.enumerate.ETexture;
public class Block extends WorldObjectMove {

	private boolean isLeft;
	private boolean isRight;
	private boolean isDown;
	private boolean isUp;
	
	private static final int SIZE = 80;
    public boolean isDead;
    
    public Block(ETexture texture, int x, int y) {
        super(texture, x, y, SIZE, SIZE);
        isLeft = true;
        isRight = true;
    }

    @Override
    void animate() {
        // do nothing
    }
    
    public void move() {
    	if(!isUp){
    		if(!isDown){
    			if(getY() > 140){
    				setNext(0, inverse(getSPEED()));
    			}
    		}
    		if(!isLeft){
    			setNext(inverse(getSPEED()), 0);
    		}
    		if(!isRight){
    			setNext(getSPEED(), 0);
    		}
    	}
    }
    
	public void setDown(boolean is) {
		isDown = is;
	}

	public void setLeft(boolean is) {
		isLeft = is;
	}

	public void setRight(boolean is) {
		isRight = is;
	}

	public void setUp(boolean is) {
		isUp = is;
	}
	
	public void clear(){
		isDown  = false;
		isUp    = false;
		isRight = false;
		isLeft  = false;
	}
}
