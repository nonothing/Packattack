package com.dose.packattack.model;

import com.dose.packattack.model.Rectangle;
import com.dose.packattack.enumerate.ETexture;
import static com.dose.packattack.MyGame.cfg;

public class WorldObject {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle rectangle;
    private ETexture texture;
   
    public WorldObject(ETexture texture, int x, int y, int width, int height) {
        this.texture = texture;
        this.x = (int)(x*cfg.getScaleX());
        this.y = (int)(y*cfg.getScaleY());
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x, y, width, height);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return (int)(width * cfg.getScaleX());
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return (int)(height*cfg.getScaleY()); 
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public ETexture getTexture() {
        return texture;
    }

    public void setTexture(ETexture texture) {
        this.texture = texture;
    }

}
