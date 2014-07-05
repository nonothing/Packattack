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
		player = new Player(ETexture.PLAYER_0, 80, 178, 80, 80);
		random = new Random();
		for (int i = 0; i < 5; i++)
			createPelican();
		
		RECT= new Rectangle(0,140, 1280, 32);
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
		if(player.getRectangle().intersects(RECT) || collisionWithBlock(player.getRectangle())!= EMPTY_BLOCK){
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
			blocks.get(i).moveV();
			if(!checkCollision(i,true)){
				blocks.get(i).newPositionY();
			} else{
				blocks.get(i).setNext(0, 0);
			}
			
			blocks.get(i).moveH();
			if(!checkCollision(i, false))
				blocks.get(i).newPositionX();
			
		}
		cheakLine();
	}
	
	public boolean checkCollision(int i, boolean V){
		blocks.get(i).clear();
		boolean result = false;
		for(int j=0; j < blocks.size(); j++){
			if(i != j && blocks.get(i).getRectangle().intersects(blocks.get(j).getRectangle())){
				if(V){
					if(blocks.get(i).getY() >= blocks.get(j).getY()){
						blocks.get(i).setDown(true);
						blocks.get(j).setUp(true);
					}
				} else {
					if(blocks.get(j).getX() >= blocks.get(i).getX()){
						blocks.get(j).setLeft(true);
						blocks.get(i).setRight(true);
					}else{
						blocks.get(i).setLeft(true);
						blocks.get(j).setRight(true);
					}
				}
				if(!result)result = true;
			}
		}
		return result;
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
			for(Block block: blocks){
					block.isDown = false;
					block.isUp = false;
			}	
		}
		
	}
	
	private int[] collisionWithBlocks(Rectangle rectangle){
		int result[] = new int[blocks.size()];
		for(int i=0 ; i < blocks.size(); i++){
			if(blocks.get(i).getRectangle().intersects(rectangle)){
				result[i] = i;
			}else{
				result[i] = EMPTY_BLOCK;
			}
		}
		return result;
	}
	
	private int collisionWithBlock(Rectangle rectangle){
		for(int i=0 ; i < blocks.size(); i++){
			if(blocks.get(i).getRectangle().intersects(rectangle)){
				return i;
			}
		}
		return EMPTY_BLOCK;
	}
	
	public boolean check(Rectangle rectangle){
		for(Block block: blocks){
			if(block.getRectangle().intersects(rectangle)){
				return true;
			}
		}
		return false;
	}
	
	private static final int EMPTY_BLOCK = 99999;
	
	private void movePlayer(EDirection directionHorizonal) {
			player.setDirectionHorizontal(directionHorizonal);
		player.moveH();
		int[] moveBlocks = collisionWithBlocks(player.getRectH());
		if(collisionWithBlock(player.getRectangle()) != EMPTY_BLOCK){
			player.setNext(0, 0);
			for(int i=0; i < blocks.size(); i++){
				int moveBlock = moveBlocks[i];
				if(moveBlock != EMPTY_BLOCK){
					if(!blocks.get(moveBlock).isUp && (blocks.get(moveBlock).getY()-170)%80==0){
						if(blocks.get(moveBlock).getX() > player.getX() && player.getDirectionHorizontal() == EDirection.RIGHT){
							blocks.get(moveBlock).isActive = true;
							blocks.get(moveBlock).setDirectionHorizontal(EDirection.RIGHT);
							blocks.get(moveBlock).moveH();
							if(checkCollision(i, false)){
								blocks.get(moveBlock).setNext(-4, 0);
								blocks.get(moveBlock).newPositionX();
							}
						} 
						if(blocks.get(moveBlock).getX() < player.getX() && player.getDirectionHorizontal() == EDirection.LEFT){
							blocks.get(moveBlock).isActive = true;
							blocks.get(moveBlock).setDirectionHorizontal(EDirection.LEFT);
							blocks.get(moveBlock).moveH();
							if(checkCollision(i, false)){
								blocks.get(moveBlock).setNext(4, 0);
								blocks.get(moveBlock).newPositionX();
							} 
						} 
					} 
				}
			}
		}
		if(collisionWithBlock(player.getRectangle()) == EMPTY_BLOCK){
			player.newPositionX();
		}
		
		player.moveV();
		System.out.println(player.getY());
		if(!player.getRectangle().intersects(RECT) && collisionWithBlock(player.getRectangle()) == EMPTY_BLOCK ){
			player.newPositionY();
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
