package com.dose.packattack.view;

import static com.dose.packattack.MyGame.cfg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dose.packattack.enumerate.ETexture;
import com.dose.packattack.model.Block;
import com.dose.packattack.model.JButton;
import com.dose.packattack.model.MyWorld;
import com.dose.packattack.model.Pelican;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.model.Score;
import com.dose.packattack.model.WorldObjectMove;

public class WorldRenderer implements Screen{
	
	public static final int FULL_HEIGHT = 800 ;
	public static final int FULL_WIDTH = 1280;
	
	private MyWorld world;
	private SpriteBatch batch;
	private Images images;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonUp;
	private JButton buttonPause;
	private ShapeRenderer renderer;
	private PopapMenuRenderer popapMenuRenderer;
	private Score score;
	
	public WorldRenderer(Images images) {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		this.images = images;
		popapMenuRenderer = new PopapMenuRenderer(images);
		score = new Score(images,FULL_WIDTH-130,FULL_HEIGHT-55,30,35);
		buttonLeft = new JButton("btn_move", 30, 40, 79, 76);
		buttonRight = new JButton("btn_move", 120, 40, 79, 76);
		buttonUp = new JButton("btn_move", 900, 40, 79, 76);
		buttonPause = new JButton("btn_pause", 16, 720, 64, 64);
		score.setCount(1);
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
		popapMenuRenderer.draw(batch);
		score.draw(batch);
		batch.end();
		
//		drawDebug();
	}

	private void drawDebug() {
		renderer.begin(ShapeType.Line);
		drawLinesObjct(world.getPlayer());
		for(Block block : world.getBlocks()){
			drawLinesObjct(block);
		}
		renderer.setColor(Color.BLACK);
		
		renderer.end();
	}
	
	private void drawButton(){
		batch.draw(buttonPause.getImage(), buttonPause.getX(), buttonPause.getY(), buttonPause.getWidth(), buttonPause.getHeight());
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

	private void drawLinesObjct(WorldObjectMove object){
		Rectangle r = object.getRectangle();
		//crossroad
		renderer.line(r.getX(), r.getY(), r.getX() + r.getWigth(),r.getY() + r.getHeight());
		renderer.line(r.getX(), r.getY() + r.getHeight(), r.getX() + r.getWigth(),r.getY());
		//lines
		renderer.line(r.getX(), r.getY(), r.getX() + r.getWigth(),r.getY());
		renderer.line(r.getX(), r.getY() + r.getHeight(), r.getX() + r.getWigth(),r.getY() + r.getHeight());
		
		renderer.line(r.getX(), r.getY(), r.getX(),r.getY() + r.getHeight());
		renderer.line(r.getX()+r.getWigth(), r.getY(), r.getX()+r.getWigth(),r.getY() + r.getHeight());
		
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
	
	public JButton getButtonPause(){
		return buttonPause;
	}
	
	public PopapMenuRenderer getPopapMenu(){
		return popapMenuRenderer;
	}
	
	public Score getScore(){
		return score;
	}
	
	@Override	public void dispose() {	}
	@Override	public void hide() {	}
	@Override	public void pause() {	}
	@Override	public void resize(int arg0, int arg1) {	}
	@Override	public void resume() {	}
	@Override	public void show() {	}
		
}
