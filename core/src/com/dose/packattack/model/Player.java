package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;
import static com.dose.packattack.MyGame.cfg;

public class Player extends WorldObjectMove {
    
    private boolean isDead;
    private boolean isGround;
    public Player(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    public boolean isGravity;
    void animate() {

        if (getX() % 32 == 0)
            countImage++;
        if (countImage == 6)
            countImage = 0;
        switch (countImage) {
        case 0:
            setTexture(ETexture.PLAYER_1);
            break;
        case 1:
            setTexture(ETexture.PLAYER_2);
            break;
        case 2:
            setTexture(ETexture.PLAYER_3);
            break;
        case 3:
            setTexture(ETexture.PLAYER_4);
            break;
        case 4:
            setTexture(ETexture.PLAYER_5);
            break;
        case 5:
            setTexture(ETexture.PLAYER_6);
            break;

        default:
            break;
        }
    }
    
    private boolean stop;

    public void move(EDirection direction, boolean vertical) {
        stop = false;
        if (getY() > (int)(160*cfg.getScaleY()))
            isGravity = true;
        else{
            isGravity = false;
            isGround = true;
            }
        if (direction == EDirection.RIGHT && !vertical) {
            setNext(getSPEED(), 0);
            inversTexture = false;
        }
        if (direction == EDirection.LEFT && !vertical) {
            setNext(inverse(getSPEED()), 0);
            inversTexture = true;
        }
        if (direction == EDirection.UP && vertical && isGround) {
            setNext(0, getSPEED()*40);
            isGravity = false;
        }
        if(direction == EDirection.NONE){
            stop = true;
        }
        
        if(isGravity && vertical)  setNext(0, inverse(getSPEED()*2));

        if (!stop)
            animate();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void setGround(boolean is) {
        isGround = is;
        
    }

}
