package com.dose.packattack.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dose.packattack.enumerate.ETexture;
import com.dose.packattack.model.Block;
import com.dose.packattack.model.JButton;
import com.dose.packattack.model.MyWorld;
import com.dose.packattack.model.Pelican;
import com.dose.packattack.model.WorldObjectMove;

import static com.dose.packattack.MyGame.cfg;

public class WorldRenderer implements Screen{
	
	private MyWorld world;
	private SpriteBatch batch;
	private Images images;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonUp;
	
	public WorldRenderer(Images images) {
		batch = new SpriteBatch();
		this.images = images;
		buttonLeft = new JButton("btn_move", 10, 10, 79, 76);
		buttonRight = new JButton("btn_move", 100, 10, 79, 76);
		buttonUp = new JButton("btn_move", 900, 10, 79, 76);
	}
	
	public void setWorld(MyWorld world){
		this.world = world;
	}
	
	public void render(float delta) {
		clear();
		batch.begin();
		batch.draw(images.getTexture(ETexture.BACKGROUND), 0, 0, cfg.getWidth(), cfg.getHeight());
		
		for(Pelican pelican : world.getPelicans()){
			drawMoveObject(pelican);
		}
		
		for(Block block : world.getBlocks()){
			drawMoveObject(block);
		}
		drawMoveObject(world.getPlayer());
		drawButton();
		batch.end();
	}
	
	private void drawButton(){
		batch.draw(buttonLeft.getImage(), buttonLeft.getX(), buttonLeft.getY(), buttonLeft.getWidth(), buttonLeft.getHeight());
		batch.draw(buttonRight.getImage(), (float) buttonRight.getX(),
				(float) buttonRight.getY(), (float) buttonRight.getWidth() / 2,
				(float) buttonRight.getHeight() / 2,
				(float) buttonRight.getWidth(),
				(float) buttonRight.getHeight(), 1, 1, 180);
		batch.draw(buttonUp.getImage(), (float) buttonUp.getX(),
				(float) buttonUp.getY(), (float) buttonUp.getWidth() / 2,
				(float) buttonUp.getHeight() / 2,
				(float) buttonUp.getWidth(),
				(float) buttonUp.getHeight(), 1, 1, 270);
	}

	private void drawMoveObject(WorldObjectMove object) {
		if(!object.isInversTexture()){
			batch.draw(images.getTexture(object.getTexture()),
					(int)(object.getX()*cfg.getScaleX()),
					(int)(object.getY()*cfg.getScaleY()),
					(int)(object.getWidth()*cfg.getScaleX()),
					(int)(object.getHeight()*cfg.getScaleY()));
		} else {
			batch.draw(images.getTexture(object.getTexture()),
					(int)(object.getX()*cfg.getScaleX()) + (int)(object.getWidth()*cfg.getScaleX()),
					(int)(object.getY()*cfg.getScaleY()),
					(int)(-object.getWidth()*cfg.getScaleX()),
					(int)(object.getHeight()*cfg.getScaleY()));
		}
	}

	private void clear() {
		Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
	}
	
	public JButton getButtonLeft(){
		return buttonLeft;
	}
	
	public JButton getButtonRight(){
		return buttonRight;
	}
	
	public JButton getButtonUp(){
		return buttonUp;
	}
	
	@Override	public void dispose() {	}
	@Override	public void hide() {	}
	@Override	public void pause() {	}
	@Override	public void resize(int arg0, int arg1) {	}
	@Override	public void resume() {	}
	@Override	public void show() {	}
		
}
