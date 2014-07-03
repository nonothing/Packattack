package com.dose.packattack.model;

import java.util.ArrayList;
import java.util.Random;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;
import static com.dose.packattack.MyGame.cfg;

public class MyWorld {

	private Player player;
	private ArrayList<Block> blocks;
	private ArrayList<Pelican> pelicans;
	private final Rectangle RECT;
	private final Random random;

	public MyWorld() {
		blocks = new ArrayList<Block>();
		pelicans = new ArrayList<Pelican>();
		player = new Player(ETexture.PLAYER_0, 100, 720, 80, 100);
		random = new Random();
		for (int i = 0; i < 5; i++)
			createPelican();
		RECT= new Rectangle(0,(int)(140*cfg.getScaleY()), cfg.getWidth(), 25);
	}

	private void createPelican() {
		pelicans.add(new Pelican(ETexture.PELICAN_1,
				random.nextInt(cfg.getWidth() - (int) (110 * cfg.getScaleX())),
				690, 110, 100));
	}

	public void createBlock(int x, int y) {
		if (new Random().nextInt(2) == 0) {
			blocks.add(new Block(ETexture.CUBE_WOOD, x, y));
		} else {
			blocks.add(new Block(ETexture.CUBE_WOOD, x, y));
		}

	}
	
	public void jumpPlayer(){
		if(player.getRectangle().intersects(RECT)){
			player.setDirectionVertical(EDirection.UP);
		}
	}

	public void goPlayer(EDirection directionHorizonal,	EDirection directionVertical) {
		movePlayer(directionHorizonal);
		
		for (Pelican pelican : pelicans) {
			pelican.move();
			pelican.createBlock(this);
		}
	}

	private void movePlayer(EDirection directionHorizonal) {
		player.setDirectionHorizontal(directionHorizonal);
		player.moveH();
		if(!player.getRectangle().intersects(RECT)){
			player.newPosition();
		}
		player.moveV();
		if(!player.getRectangle().intersects(RECT)){
			player.newPosition();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public ArrayList<Pelican> getPelicans() {
		return pelicans;
	}
}
