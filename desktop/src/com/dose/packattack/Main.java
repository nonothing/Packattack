package com.dose.packattack;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dose.packattack.MyGame;
import com.dose.packattack.model.JLinkPC;

import static com.dose.packattack.MyGame.*;

public class Main {
	public static void main(String[] args) {
		cfg = new Config();
		links = new JLinkPC();
		LwjglApplicationConfiguration acfg = new LwjglApplicationConfiguration();
		acfg.title = "Packattack";
		acfg.width = cfg.getWidth();
		acfg.height = cfg.getHeight();
		cfg.setPC(true);
		cfg.setDebug(true);
		initSize();
		new LwjglApplication(new MyGame(), acfg);
	}
	
	private static void initSize(){
		cfg.setScaleX(cfg.getWidth() / 1280.0);
		cfg.setScaleY(cfg.getHeight() / 800.0);
	}
}
