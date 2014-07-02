package com.dose.packattack.interfaces;

public interface IConfig {
	
	public boolean isPC();
	public void setPC(boolean PC);
	public boolean isDebug();
	public void setDebug(boolean debug);
	public int getWidth();
	public void setWidth(int width);
	public int getHeight();
	public void setHeight(int height);
	public double getScaleX();
	public void setScaleX(double scaleX);
	public double getScaleY();
	public void setScaleY(double scaleY);
	
	public void readConfig();
	public void writeConfig();
}
