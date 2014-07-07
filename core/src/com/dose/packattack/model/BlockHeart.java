package com.dose.packattack.model;

import com.dose.packattack.enumerate.ETexture;

public class BlockHeart extends Block{
	
	private static final float MIN_ZOOM = 0.9f;
	private float zoom;
	private int offsetX;
	private int offsetY;
	
	public BlockHeart(ETexture texture, int x, int y) {
		super(texture, x, y);
		zoom = 1;
	}

	@Override
	public void moveV() {
		animate();
		super.moveV();
	}
	private void changeZoom(){
		if(zoom == 1.0f){
			setOffset(zoom = MIN_ZOOM);
		} else {
			setOffset(zoom = 1.0f);
		}
	}

	private void setOffset(float zoom) {
		offsetX = ((int) (getWidth() * zoom) - getWidth()) / 2;
		offsetY = ((int) (getHeight() * zoom) - getHeight()) / 2;
	}
	
	@Override
	public void animate() {
		if(countImage > 150){
			if(countImage%15==0){
				changeZoom();
			}
		}
		countImage++;
	}
	
	@Override
	public int getWidth() {
		return (int)(width*zoom);
	}
	
	@Override
	public int getHeight() {
		return (int)(height*zoom);
	}
	
	@Override
	public int getX() {
		return x - offsetX;
	}
	
	@Override
	public int getY() {
		return y - offsetY;
	}
	
}
