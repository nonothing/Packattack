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
		textures.put(ETexture.PLAYER_0, loadTexture("player_0"));
		textures.put(ETexture.PLAYER_1, loadTexture("player_1"));
		textures.put(ETexture.PLAYER_2, loadTexture("player_2"));
		textures.put(ETexture.PLAYER_3, loadTexture("player_3"));
		textures.put(ETexture.PLAYER_4, loadTexture("player_4"));
		textures.put(ETexture.PLAYER_5, loadTexture("player_5"));
		textures.put(ETexture.PLAYER_6, loadTexture("player_6"));
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

		textures.put(ETexture.CUBE_WOOD, loadTexture("box"));
		
	}
	
	private Texture loadTexture(String name){
		return new Texture(Gdx.files.internal("images/" + name + ".png"));
	}
	
	public Texture getTexture(ETexture name) {
		return textures.get(name);
	}
}
