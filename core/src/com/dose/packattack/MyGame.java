package com.dose.packattack;

import com.badlogic.gdx.Game;
import com.dose.packattack.controller.MenuController;
import com.dose.packattack.controller.WorldController;
import com.dose.packattack.interfaces.IActivityRequestHandler;
import com.dose.packattack.interfaces.ILink;
import com.dose.packattack.model.MyWorld;
import com.dose.packattack.view.Images;
import com.dose.packattack.view.Logo;
import com.dose.packattack.view.MenuRenderer;
import com.dose.packattack.view.WorldRenderer;


public class MyGame extends   Game  {
	
	public static Config cfg;
	public static ILink links;
	MenuController menuController;
	WorldController worldController;
	private Logo logo;
	private IActivityRequestHandler mAds;
	private Images images;
	
	public MyGame (IActivityRequestHandler ads){
		mAds = ads;
	}
	public MyGame (){
	}
	
	@Override
	public void create() {
		images = new Images();
		menuController = new MenuController(this, new MenuRenderer());
		worldController = new WorldController(this, new MyWorld(), new WorldRenderer(images));
		
		if(!cfg.isDebug()){
			showAds(false);
			logo = new Logo(this);
			setScreen(logo);
		}else{
			worldController.setInput();
			setScreen(worldController.getWorldRenderer());
		}
	}
	
	@Override
	public void dispose() {
		worldController = null;
//		batch.dispose();TODO
//		texture.dispose();TODO
		super.dispose();
	}
	
	public void changeMenu(){
		showAds(true);
		menuController.setInput();
		setScreen(menuController.getMenuRenderer());
	}
	
	public void changeGame(){
		showAds(false);
		worldController.setInput();
		setScreen(worldController.getWorldRenderer());
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	
	private void showAds(boolean show){
		if(mAds != null){
			mAds.showAdMob(show);
		}
	}
}
