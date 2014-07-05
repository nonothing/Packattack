package com.dose.packattack.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.dose.packattack.MyGame;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.model.JButton;
import com.dose.packattack.model.MyWorld;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.view.WorldRenderer;

public class WorldController implements InputProcessor {

    private MyWorld world;
    private Timer mainTimer;
	private WorldRenderer view;
	private MyGame myGame;
	private boolean isTouch;
	private Rectangle rectangle;
	private EDirection directionHorizontal;
    private EDirection directionVertical;
    public static boolean PAUSE;
    
    public WorldController(MyGame myGame, MyWorld world, WorldRenderer worldRenderer)  {
        this.world = world;
        this.mainTimer = new Timer();//TODO timer 10
        this.mainTimer.schedule(timerTask, 0f, 0.02f);
        this.mainTimer.start();
		this.view = worldRenderer;
		this.myGame = myGame;
		worldRenderer.setWorld(world);
		directionHorizontal = EDirection.NONE;
		directionVertical = EDirection.NONE;
     }

    Task timerTask = new Task() {
		
		@Override
		public void run() {
			if(!PAUSE){
				world.goPlayer(directionHorizontal, directionVertical);
				view.getScore().setCount(world.getScore());
			}
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
		if(touchButton(view.getButtonLeft())){
			directionHorizontal = EDirection.LEFT;
		}
		
		if(touchButton(view.getButtonRight())){
			directionHorizontal = EDirection.RIGHT;
		} 
		
		if(touchButton(view.getButtonUp())){
			world.jumpPlayer();
			directionVertical = EDirection.UP;
		} 
		if(touchButton(view.getButtonPause())){
			if(PAUSE){
				PAUSE = false;
			} else {
				PAUSE = true;
			}
		} 
		
		if(PAUSE){
			if(touchButton(view.getPopapMenu().getButtonNext())){
				PAUSE = false;
			}
			if(touchButton(view.getPopapMenu().getButtonRetry())){
				world.newGame();
				PAUSE = false;
			}
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
		return view;
	}
	
	@Override	public boolean keyDown(int keyCode) {		return true;	}
	@Override	public boolean keyTyped(char arg0) {		return false;	}
	@Override	public boolean keyUp(int keyCode) {		return true;	}
	@Override	public boolean mouseMoved(int x, int y) {		return true;	}
	@Override	public boolean scrolled(int arg0) {		return false;	}

}
