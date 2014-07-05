package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;
public class Block extends WorldObjectMove {

	public boolean isLeft;
	public boolean isRight;
	public boolean isDown;
	public boolean isUp;
	public boolean isActive;
	private static final int SIZE = 80;
    public boolean isDead;
    
    public Block(ETexture texture, int x, int y) {
        super(texture, x, y, SIZE, SIZE);
        setDirectionHorizontal(EDirection.NONE);
    }

    @Override
    void animate() {
        // do nothing
    }
    
	public void moveH() {
		if (!isLeft && getDirectionHorizontal() == EDirection.LEFT && isActive) {
			if (getX() > 0)
				setNext(inverse(getSPEED()), 0);
			if (getRectangle().getX() % 80 == 0) {
				setDirectionHorizontal(EDirection.NONE);
				isActive = false;
			}
		}
		if (!isRight && getDirectionHorizontal() == EDirection.RIGHT
				&& isActive) {
			if (getX() < 1280 - getWidth())
				setNext(getSPEED(), 0);
			if (getRectangle().getX() % 80 == 0) {
				setDirectionHorizontal(EDirection.NONE);
				isActive = false;
			}
		}
	}

	public void moveV() {
		if (!isUp) {
			if (!isDown) {
				if (getY() > 172) {
					setNext(0, inverse(getSPEED()));
				} else {
					isDown = true;
				}
			}
		}
	}

	public boolean isActive(){
		return getDirectionHorizontal() == EDirection.NONE;
			
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
		isRight = false;
		isLeft  = false;
	}
	
}
