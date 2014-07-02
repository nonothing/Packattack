package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;

public abstract class WorldObjectMove extends WorldObject{

    private EDirection direction;
    private final int SPEED = 4;
    protected int countImage;
    protected boolean inversTexture;
    public WorldObjectMove(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
        direction = EDirection.RIGHT;
        countImage = 0;
        inversTexture = false;
    }
    
    abstract void animate();

    public void setNext(int speedX, int speedY) {
        setRectangle(new Rectangle(getX() + speedX, getY() + speedY,
                getWidth(), getHeight()));
    }

    protected int inverse(int number) {
        return -1 * number;
    }

    public EDirection getDirection() {
        return direction;
    }

    public void setDirection(EDirection direction) {
        this.direction = direction;
    }

    public int getSPEED() {
        return SPEED;
    }

    public boolean isInversTexture(){
        return inversTexture;
    }
}
