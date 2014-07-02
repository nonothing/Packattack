package com.dose.packattack.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.dose.packattack.MyGame.cfg;

public class JButton {

	private int x;
	private int y;
	private int width;
	private int height;
	private Texture texture;
	private Rectangle rectangle;
	private float zoom;
	private int offsetX;
	private int offsetY;
	private String name;
	
	public JButton(String image, int x, int y) {
		name = image;
		texture = new Texture(Gdx.files.internal("images/buttons/" + image + ".png"));
		this.x = x;
		this.y = y;
		width  = texture.getWidth();
		height = texture.getHeight();
		zoom = 1.0f;
		offsetX = 0;
		offsetY = 0;
		rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public JButton(String image, int x, int y, int width, int height) {
		this(image, x, y);
		this.width  = width;
		this.height = height;
		rectangle = new Rectangle(getX() , getY(), getWidth(), getHeight());
	}
	
	public void setTexture(String image){
		name = image;
		texture = new Texture(Gdx.files.internal("images/buttons/" + image + ".png"));
		width  = texture.getWidth();
		height = texture.getHeight();
		rectangle = new Rectangle(getX() , getY(), getWidth(), getHeight());
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return (int) (x * cfg.getScaleX()) - offsetX;
	}

	public int getY() {
		return (int) (y * cfg.getScaleY()) -offsetY;
	}

	public int getWidth() {
		return (int) (width * cfg.getScaleX() * zoom);
	}

	public int getHeight() {
		return (int) (height * cfg.getScaleY() * zoom);
	}
	
	public void setZoom(boolean isZoom) {
		if (isZoom == true) {
			zoom = 0.9f;
			offsetX = ((int) (getWidth() * zoom) - getWidth()) / 2;
			offsetY = ((int) (getHeight() * zoom) - getHeight()) / 2;
		} else {
			zoom = 1.0f;
			offsetX = 0;
			offsetY = 0;
		}
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public TextureRegion getImage(){
		return new TextureRegion(texture);
	}
	
	public String getName(){
		return name;
	}
}
