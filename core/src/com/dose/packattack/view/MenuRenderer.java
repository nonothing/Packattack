package com.dose.packattack.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuRenderer implements Screen{
	
	private SpriteBatch spriteBatch;
	Texture texture;
	
	public MenuRenderer() {
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	private void loadTextures() {
//		texture  = new Texture(Gdx.files.internal("images/hold.png"));
	}
	
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor( 1, 1, 1, 1 );
	    Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		spriteBatch.begin();
//		spriteBatch.draw(texture,0,0,(int)(1280*scaleX), (int)(750 *scaleY));
		spriteBatch.end();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}
		
}
