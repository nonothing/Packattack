package com.dose.packattack.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.dose.packattack.MyGame;
import com.dose.packattack.view.MenuRenderer;

public class MenuController implements InputProcessor{

	private MenuRenderer menuRenderer;
	private MyGame myGame;
	public MenuController(MyGame myGame, MenuRenderer menuRenderer) {
		this.menuRenderer = menuRenderer;
		this.myGame = myGame;
	}
	
	public void setInput(){
			Gdx.input.setInputProcessor(this);
	}
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		myGame.changeGame();
		return false;
	}

	public MenuRenderer getMenuRenderer(){
		return menuRenderer;
	}
	
}
