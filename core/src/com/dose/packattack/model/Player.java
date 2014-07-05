package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;

public class Player extends WorldObjectMove {
    
    private boolean isDead;
    private int countHeightJump;
    
    public Player(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
        setDirectionVertical(EDirection.DOWN);
        setDirectionHorizontal(EDirection.NONE);
    }

    public boolean isGravity;
    void animate() {
        switch (countImage) {
        case 0:  setTexture(ETexture.PLAYER_1);            break;
        case 8:  setTexture(ETexture.PLAYER_2);            break;
        case 16: setTexture(ETexture.PLAYER_3);            break;
        case 24: setTexture(ETexture.PLAYER_4);            break;
        case 32: setTexture(ETexture.PLAYER_5);            break;     
        case 40: setTexture(ETexture.PLAYER_6);            break;
        default:
            break;
        }
        
        countImage++;
        if (countImage == 48){
        	countImage = 0;
        }
    }
    
    public void moveH() {
    	if(getDirectionHorizontal() != EDirection.NONE){
    		animate();
    	} else {
    		setTexture(ETexture.PLAYER_0);
    	}
    	
        if (getDirectionHorizontal() == EDirection.RIGHT && getX() < 1280 - getWidth()) {
            setNext(getSPEED(), 0);
            inversTexture = false;
        }
        
        if (getDirectionHorizontal() == EDirection.LEFT && getX() > 0) {
            setNext(inverse(getSPEED()), 0);
            inversTexture = true;
        }
       
    }
    
    public void moveV(){
    	if(getDirectionVertical() == EDirection.DOWN){
    		setNext(0, inverse(getSPEED()));
    	}
    	
    	if(getDirectionVertical() == EDirection.UP){
    		setNext(0, getSPEED()*2);
    		countHeightJump++;
    		if(countHeightJump >= 15){
    			countHeightJump = 0;
    			setDirectionVertical(EDirection.DOWN);
    		}
    	}
    }
    
    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
    
    public Rectangle getRectH(){
    	return new Rectangle(getRectangle().getX(), getRectangle().getY(), getRectangle().getWigth(), getRectangle().getHeight() - 30);
    }
    
}
