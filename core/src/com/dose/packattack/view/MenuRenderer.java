package com.dose.packattack.view;


import static com.dose.packattack.MyGame.cfg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dose.packattack.enumerate.ETexture;
import com.dose.packattack.model.JButton;

public class MenuRenderer implements Screen{
	
	private SpriteBatch batch;
	private Images images;
	private JButton buttonPlay;
	private JButton buttonRate;
	private JButton buttonAbout;
	private JButton buttonMoreGames;
	
	public MenuRenderer(Images images) {
		this.images = images;
		batch = new SpriteBatch();
		buttonPlay = new JButton("btn_play", 528, 382, 229, 93);
		buttonRate = new JButton("btn_star", 110, 282, 168, 157);
		buttonAbout = new JButton("btn_developers", 1060, 482, 119, 168);
		buttonMoreGames = new JButton("btn_more_games_DOSE", 960, 172, 186, 217);
	}
	
	public void render(float delta) {
		clear();
		batch.begin();
		batch.draw(images.getTexture(ETexture.BACKGROUND), 0, 0, cfg.getWidth(), cfg.getHeight());
		batch.draw(buttonPlay.getImage(), buttonPlay.getX(), buttonPlay.getY(), buttonPlay.getWidth(), buttonPlay.getHeight());
		batch.draw(buttonAbout.getImage(), buttonAbout.getX(), buttonAbout.getY(), buttonAbout.getWidth(), buttonAbout.getHeight());
		batch.draw(buttonRate.getImage(), buttonRate.getX(), buttonRate.getY(), buttonRate.getWidth(), buttonRate.getHeight());
		batch.draw(buttonMoreGames.getImage(), buttonMoreGames.getX(), buttonMoreGames.getY(), buttonMoreGames.getWidth(), buttonMoreGames.getHeight());
		batch.end();
	}

	private void clear() {
		Gdx.graphics.getGL20().glClearColor( 1, 1, 1, 1 );
	    Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
	}
	
	public JButton getButtonPlay(){
		return buttonPlay;
	}
	
	public JButton getButtonAbout(){
		return buttonAbout;
	}
	
	public JButton getButtonRate(){
		return buttonRate;
	}
	
	public JButton getButtonMoreGames(){
		return buttonMoreGames;
	}

	@Override	public void dispose() {	}
	@Override	public void hide() {	}
	@Override	public void pause() {	}
	@Override	public void resize(int arg0, int arg1) {	}
	@Override	public void resume() {	}
	@Override	public void show() {	}
		
}
