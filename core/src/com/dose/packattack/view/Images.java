package com.dose.packattack.view;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dose.packattack.enumerate.ETexture;


public class Images {
	private HashMap<ETexture, Texture> textures;
	
	public Images() {
		textures = new HashMap<ETexture, Texture>();
		textures.put(ETexture.BACKGROUND, loadTexture("background"));
		textures.put(ETexture.PLAYER_STAY, loadPlayerTexture("player_stay"));
		textures.put(ETexture.PLAYER_1, loadPlayerTexture("run/player_1"));
		textures.put(ETexture.PLAYER_2, loadPlayerTexture("run/player_2"));
		textures.put(ETexture.PLAYER_3, loadPlayerTexture("run/player_3"));
		textures.put(ETexture.PLAYER_4, loadPlayerTexture("run/player_4"));
		textures.put(ETexture.PLAYER_5, loadPlayerTexture("run/player_5"));
		textures.put(ETexture.PLAYER_6, loadPlayerTexture("run/player_6"));
		textures.put(ETexture.PLAYER_7, loadPlayerTexture("run/player_7"));
		textures.put(ETexture.PLAYER_8, loadPlayerTexture("run/player_8"));
		
		textures.put(ETexture.PLAYER_MOVE_1, loadPlayerTexture("move/player_1"));
		textures.put(ETexture.PLAYER_MOVE_2, loadPlayerTexture("move/player_2"));
		textures.put(ETexture.PLAYER_MOVE_3, loadPlayerTexture("move/player_3"));
		textures.put(ETexture.PLAYER_MOVE_4, loadPlayerTexture("move/player_4"));
		textures.put(ETexture.PLAYER_MOVE_5, loadPlayerTexture("move/player_5"));
		textures.put(ETexture.PLAYER_MOVE_6, loadPlayerTexture("move/player_6"));
		textures.put(ETexture.PLAYER_MOVE_7, loadPlayerTexture("move/player_7"));
		textures.put(ETexture.PLAYER_MOVE_8, loadPlayerTexture("move/player_8"));
		
		textures.put(ETexture.DIGITAL_0, loadTexture("number0"));
		textures.put(ETexture.DIGITAL_1, loadTexture("number1"));
		textures.put(ETexture.DIGITAL_2, loadTexture("number2"));
		textures.put(ETexture.DIGITAL_3, loadTexture("number3"));
		textures.put(ETexture.DIGITAL_4, loadTexture("number4"));
		textures.put(ETexture.DIGITAL_5, loadTexture("number5"));
		textures.put(ETexture.DIGITAL_6, loadTexture("number6"));
		textures.put(ETexture.DIGITAL_7, loadTexture("number7"));
		textures.put(ETexture.DIGITAL_8, loadTexture("number8"));
		textures.put(ETexture.DIGITAL_9, loadTexture("number9"));
		
//		textures.put(ETexture.PLAYER_0, loadTexture("test"));
//		textures.put(ETexture.PLAYER_1, loadTexture("test"));
//		textures.put(ETexture.PLAYER_2, loadTexture("test"));
//		textures.put(ETexture.PLAYER_3, loadTexture("test"));
//		textures.put(ETexture.PLAYER_4, loadTexture("test"));
//		textures.put(ETexture.PLAYER_5, loadTexture("test"));
//		textures.put(ETexture.PLAYER_6, loadTexture("test"));

		textures.put(ETexture.PELICAN_1, loadTexture("dragon1"));
		textures.put(ETexture.PELICAN_2, loadTexture("dragon2"));
		textures.put(ETexture.PELICAN_3, loadTexture("dragon3"));
		textures.put(ETexture.PELICAN_4, loadTexture("dragon4"));
		
		textures.put(ETexture.THREE, loadTexture("three"));
		textures.put(ETexture.LAYER_1, loadTexture("layer1"));
		textures.put(ETexture.LAYER_2, loadTexture("layer2"));
		textures.put(ETexture.CUBE_WOOD, loadTexture("box"));
		textures.put(ETexture.CUBE_METAL, loadTexture("box2"));
		textures.put(ETexture.HEART, loadTexture("heart"));
		textures.put(ETexture.POPAP_BACKGROUND, loadTexture("popap_background"));
		
	}
	
	private Texture loadPlayerTexture(String name){
		return loadTexture("player/" + name);
	}
	
	private Texture loadTexture(String name){
		return new Texture(Gdx.files.internal("images/" + name + ".png"));
	}
	
	public Texture getTexture(ETexture name) {
		return textures.get(name);
	}
}
