package com.dose.packattack.model;

import java.util.Random;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;

public class Pelican extends WorldObjectMove {

    public Pelican(ETexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
        setDirection(EDirection.RIGHT);
    }

    @Override
    void animate() {

        if (getX() % 8 == 0)
            countImage++;
        if (countImage == 4)
            countImage = 0;
        switch (countImage) {
        case 0:
            setTexture(ETexture.PELICAN_1);
            break;
        case 1:
            setTexture(ETexture.PELICAN_2);
            break;
        case 2:
            setTexture(ETexture.PELICAN_3);
            break;
        case 3:
            setTexture(ETexture.PELICAN_4);
            break;

        default:
            break;
        }
    }

    private boolean isCreateBlock;
    private int dropBlockX;

    public void move() {
        switch (getDirection()) {
        case RIGHT:
            setNext(getSPEED(), 0);
            inversTexture = false;
            break;
        case LEFT:
            setNext(inverse(getSPEED()), 0);
            inversTexture = true;
            break;
        }
        setX(getRectangle().getX());
        if (getX() > 800) {
            setDirection(EDirection.LEFT);
            if (!isCreateBlock) {
                isCreateBlock = true;
                dropBlockX = new Random().nextInt(10);
            }
        }
        if (getX() < -getWidth()) {
            setDirection(EDirection.RIGHT);
            if (!isCreateBlock) {
                isCreateBlock = true;
                dropBlockX = new Random().nextInt(10);
            }
        }
        animate();

    }

    public void createBlock(MyWorld world) {

        if (isCreateBlock && getX() == dropBlockX * 80) {
            if (!world.collisionWithLevel(new Rectangle(dropBlockX * 80,
                    getY(), 80, 80))) {
                world.createBlock(getX(), getY());
                isCreateBlock = false;
            }
        } else {
            dropBlockX = new Random().nextInt(10);
        }
    }

}
