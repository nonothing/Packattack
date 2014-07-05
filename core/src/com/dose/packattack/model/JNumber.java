package com.dose.packattack.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dose.packattack.enumerate.ETexture;
import com.dose.packattack.view.Images;
import static com.dose.packattack.MyGame.cfg;

public class JNumber {
	protected Images imageWorld;
	private final int WIDTH;
	private final int HEIGH;

	public JNumber(Images imageWorld, int w, int h) {
		this.imageWorld = imageWorld;
		WIDTH = (int) (w * cfg.getScaleX());
		HEIGH = (int) (h * cfg.getScaleY());
	}
	
	public JNumber(Images imageWorld) {
		this.imageWorld = imageWorld;
		 WIDTH = (int) (50 * cfg.getScaleX());
		 HEIGH = (int) (100 * cfg.getScaleY());
	}

	public void draw(SpriteBatch batch, short number, int x, int y) {
		switch (number) {
		case 1:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_1), x, y, WIDTH, HEIGH);	break;
		case 2:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_2), x, y, WIDTH, HEIGH);	break;
		case 3:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_3), x, y, WIDTH, HEIGH);	break;
		case 4:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_4), x, y, WIDTH, HEIGH);	break;
		case 5:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_5), x, y, WIDTH, HEIGH);	break;
		case 6:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_6), x, y, WIDTH, HEIGH);	break;
		case 7:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_7), x, y, WIDTH, HEIGH);	break;
		case 8:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_8), x, y, WIDTH, HEIGH);	break;
		case 9:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_9), x, y, WIDTH, HEIGH);	break;
		case 0:	batch.draw(imageWorld.getTexture(ETexture.DIGITAL_0), x, y, WIDTH, HEIGH);	break;
		}
	}
}
