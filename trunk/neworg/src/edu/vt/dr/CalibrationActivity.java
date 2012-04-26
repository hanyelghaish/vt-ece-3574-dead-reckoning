package edu.vt.dr;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class CalibrationActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
		setContentView(R.layout.calnote);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
	}

}
