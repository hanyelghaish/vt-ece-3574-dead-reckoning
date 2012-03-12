package edu.vt.dr;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

	private static final String mainActivityLaunch = "edu.vt.dr.MAINACTIVITY";

	@Override
	protected void onCreate(Bundle b) 
	{
		super.onCreate(b);        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		MediaPlayer splashChime = MediaPlayer.create(Splash.this, R.raw.splashsound);
		splashChime.start();
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(9200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					Intent openMainActivity = new Intent(mainActivityLaunch);
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
