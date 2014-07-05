package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;

public abstract class WorldObjectMove extends WorldObject{

    private EDirection directionHorizontal;
    private EDirection directionVertical;
    private final int SPEED = 4;
    protected int countImage;
    protected boolean inversTexture;
    
    public WorldObjectMove(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
        directionHorizontal = EDirection.RIGHT;
        countImage = 0;
        inversTexture = false;
    }
    
    abstract void animate();

    public void setNext(int speedX, int speedY) {
        setRectangle(new Rectangle(getX() + speedX, getY() + speedY,
                getWidth(), getHeight()));
    }
    
    public void newPosition(){
    	setX(getRectangle().getX());
    	setY(getRectangle().getY());
    }
    
	public void newPositionX() {
		setX(getRectangle().getX());
	}

	public void newPositionY() {
		setY(getRectangle().getY());
	}

    protected int inverse(int number) {
        return -1 * number;
    }

    public EDirection getDirectionHorizontal() {
        return directionHorizontal;
    }

    public void setDirectionHorizontal(EDirection direction) {
        this.directionHorizontal = direction;
    }
    
    public EDirection getDirectionVertical() {
    	return directionVertical;
    }
    
    public void setDirectionVertical(EDirection direction) {
    	this.directionVertical = direction;
    }

    public int getSPEED() {
        return SPEED;
    }

    public boolean isInversTexture(){
        return inversTexture;
    }
}
