package edu.vt.dr;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Pam3010Map extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pam3010map);
		
	}
	
	

}
