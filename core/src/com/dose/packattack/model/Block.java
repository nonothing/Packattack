package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;
import static com.dose.packattack.model.MyWorld.SIZE_BLOCK;
import static com.dose.packattack.view.WorldRenderer.FULL_WIDTH;

public class Block extends WorldObjectMove {

	private static final int BOTTOM = 172;
	public boolean isLeft;
	public boolean isRight;
	public boolean isDown;
	public boolean isUp;
	public boolean isActive;
    public boolean isDead;
    public Block(ETexture texture, int x, int y) {
        super(texture, x, y, SIZE_BLOCK, SIZE_BLOCK);
        setDirectionHorizontal(EDirection.NONE);
    }

	public void moveH() {
		if (!isLeft && getDirectionHorizontal() == EDirection.LEFT && isActive) {
			if (getX() > 0)
				setNext(inverse(getSPEED()), 0);
			if (getRectangle().getX() % SIZE_BLOCK == 0) {
				setDirectionHorizontal(EDirection.NONE);
				isActive = false;
			}
		}
		if (!isRight && getDirectionHorizontal() == EDirection.RIGHT && isActive) {
			if (getX() < FULL_WIDTH - getWidth())
				setNext(getSPEED(), 0);
			if (getRectangle().getX() % SIZE_BLOCK == 0) {
				setDirectionHorizontal(EDirection.NONE);
				isActive = false;
			}
		}
	}

	public void moveV() {
		if (!isUp) {
			if (!isDown) {
				if (getY() > BOTTOM) {
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
	
	@Override
	public void animate() {
		// do nothing
	}
	
	public void clear(){
		isDown  = false;
		isRight = false;
		isLeft  = false;
	}
	
}
