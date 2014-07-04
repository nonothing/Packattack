package com.dose.packattack.model;

import java.util.ArrayList;
import java.util.Random;
import com.dose.packattack.model.Rectangle;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;

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
		for (int i = 0; i < 1; i++)
			createPelican();
		RECT= new Rectangle(0,140, 1280, 25);
	}

	private void createPelican() {
		pelicans.add(new Pelican(ETexture.PELICAN_1,
				80 * random.nextInt(16),
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
		for(int i =0; i < blocks.size(); i++){
			blocks.get(i).move();
			checkCollision(i);
			blocks.get(i).newPosition();
		}
//		cheakLine();
	}
	
	public void checkCollision(int i){
		for(int j=0; j < blocks.size(); j++){
			if(i != j && blocks.get(j).getRectangle().intersects(blocks.get(i).getRectangle())){
				if(blocks.get(j).getX() > blocks.get(i).getX()){
					blocks.get(j).setLeft(true);
					blocks.get(i).setRight(true);
				}
				if(blocks.get(j).getX() < blocks.get(i).getX()){
					blocks.get(i).setLeft(true);
					blocks.get(j).setRight(true);
				}
				if(blocks.get(j).getY() < blocks.get(i).getY()){
					blocks.get(i).setDown(true);
					blocks.get(j).setUp(true);
				}
				if(blocks.get(j).getY() > blocks.get(i).getY()){
					blocks.get(j).setDown(true);
					blocks.get(i).setUp(true);
				}
			}
		}
	}
	
	private ArrayList<Block> buffer = new ArrayList<Block>(20);
	private void cheakLine(){
		buffer.clear();
		for(Block block: blocks){
			if(block.getRectangle().intersects(RECT)){
				block.isDead = true;
				buffer.add(block);
			}
		}
		if(buffer.size() >= 16){
			for(Block element: buffer){
				blocks.remove(element);
			}
		}
		
	}
	
	private boolean collisionWithBlock(Rectangle rectangle){
		for(Block block: blocks){
			if(block.getRectangle().intersects(rectangle)){
				return true;
			}
		}
		return false;
	}
	
	private void movePlayer(EDirection directionHorizonal) {
		player.setDirectionHorizontal(directionHorizonal);
		player.moveH();
		if(!player.getRectangle().intersects(RECT) && !collisionWithBlock(player.getRectangle())){
			player.newPosition();
		}
		player.moveV();
		if(!player.getRectangle().intersects(RECT) && !collisionWithBlock(player.getRectangle())){
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
