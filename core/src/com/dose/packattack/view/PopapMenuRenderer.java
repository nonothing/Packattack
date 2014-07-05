package com.dose.packattack.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dose.packattack.enumerate.ETexture;
import com.dose.packattack.model.JButton;

import static com.dose.packattack.MyGame.cfg;
import static com.dose.packattack.controller.WorldController.PAUSE;
public class PopapMenuRenderer {
	
	private Images images;
	private final int BACKGROUND_WIDTH = (int)(548 * cfg.getScaleX());
	private final int BACKGROUND_HEIGH = (int)(330 * cfg.getScaleY());
	private final int BACKGROUND_X = cfg.getWidth()/2 - BACKGROUND_WIDTH/2;
	private final int BACKGROUND_Y = cfg.getHeight()/2 - BACKGROUND_HEIGH/2;
	private JButton buttonNext;
	private JButton buttonRetry;
//	private JButton buttonShowLevel;
	
	public PopapMenuRenderer(Images images){
		this.images = images;
		buttonNext = new JButton("btn_next", 724, 247, 106, 109);
		buttonRetry = new JButton("btn_retry", 594, 247, 106, 109);
//		buttonShowLevel = new JButton("btn_showLevels", 463, 247, 106, 109);
	}
	
	public void draw(SpriteBatch batch){
//		batch.draw(imageWorld.getTexture(ETexture.TEST), BACKGROUND_X, BACKGROUND_Y,BACKGROUND_WIDTH,BACKGROUND_HEIGH);
		if(PAUSE ){
			batch.draw(images.getTexture(ETexture.POPAP_BACKGROUND), BACKGROUND_X, BACKGROUND_Y,BACKGROUND_WIDTH,BACKGROUND_HEIGH);
			batch.draw(buttonNext.getImage(), buttonNext.getX(), buttonNext.getY(), buttonNext.getWidth(), buttonNext.getHeight());
			batch.draw(buttonRetry.getImage(), buttonRetry.getX(), buttonRetry.getY(), buttonRetry.getWidth(), buttonRetry.getHeight());
//			batch.draw(buttonShowLevel.getImage(), buttonShowLevel.getX(), buttonShowLevel.getY(), buttonShowLevel.getWidth(), buttonShowLevel.getHeight());
		} 
	}
	
	public JButton getButtonNext(){
		return buttonNext;
	}
	
	public JButton getButtonRetry(){
		return buttonRetry;
	}
	
//	public JButton getButtonShowLevel(){
//		return buttonShowLevel;
//	}
}
