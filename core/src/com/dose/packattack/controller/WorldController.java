package com.dose.packattack.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.dose.packattack.MyGame;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.model.JButton;
import com.dose.packattack.model.MyWorld;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.view.WorldRenderer;

public class WorldController implements InputProcessor {

    private MyWorld world;
    private Timer mainTimer;
    private MenuController menuController;
    private SoundController soundController;
	private WorldRenderer worldRenderer;
	private MyGame myGame;
	private boolean isTouch;
	private Rectangle rectangle;
	private EDirection directionHorizontal;
    private EDirection directionVertical;
    
    public WorldController(MyGame myGame, MyWorld world, WorldRenderer worldRenderer)  {
        this.menuController = menuController;
        this.world = world;
        this.mainTimer = new Timer();//TODO timer 10
        this.mainTimer.schedule(timerTask, 1, 10);
        this.soundController = soundController; 
		this.worldRenderer = worldRenderer;
		this.myGame = myGame;
		worldRenderer.setWorld(world);
     }

    TimerTask timerTask = new TimerTask() {
		
		@Override
		public void run() {
			 world.goPlayer(directionHorizontal, directionVertical);
		}
	};
	
	public void setInput(){
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		createRectangle(x, y, true);
		touchButton();
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		createRectangle(x, y, true);
		touchButton();
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		createRectangle(x, y, false);
		touchButton();
		return true;
	}
	
	private void touchButton(){
		if(touchButton(worldRenderer.getButtonLeft())){
			directionHorizontal = EDirection.LEFT;
		}
		
		if(touchButton(worldRenderer.getButtonRight())){
			directionHorizontal = EDirection.RIGHT;
		} 
		
		if(touchButton(worldRenderer.getButtonUp())){
			directionVertical = EDirection.UP;
		} 
	}
	
	private boolean touchButton(JButton button){
		if (button.getRectangle().intersects(rectangle)) {
			button.setZoom(isTouch);
			if(!isTouch){
				directionHorizontal = EDirection.NONE;
		        directionVertical = EDirection.NONE;
			}
			return (isTouch)?true:false;
		}else{
			button.setZoom(false);
			return false;
		}	
	}
	
	private void createRectangle(int x, int y, boolean touch) {
		isTouch = touch;
		rectangle = new Rectangle(x,Gdx.graphics.getHeight() - y , 2, 2);
	}
	
	public WorldRenderer getWorldRenderer(){
		return worldRenderer;
	}
	
	@Override	public boolean keyDown(int keyCode) {		return true;	}
	@Override	public boolean keyTyped(char arg0) {		return false;	}
	@Override	public boolean keyUp(int keyCode) {		return true;	}
	@Override	public boolean mouseMoved(int x, int y) {		return true;	}
	@Override	public boolean scrolled(int arg0) {		return false;	}

}
