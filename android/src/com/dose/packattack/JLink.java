package com.dose.packattack;

import com.dose.packattack.interfaces.ILink;

public class JLink implements ILink{
	
	private MainActivity mActivity;
	
	public JLink(MainActivity activity) {
		mActivity = activity;
	}
	
	@Override
	public void vkPostToWall(String picture) {
		// TODO Auto-generated method stub
	}

	@Override
	public void fbPostToWall(String picture) {
		// TODO Auto-generated method stub
	}

	@Override
	public void showMyGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void showMoreGames() {
		// TODO Auto-generated method stub
	}

}
