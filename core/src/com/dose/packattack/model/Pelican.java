package com.dose.packattack.model;

import java.util.Random;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;
import static com.dose.packattack.model.MyWorld.*;
import static com.dose.packattack.view.WorldRenderer.FULL_WIDTH;

public class Pelican extends WorldObjectMove {

	private boolean isCreateBlock;
	private int dropBlockX;
	
    public Pelican(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
        setDirectionHorizontal(EDirection.RIGHT);
    }

    @Override
	public void animate() {

        if (getX() % 8 == 0)
            countImage++;
        if (countImage == 4)
            countImage = 0;
        switch (countImage) {
        case 0:setTexture(ETexture.PELICAN_1);break;
        case 1:setTexture(ETexture.PELICAN_2);break;
        case 2:setTexture(ETexture.PELICAN_3);break;
        case 3:setTexture(ETexture.PELICAN_4);break;

        default:
            break;
        }
    }

    public void move() {
        switch (getDirectionHorizontal()) {
        case RIGHT:
            setNext(getSPEED(), 0);
            inversTexture = false;
            break;
        case LEFT:
            setNext(inverse(getSPEED()), 0);
            inversTexture = true;
            break;
		default:break;
        }
        setX(getRectangle().getX());
        if (getX() > FULL_WIDTH) {
        	setDirectionHorizontal(EDirection.LEFT);
            if (!isCreateBlock) {
                isCreateBlock = true;
                dropBlockX = new Random().nextInt(LINE_BLOCKS);
            }
        }
        if (getX() < -getWidth()) {
        	setDirectionHorizontal(EDirection.RIGHT);
            if (!isCreateBlock) {
                isCreateBlock = true;
                dropBlockX = new Random().nextInt(LINE_BLOCKS);
            }
        }
        animate();

    }

    public void createBlock(MyWorld world) {
        if (isCreateBlock && getX() == dropBlockX * SIZE_BLOCK) {
            if (!world.check(new Rectangle(dropBlockX * SIZE_BLOCK, getY(), SIZE_BLOCK, SIZE_BLOCK))) {
                world.createBlock(getX(), getY());
                isCreateBlock = false;
            }
        } else {
            dropBlockX = new Random().nextInt(LINE_BLOCKS);
        }
    }

}
