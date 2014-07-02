package com.dose.packattack.model;

import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;
import static com.dose.packattack.MyGame.cfg;
public class Block extends WorldObjectMove {

    public boolean isMoveHorizontal;
    public boolean isMoveVertical;
    public boolean isDead;
    
    public Block(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
        isMoveHorizontal = true;
        isMoveVertical = true;
    }

    @Override
    void animate() {
        // do nothing
    }
    public void move(EDirection direction, boolean vertical) {

        if (direction == EDirection.RIGHT && !vertical && getX() < (int)(1200*cfg.getScaleX())) {
            setNext(getSPEED(), 0);
        }
        if (direction == EDirection.LEFT && !vertical && getX() > 0) {
            setNext(inverse(getSPEED()), 0);

        }
        if (direction == EDirection.DOWN && vertical && getY() > (int)(160 * cfg.getScaleY())) {
            setNext(0, inverse(getSPEED()));
        }
        
        
    }
}
