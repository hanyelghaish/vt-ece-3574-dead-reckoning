package edu.vt.dr;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;


public class AboutUs extends Activity{
    TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aboutus);
		this.tv = (TextView) findViewById(R.id.boutUsText);
		tv.setText("");
		tv.append("   Welcome to our Dead Reckoning App!!");
		tv.append(" how are you \n");




	}
	
	

}
