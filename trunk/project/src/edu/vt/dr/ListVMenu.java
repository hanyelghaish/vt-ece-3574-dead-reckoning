package edu.vt.dr;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;


public class ListVMenu extends Activity {
	
	private static final String PAM3010 = "edu.vt.dr.PAM3010MAP";
	private static final String STRAIGHT = "edu.vt.dr.STRAIGHTMAP";
	private static final String CIRCLE = "edu.vt.dr.CIRCLEMAP";
	private static final String LSHAPE = "edu.vt.dr.LSHAPEMAP";

	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	//calls super, sets GUI
        super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.maplist);
        
        //associates buttons with IDs
        
        ImageButton pam3010Button = (ImageButton) findViewById(R.id.buttonPam3010);
        ImageButton straightButton = (ImageButton) findViewById(R.id.buttonStraight);
        ImageButton circleButton = (ImageButton) findViewById(R.id.buttonCircle);
        ImageButton lshapeButton = (ImageButton) findViewById(R.id.buttonLShape);
                
        //associates listener for Map Selection button
        pam3010Button.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent i = new Intent(PAM3010);
              startActivity(i);
			}
        });
        
        //associates listener for About Us Button
        straightButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(STRAIGHT);
				startActivity(i);
				
			}
		});
        
        
        circleButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(CIRCLE);
				startActivity(i);
			}
		});
        
        lshapeButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(LSHAPE);
				startActivity(i);
			}
		});
       
    }
}
