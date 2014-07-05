package com.dose.packattack.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.dose.packattack.view.Images;
import static com.dose.packattack.MyGame.cfg;

public class Score {
	private final int SIZE = 4;
	private Timer timer;
	private JNumber numbers[];
	private int x;
	private int y;
	private int curentScore;
	private int score;
	private short numb[];
	private boolean isCounting;
	private final int MAX_SCORE = 9999;
	private int width;
	
	public Score(Images imageWorld, int x, int y, int width, int height) {
		numbers = new JNumber[SIZE];
		numb = new short[SIZE];
		for (int i = 0; i < SIZE; i++) {
			numbers[i] = new JNumber(imageWorld, width, height);
		}
		this.x = x;
		this.y = y;
		this.width = width;
		timer = new Timer();
		timer.scheduleTask(task, 0, 0.001f);
		score = 0;
	}
	
	Task task = new Task() {
		
		@Override
		public void run() {
			if(isCounting){
				if(curentScore <= score){
					setCount(curentScore++);
				}
			}
		}
	};
	
	public boolean accelerateScore(){
		if(curentScore >= score){
			return true;
		} else{
			curentScore = score;
			return false;
		}
	}
	
	public void clear() {
		curentScore = 0;
		for (int i = 0; i < SIZE; i++) {
			numb[i] = 0;
		}
	}

	public void setScore(int time, int countStep){
		clear();
		score = MAX_SCORE - time * countStep;
		if(score < 0) score = 0;
	}
	
	public void setCount(int count) {
			String number = String.valueOf(count);
			for (int i = 0; i < number.length(); i++) {
				numb[SIZE - i - 1] = (short) (number.charAt(number.length() - i - 1) - '0');
			}
	}
	
	public void draw(SpriteBatch batch){
		for (int i = 0; i < SIZE; i++) {
			numbers[i].draw(batch, numb[i], (int)((x + i*width )*cfg.getScaleX()), (int)(y*cfg.getScaleY()));
		}
	}
	
	public void isCounting(boolean is){
		isCounting = is;
	}
}
