package edu.vt.dr;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Hutton
 * 
 * This class is the main driver of the Project. 
 * 
 * It is called upon when the program first starts.
 *
 * From here, the user can select one of two buttons
 *    1.) Map selection - the user can then select a map to display
 *    2.) Raw Data - the user can view the data from the accelerometer
 *    3.) Movement - the user can watch the their movement correspond to change in position
 *    
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * RJB                      2/21/12          Cleaned code; documentation added 
 * RJB                      3/10/12           Added Movement button/functionality
 * RJB                      4/10/12           
 * 
 ******************************************************************************/

public class mainActivity extends Activity {
	
    private static final String LISTVMENU = "edu.vt.dr.LISTVMENU";
	private static final String ABOUTUS = "edu.vt.dr.ABOUTUS";

	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	//calls super, sets GUI
        super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mainmenu);
        
        //associates buttons with IDs
        
        ImageButton mapMenu = (ImageButton) findViewById(R.id.mapButton);
        ImageButton aboutUs = (ImageButton) findViewById(R.id.aboutButton);
                
        //associates listener for Map Selection button
        mapMenu.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent i = new Intent(LISTVMENU);
              startActivity(i);
			}
        });
        
        //associates listener for About Us Button
        aboutUs.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(ABOUTUS);
				startActivity(i);
				
			}
		});
        
        
    }
}