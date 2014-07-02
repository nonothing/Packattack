package com.dose.packattack;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dose.packattack.ConfigAndroid;
import com.dose.packattack.MyGame;
import com.dose.packattack.interfaces.IActivityRequestHandler;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import static com.dose.packattack.MyGame.cfg;
import static com.dose.packattack.MyGame.links;

public class MainActivity extends AndroidApplication implements IActivityRequestHandler{
	
	private AdView adView;
	private final static String ID_ADS = "";
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cfg = new ConfigAndroid();
		cfg.setDebug(false);
		AndroidApplicationConfiguration acfg = new AndroidApplicationConfiguration();
		links = new JLink(this);
		initDisplay();

		RelativeLayout layout = new RelativeLayout(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		View gameView = initializeForView(new MyGame(this), acfg);

		adView = new AdView(this, AdSize.BANNER, ID_ADS);
		AdRequest adRequest = new AdRequest();
		adView.loadAd(adRequest);
		layout.addView(gameView);

		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layout.addView(adView, adParams);

		setContentView(layout);
	}
    
    @SuppressWarnings("deprecation")
	private void initDisplay() {
		Display display = getWindowManager().getDefaultDisplay();
		cfg.setWidth(display.getWidth());
		cfg.setHeight(display.getHeight());
		cfg.setScaleX(cfg.getWidth()/1280.0);
		cfg.setScaleY(cfg.getHeight()/800.0);
		Log.i("DISPLAY", "x="+cfg.getWidth() + " y="+ cfg.getHeight() + " scaleX=" + cfg.getScaleX()+ " scaleY=" + cfg.getScaleY());
	} 
    
    public void showAdMob(boolean show){
        handler.sendEmptyMessage(show ? 1 : 0);
      }

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0)
				adView.setVisibility(View.GONE);
			if (msg.what == 1) {
				adView.setVisibility(View.VISIBLE);
				AdRequest adRequest = new AdRequest();
				adView.loadAd(adRequest);
			}
		}
	};
}