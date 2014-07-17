package com.dose.packattack.model;

import java.util.ArrayList;
import java.util.Random;

import com.dose.packattack.model.Rectangle;
import com.dose.packattack.enumerate.EDirection;
import com.dose.packattack.enumerate.ETexture;

import static com.dose.packattack.controller.WorldController.PAUSE;
import static com.dose.packattack.view.WorldRenderer.FULL_WIDTH;

public class MyWorld {

	private static final int BOTTOM = 170;
	public static final int LINE_BLOCKS = 16;
	private static final int EMPTY_BLOCK = 99999;
	public static final int SIZE_BLOCK = 80;
	
	private ArrayList<Block> bufferBlocks = new ArrayList<Block>(20);
	private Block boofBlock;
	
	private Player player;
	private ArrayList<Block> blocks;
	private ArrayList<Pelican> pelicans;
	private final Rectangle RECT;
	private final Rectangle RECT2;
	private final Random random;
	private int score;
	private boolean isHeart;
	private int count;

	public MyWorld() {
		blocks = new ArrayList<Block>();
		pelicans = new ArrayList<Pelican>();
		random = new Random();
		
		RECT= new Rectangle(0,140, FULL_WIDTH, 28);
		RECT2= new Rectangle(0,140, FULL_WIDTH, 40);
		newGame();
		}

	public void newGame(){
		score = 0;
		isHeart = false;
		blocks.clear();
		pelicans.clear();
		player = new Player(ETexture.PLAYER_STAY, 80, 178, 80, 80);
		player.setDead(false);
		for (int i = 0; i < 5; i++){
			createPelican();
		}
	}
	
	private void createPelican() {
		pelicans.add(new Pelican(ETexture.PELICAN_1,
				SIZE_BLOCK * random.nextInt(LINE_BLOCKS),
				690, 110, 100));
	}

	public void createBlock(int x, int y) {
		switch (random.nextInt(4)) {
		case 1:
			createBlock(ETexture.CUBE_WOOD, x, y, 15);
			break;
		case 2:
			createBlock(ETexture.CUBE_METAL, x, y, 25);
			break;
		case 3:
			count++;
			if (!isHeart && !player.isHeart() && count >= 10) {
				blocks.add(new BlockHeart(ETexture.HEART, x, y));
				score += 5;
				isHeart = true;
				count = 0;
			} else {
				createBlock(ETexture.CUBE_WOOD, x, y, 15);
			}
			break;
		default:
			break;
		}

	}

	private void createBlock(ETexture texture,int x, int y, int score) {
		blocks.add(new Block(texture, x, y));
		this.score += score;
	}
	
	public void jumpPlayer(){
		if (player.getRectangle().intersects(RECT) || collisionWithBlock(player.getRectangle()) != EMPTY_BLOCK) {
			player.setDirectionVertical(EDirection.UP);
		}
	}

	public void goPlayer(EDirection directionHorizonal,	EDirection directionVertical) {
		checkDead();
		if(player.isDead()){
			PAUSE = true;
		}
		
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
	
	public boolean checkCollision(int i, boolean isVertical) {
		blocks.get(i).clear();
		boolean result = false;
		for (int j = 0; j < blocks.size(); j++) {
			if (i != j && blocks.get(i).getRectangle().intersects(blocks.get(j).getRectangle())) {
				if (isVertical) {
					if (blocks.get(i).getY() >= blocks.get(j).getY()) {
						blocks.get(i).setDown(true);
						blocks.get(j).setUp(true);
						if(blocks.get(j).getTexture() == ETexture.HEART){
							blocks.get(j).isDead = true;
						}
					}
				} else {
					if (blocks.get(j).getX() >= blocks.get(i).getX()) {
						blocks.get(j).setLeft(true);
						blocks.get(i).setRight(true);
					} else {
						blocks.get(i).setLeft(true);
						blocks.get(j).setRight(true);
					}
				}
				if (!result) {
					result = true;
				}
			}
		}
		return result;
	}

	private void cheakLine(){
		bufferBlocks.clear();
		for(Block block: blocks){
			if(block.isDead && block.getTexture() == ETexture.HEART && block.getCountImage() >= 360){
				boofBlock = block;
			}
			if(block.getRectangle().intersects(RECT2)){
				block.isDead = true;
				bufferBlocks.add(block);
			}
		}
		if(boofBlock != null){
			blocks.remove(boofBlock);
			boofBlock = null;
			isHeart = false;
		}
		if(bufferBlocks.size() >= LINE_BLOCKS){
			for(Block element: bufferBlocks){
				blocks.remove(element);
			}
			for(Block block: blocks){
					block.isDown = false;
					block.isUp = false;
			}	
			score += 250;
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
	public Rectangle playerHeight = new Rectangle(0, 0, 1,	1);
	public void checkDead(){
		playerHeight = new Rectangle(player.getRectangle().getX(), player.getRectangle().getY() + 10, 80, 135);
		for(Block block: blocks){
			if(playerHeight.intersects(block.getRectangle())){
				player.setDead(true);
			}
		}
	}
	
	public boolean check(Rectangle rectangle){
		for(Block block: blocks){
			if(block.getRectangle().intersects(rectangle)){
				return true;
			}
		}
		return false;
	}
	
	private void movePlayer(EDirection directionHorizonal) {
		if ((player.getY() - 174) % 76 < 10) {
			if(player.isRun()){
				player.setDirectionHorizontal(directionHorizonal);
			}
			player.moveH();
				int[] moveBlocks = collisionWithBlocks(player.getRectH());
				if(collisionWithBlock(player.getRectangle()) != EMPTY_BLOCK){
					player.setNext(0, 0);
					for (int index = 0; index < blocks.size(); index++) {
						if(moveBlocks[index] != EMPTY_BLOCK){
							if(checkIsMoveBlock(moveBlocks[index])){
								switch (blocks.get(moveBlocks[index]).getTexture()) {
								case CUBE_WOOD:
									if(player.isRun()){
										moveBlock(index, moveBlocks[index], EDirection.RIGHT); 
										moveBlock(index, moveBlocks[index], EDirection.LEFT);
										if(blocks.get(moveBlocks[index]).getY() == player.getY()){
											player.setRun(false);
										}
									}
									break;
								case HEART: 
									blocks.remove(moveBlocks[index]);
									break;
								default:
									break;
								}
								
							} 
//						player.setDead(checkDead(moveBlocks[index]));
						}
					}
				}
			if(collisionWithBlock(player.getRectangle()) == EMPTY_BLOCK){
				player.newPositionX();
			}
		}
		
		player.moveV();
		if(!player.getRectangle().intersects(RECT) && collisionWithBlock(player.getRectangle()) == EMPTY_BLOCK ){
			player.newPositionY();
		}
	}
	
	private boolean checkIsMoveBlock(int index) {
		return (!blocks.get(index).isUp	&& (blocks.get(index).getY() - BOTTOM) % SIZE_BLOCK == 0);
	}
	
	private boolean checkDirection(int index, EDirection direction){
		if(blocks.get(index).getY() == player.getY()){
			if(direction == EDirection.RIGHT && blocks.get(index).getX() > player.getX()){
				return  true;
			}
			if(direction == EDirection.LEFT && blocks.get(index).getX() < player.getX()){
				return true;
			}
		}
		return false;
	}

	private boolean moveBlock(int i, int moveBlock, EDirection direction) {
		Boolean result = false;
		if(checkDirection(moveBlock, direction)){
			blocks.get(moveBlock).isActive = true;
			blocks.get(moveBlock).setDirectionHorizontal(direction);
			blocks.get(moveBlock).moveH();
			if(checkCollision(i, false)){
				blocks.get(moveBlock).setNext(getSpeed(direction), 0);
				blocks.get(moveBlock).newPositionX();
				result = true;
			}
		}
		return result;
	}
	
	private int getSpeed(EDirection direction) {
		if (direction == EDirection.RIGHT) {
			return -4;
		}
		if (direction == EDirection.LEFT) {
			return 4;
		}
		return 0;
	}
	
	private boolean checkDead(int moveBlock) {
		if(moveBlock >= blocks.size())return false;
		Block block = blocks.get(moveBlock);
		if(block.getTexture() == ETexture.HEART) return false;
		return !block.isDown && block.getY() > player.getY()
				&& ((block.getX() >= player.getX() && block.getX() < player
						.getX() + player.getWidth()) || (block.getX()
						+ block.getWidth() > player.getX() && block.getX()
						+ block.getWidth() < player.getX() + player.getWidth()));
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

	public int getScore() {
		return score;
	}
}
