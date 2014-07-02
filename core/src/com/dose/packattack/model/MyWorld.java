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
	    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

	    public MyWorld() {
	        blocks = new ArrayList<Block>();
	        pelicans = new ArrayList<Pelican>();
	        player = new Player(ETexture.PLAYER_0, 100, 720, 80, 100);
	        pelicans.add(new Pelican(ETexture.PELICAN_1, 0, 690, 110, 100));

	        createRectangles();

	    }

	    public void createBlock(int x, int y) {
	        if (new Random().nextInt(2) == 0) {
	            blocks.add(new Block(ETexture.CUBE_WOOD, x, y, 80, 80));
	        } else {
	            blocks.add(new Block(ETexture.CUBE_WOOD, x, y, 80, 80));
	        }

	    }

	    public void goPlayer(EDirection directionHorizonal,
	            EDirection directionVertical) {
	        player.move(directionHorizonal, false);
	        for (Block block : blocks) {
	            collisionBlockToBlock();
	            moveToBlock(player.getRectangle(), directionHorizonal);
	            if (block.isMoveHorizontal) {
	                block.setX(block.getRectangle().getX());
	            }
	            block.move(EDirection.DOWN, true);
	            collisionBlockToBlock();
	            if (block.isMoveVertical) {
	                block.setY(block.getRectangle().getY());
	            }
	        }
	        if (!collisionWithLevel(player.getRectangle())) {
	            player.setX(player.getRectangle().getX());
	        }
	        player.move(directionVertical, true);
	        if (!collisionWithLevel(player.getRectangle())) {
	            player.setY(player.getRectangle().getY());
	        }

	        checkLineBlock();
	        // if(player.isDead()) newGame();
	        for (Pelican pelican : pelicans) {
	            pelican.move();
	            pelican.createBlock(this);
	        }
	    }

	    public Player getPlayer() {
	        return player;
	    }

	    private void collisionBlockToBlock() {

	        for (int i = 0; i < blocks.size(); i++) {

	            blocks.get(i).isMoveVertical = true;
	            for (int j = 0; j < blocks.size(); j++) {
	                if (i != j) {

	                    if (blocks.get(i).getRectangle()
	                            .intersects(blocks.get(j).getRectangle())) {
	                        if (blocks.get(i).getY() == blocks.get(j).getY()) {
	                            blocks.get(i).isMoveHorizontal = false;
	                        }

	                        if (blocks.get(i).getY() + blocks.get(i).getHeight() > blocks
	                                .get(j).getY()) {
	                            blocks.get(i).isMoveVertical = false;
	                            if (countCollisionBlock(j) == 1)
	                                blocks.get(j).isMoveVertical = true;
	                        }
	                    }

	                }
	            }
	        }
	    }

	    public void checkLineBlock() {
	        int index = 0;
	        for (Rectangle rectangle : rectangles) {
	            for (Block block : blocks) {
	                if (rectangle.intersects(block.getRectangle())) {
	                    index++;
	                    block.isDead = true;
	                }
	            }
	        }

	        if (index >= 20) {
	            for (int i = blocks.size() - 1; i >= 0; i--) {
	                if (blocks.get(i).isDead) {
	                    blocks.remove(i);
	                }
	            }
	        }

	    }

	    private void createRectangles() {
	        for (int i = 0; i < 800; i += 40) {
	            rectangles.add(new Rectangle(i, 360, 25, 20));
	        }
	    }

	    private int countCollisionBlock(int j) {
	        int result = 0;
	        for (int i = 0; i < blocks.size(); i++) {
	            if (i != j)
	                if (blocks.get(i).getRectangle()
	                        .intersects(blocks.get(j).getRectangle())) {
	                    result++;
	                }
	        }
	        return result;
	    }

	    private void moveToBlock(Rectangle rectangle, EDirection direction) {
	        for (Block block : blocks) {
	            if (block.getRectangle().intersects(rectangle)
	                    && block.getTexture() == ETexture.CUBE_WOOD) {
	                if (direction == EDirection.RIGHT
	                        && (rectangle.getX() + rectangle.getWigth() + 4) > block
	                                .getRectangle().getX()) {
	                    block.move(EDirection.RIGHT, false);
	                }
	                if (direction == EDirection.LEFT
	                        && rectangle.getX() < (block.getRectangle().getX() + block
	                                .getRectangle().getWigth())) {
	                    block.move(EDirection.LEFT, false);
	                }
	            }
	        }
	    }

	    public void newGame() {
	        blocks.clear();
	        player.setDead(false);

	    }

	    public boolean collisionWithLevel(Rectangle rectangle) {
	        player.setGround(false);
	        for (Block block : blocks) {
	            if (block.getRectangle().intersects(rectangle)) {
	                if (block.getY() < rectangle.getY()
	                        && (rectangle.getX() < block.getX() + block.getWidth() || rectangle
	                                .getX() + rectangle.getWigth() < block.getX()))
	                    player.setDead(true);
	                if (block.getY() > rectangle.getY()
	                        && (rectangle.getX() < block.getX() + block.getWidth() || rectangle
	                                .getX() + rectangle.getWigth()< block.getX()))
	                    player.setGround(true);
	                return true;
	            }
	        }
	        return false;
	    }

	    public ArrayList<Block> getBlocks() {
	        return blocks;
	    }

	    public ArrayList<Pelican> getPelicans() {
	        return pelicans;
	    }
}
