package com.dose.packattack.view;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dose.packattack.MyGame;

import static com.dose.packattack.MyGame.cfg;

public class Logo implements Screen{
	
	private SpriteBatch spriteBatch;
	private Texture dose;
	private Texture dose1;
	private Texture dose2;
	private Texture dose3;
	private Texture dose4;
	private Texture dose5;
	private Texture dose6;
	private Texture line;
	private Texture lineClear;
	private MyGame game;
	private Timer timer;
	private int count;
	private float alpha;
	private boolean hideRect ;
	private float showLine;
	
	public Logo(MyGame game) {
		this.game = game;
		spriteBatch = new SpriteBatch();
		dose  = new Texture(Gdx.files.internal("images/logo/logo_dose.png"));
		dose1  = new Texture(Gdx.files.internal("images/logo/dose1.png"));
		dose2  = new Texture(Gdx.files.internal("images/logo/dose2.png"));
		dose3  = new Texture(Gdx.files.internal("images/logo/dose3.png"));
		dose4  = new Texture(Gdx.files.internal("images/logo/dose4.png"));
		dose5  = new Texture(Gdx.files.internal("images/logo/dose5.png"));
		dose6  = new Texture(Gdx.files.internal("images/logo/dose6.png"));
		dose6  = new Texture(Gdx.files.internal("images/logo/dose6.png"));
		line = new Texture(Gdx.files.internal("images/logo/line.png"));
		lineClear = new Texture(Gdx.files.internal("images/logo/line_clear.png"));
		timer = new Timer();
		alpha = -1;
		timer.schedule(showLogo, 1, 20);
		timer.schedule(moveLine, 1, 5);
		
	}
	
	TimerTask showLogo = new TimerTask() {
		@Override
		public void run() {
			if(alpha <= -0.8){
				alpha+=0.001;
			}
			
		}
	};
	
	TimerTask moveLine = new TimerTask() {
		@Override
		public void run() {
			if(alpha >= -0.95f){
				showLine++;
				if(showLine > cfg.getWidth()){
						hideRect = true;
					}
				alpha = 2;
			}
			if(hideRect){
				count++;
			}
		}
	};
	
	private void animates() {
		if (count < 10) {
			spriteBatch.draw(dose1,0,0,cfg.getWidth(),cfg.getHeight());
		}
		else if (count < 20) {
			spriteBatch.draw(dose2,0,0,cfg.getWidth(),cfg.getHeight());
		}
		else if (count < 30) {
			spriteBatch.draw(dose3,0,0,cfg.getWidth(),cfg.getHeight());
		}
		else if (count < 40) {
			spriteBatch.draw(dose4,0,0,cfg.getWidth(),cfg.getHeight());
		}
		else if (count < 50) {
			spriteBatch.draw(dose5,0,0,cfg.getWidth(),cfg.getHeight());
		}
		else if (count < 60) {
			spriteBatch.draw(dose6,0,0,cfg.getWidth(),cfg.getHeight());
		}
		else if (count > 60) {
			game.changeMenu();
		}
	}
	
	@Override
	public void render(float delta) {
		spriteBatch.setColor(1.0f, 1.0f, 1.0f, alpha);
		spriteBatch.begin();
		if(!hideRect){
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        spriteBatch.draw(dose,0,0,cfg.getWidth(),cfg.getHeight());
	        Gdx.gl.glDisable(GL20.GL_BLEND);
	        if(alpha>=1.0){
	        	spriteBatch.draw(line,0,(int)(300*cfg.getScaleY()),(int)(1280*cfg.getScaleX()), (int)(51 *cfg.getScaleY()));
	        	spriteBatch.draw(lineClear,showLine,(int)(300*cfg.getScaleY()),(int)(1280*cfg.getScaleX()), (int)(51 * cfg.getScaleY()));
	        }
		}else{
			animates();
		}
		spriteBatch.end();
	}

	@Override	public void resize(int arg0, int arg1) {}
	@Override	public void resume()  {}
	@Override	public void show()    {}
	@Override	public void dispose() {}
	@Override	public void hide()    {}
	@Override	public void pause()   {}

}
