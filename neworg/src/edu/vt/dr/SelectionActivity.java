/*************************************************************************

  PedometerActivity
  
  	Top-level activity for pedometer without visual tracking
  	
  	MH 3.27.2012
  	RJB 4.10.2012   -changed button listener info (to point to package)
  	
*************************************************************************/

package edu.vt.dr;

import edu.vt.dr.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends Activity {
	
	private static final String GL_AND_SENSORS_ACTIVITY = "edu.vt.dr.GLAndSensorsActivity";
	private static final String PEDOMETER_ACTIVITY = "edu.vt.dr.PedometerActivity";
	private Button pedButton;
	private Button trackButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.selectmode);
	    
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    
	    pedButton = (Button) findViewById(R.id.button1);
	    trackButton = (Button) findViewById(R.id.button2);
	    
        pedButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(PEDOMETER_ACTIVITY);
				startActivity(i);
			}
		});
        
        trackButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(GL_AND_SENSORS_ACTIVITY);
				startActivity(i);
			}
		});
        	
	}
	
    @Override
    public void onResume() {
    	super.onResume();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    }
}
