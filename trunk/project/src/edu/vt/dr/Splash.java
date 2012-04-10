package edu.vt.dr;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Sutton
 * 
 * This class is for the splash screen in the initial launch of the app
 * 
 * It will play a little jingle, show a quick animation and then goto the main screen
 *
 *
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * RJB                      3/12/12           Initial creation, put in sound and temp background 
 * RJB                      4/10/12           New background added
 ******************************************************************************/


public class Splash extends Activity {

	private static final String mainActivityLaunch = "edu.vt.dr.MAINACTIVITY";
	MediaPlayer splashChime;
	
	@Override
	protected void onCreate(Bundle b) 
	{
		super.onCreate(b);        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		splashChime = MediaPlayer.create(Splash.this, R.raw.splashsound);
		splashChime.start();
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(5200);
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
		splashChime.stop();
		finish();
	}
	
	
	

}
