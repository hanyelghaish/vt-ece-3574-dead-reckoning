package edu.vt.dr;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.Object;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Hutton
 * 
 * This class is for displaying the About Us page. 
 * 
 * It is called upon when the About Us button is pressed on the intial menu.
 *
 * From here, the user can click on 1 of 3 buttons
 * 1 - Raw Data - user can see the data come in from accelerometer
 * 2 - Trace - User can see the data translate into movement on the screen
 * 3 - Movement - User can see the data representation of movement and angle from initial location
 * 
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * MH                       BC #2              Inital creation
 * RJB                      4/10/12         Cleaned code; documentation added 
 * 
 ******************************************************************************/

public class AboutUs extends Activity{
    private static final String RAWDATAACTIVITY = "edu.vt.dr.RAWDATAACTIVITY";
    private static final String MOVEMENT = "edu.vt.dr.MOVEMENTACTIVITY";
    
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aboutus);
		this.tv = (TextView) findViewById(R.id.boutUsText);
		tv.setText("");
		tv.setTextColor(0xFF000000);
		
		tv.append("Welcome to our Dead Reckoning App");
	    tv.append("                                 ");
	    tv.append("                                 ");
	    tv.append("What is Dead Reckoning?          ");
	    tv.append("Dead Reckoning is the process of ");
	    tv.append("finding ones position based on   ");
	    tv.append("movement from an initial position.");
	    tv.append("Using your phone's accelerometer ");
	    tv.append("and compass, we can calculate    ");
	    tv.append("where your new position is.      ");
	    tv.append("                                 ");
	    tv.append("Click the buttons below to view  ");
	    tv.append("raw data and filtered data.      ");
	    tv.append("                                 ");
	    tv.append("This app was created by:         ");
	    tv.append("   Mike Hutton       Kim Do      ");
	    tv.append("      and          Roger Baker   ");
		
	    
	    ImageButton raw = (ImageButton) findViewById(R.id.rawData);
	    ImageButton movement = (ImageButton) findViewById(R.id.movementData);
	    ImageButton trackButton = (ImageButton) findViewById(R.id.trackingButton);
	    
	    raw.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(RAWDATAACTIVITY);
				startActivity(i);
			}
		});
	    
	    movement.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(MOVEMENT);
				startActivity(i);
			}
		});
	    
	    trackButton.setOnClickListener(new View.OnClickListener() {
			
				public void onClick(View v) {
					try {
						Intent i = new Intent();
						//This button listener is a bit different as this Activity is within a different package
						//from program package
						i.setClassName("edu.vt.dr",
								"edu.vt.dr.testing.SelectionActivity");
						startActivity(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	    
	    
	    




	}
	
	

}
