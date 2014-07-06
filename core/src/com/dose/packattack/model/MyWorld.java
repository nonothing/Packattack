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
	private Player player;
	private ArrayList<Block> blocks;
	private ArrayList<Pelican> pelicans;
	private final Rectangle RECT;
	private final Rectangle RECT2;
	private final Random random;
	private int score;

	public MyWorld() {
		blocks = new ArrayList<Block>();
		pelicans = new ArrayList<Pelican>();
		player = new Player(ETexture.PLAYER_0, 80, 178, 80, 80);
		random = new Random();
		for (int i = 0; i < 5; i++)
			createPelican();
		
		RECT= new Rectangle(0,140, FULL_WIDTH, 28);
		RECT2= new Rectangle(0,140, FULL_WIDTH, 40);
	}

	public void newGame(){
		score = 0;
		blocks.clear();
		pelicans.clear();
		player = new Player(ETexture.PLAYER_0, 80, 178, 80, 80);
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
		if (new Random().nextInt(2) == 0) {
			blocks.add(new Block(ETexture.CUBE_WOOD, x, y));
			score += 15;
		} else {
			blocks.add(new Block(ETexture.CUBE_WOOD, x, y));
			score += 25;
		}

	}
	
	public void jumpPlayer(){
		if (player.getRectangle().intersects(RECT) || collisionWithBlock(player.getRectangle()) != EMPTY_BLOCK) {
			player.setDirectionVertical(EDirection.UP);
		}
	}

	public void goPlayer(EDirection directionHorizonal,	EDirection directionVertical) {
		
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
			if(block.getRectangle().intersects(RECT2)){
				block.isDead = true;
				bufferBlocks.add(block);
			}
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
			player.setDirectionHorizontal(directionHorizonal);
			player.moveH();
		
			int[] moveBlocks = collisionWithBlocks(player.getRectH());
			if(collisionWithBlock(player.getRectangle()) != EMPTY_BLOCK){
				player.setNext(0, 0);
				for (int index = 0; index < blocks.size(); index++) {
					if(moveBlocks[index] != EMPTY_BLOCK){
						if(checkIsMoveBlock(moveBlocks[index])){
							moveBlock(index, moveBlocks[index], EDirection.RIGHT); 
							moveBlock(index, moveBlocks[index], EDirection.LEFT); 
						} 
						player.setDead(checkDead(moveBlocks[index]));
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

	private void moveBlock(int i, int moveBlock, EDirection direction) {
		if(checkDirection(moveBlock, direction)){
			blocks.get(moveBlock).isActive = true;
			blocks.get(moveBlock).setDirectionHorizontal(direction);
			blocks.get(moveBlock).moveH();
			if(checkCollision(i, false)){
				blocks.get(moveBlock).setNext(getSpeed(direction), 0);
				blocks.get(moveBlock).newPositionX();
			}
		}
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
		Block block = blocks.get(moveBlock);
		return !block.isDown && block.getY() > player.getY()
				&& ((block.getX() > player.getX() && block.getX() < player
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
