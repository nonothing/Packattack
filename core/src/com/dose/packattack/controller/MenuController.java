package com.dose.packattack.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.dose.packattack.MyGame;
import com.dose.packattack.model.JButton;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.view.MenuRenderer;

public class MenuController implements InputProcessor{

	private MenuRenderer view;
	private boolean isTouch;
	private Rectangle rectangle;
	private MyGame game;
	
	public MenuController(MyGame myGame, MenuRenderer menuRenderer) {
		this.view = menuRenderer;
		this.game = myGame;
	}
	
	public void setInput() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchButton(x, y, true);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		touchButton(x, y, true);
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		touchButton(x, y, false);
		return false;
	}

	public void touchButton(int x, int y, boolean touch){
		isTouch = touch;
		rectangle = new Rectangle(x,Gdx.graphics.getHeight() - y , 2, 2);
		if(touchButton(view.getButtonAbout())){
			
		}
		
		if(touchButton(view.getButtonPlay())){
			game.changeGame();
		}
		
		if(touchButton(view.getButtonRate())){
			game.links.showMyGame();
		}
		
		if(touchButton(view.getButtonMoreGames())){
			game.links.showMoreGames();
		}
	}
	
	private boolean touchButton(JButton button){
		if (button.getRectangle().intersects(rectangle)) {
			button.setZoom(isTouch);
			return (!isTouch)?true:false;
		}else{
			button.setZoom(false);
			return false;
		}	
	}
	
	public MenuRenderer getMenuRenderer(){
		return view;
	}
	
	@Override	public boolean keyDown(int arg0) {		return false;	}
	@Override	public boolean keyTyped(char arg0) {		return false;	}
	@Override	public boolean keyUp(int arg0) {		return false;	}
	@Override	public boolean mouseMoved(int arg0, int arg1) {		return false;	}
	@Override	public boolean scrolled(int arg0) {		return false;	}
	
}
