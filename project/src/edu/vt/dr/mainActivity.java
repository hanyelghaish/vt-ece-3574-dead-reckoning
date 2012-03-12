package edu.vt.dr;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Nathan Gentzen, Mike Sutton
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
 * 
 ******************************************************************************/

public class mainActivity extends Activity {
	
    private static final String RAWDATAACTIVITY = "edu.vt.dr.RAWDATAACTIVITY";
    private static final String MOVEMENTACTIVITY = "edu.vt.dr.MOVEMENTACTIVITY";
	private static final String MENUACTIVITY = "edu.vt.dr.MENUACTIVITY";

	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	//calls super, sets GUI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //associates buttons with IDs
        Button map = (Button) findViewById(R.id.mapButton);
        Button data = (Button) findViewById(R.id.dataButton);
        Button movement = (Button) findViewById(R.id.movementButton);	
        

        
        //associates listener for button Map Selection
        map.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent gotoMenu = new Intent(MENUACTIVITY);
              startActivity(gotoMenu);
			}
        });
        
        //associates listener for button Raw Data
        data.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent gotoData = new Intent(RAWDATAACTIVITY);
				startActivity(gotoData);
			}
		});
        
        //associates listener for button Movement
        movement.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent gotoData = new Intent(MOVEMENTACTIVITY);
				startActivity(gotoData);
			}
		});
        
    }
}