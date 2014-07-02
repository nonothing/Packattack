package com.dose.packattack;

import com.dose.packattack.interfaces.IConfig;

public abstract class ConfigBase implements IConfig{
	private boolean debug = true;
	private int width = 800;
	private int height = 444;
	private double scaleX = 1.0;
	private double scaleY = 1.0;
	private boolean PC;
	
	public boolean isPC() {
		return PC;
	}
	
	public void setPC(boolean PC) {
		this.PC = PC;
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public double getScaleX() {
		return scaleX;
	}
	
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	
	public double getScaleY() {
		return scaleY;
	}
	
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
}
