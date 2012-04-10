/*************************************************************************

  PedometerActivity
  
  	Top-level activity for pedometer without visual tracking
  	
  	MH 3.27.2012
  	RJB 4.10.2012   -changed button listener info (to point to package)
  	
*************************************************************************/

package edu.vt.dr.testing;

import edu.vt.dr.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends Activity {
	
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
				try {
					Intent i = new Intent();
					//Choose Application Package & Class
					//i.setClassName(<APPLICATION PACKAGE NAME>,<CLASS PACKAGE+NAME>)
					i.setClassName("edu.vt.dr",
							"edu.vt.dr.testing.PedometerActivity");
					startActivity(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        	
        
        trackButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				try {
					Intent i = new Intent();
					//Choose Application Package & Class
					//i.setClassName(<APPLICATION PACKAGE NAME>,<CLASS PACKAGE+NAME>)
					i.setClassName("edu.vt.dr",
							"edu.vt.dr.testing.GLAndSensorsActivity");
					startActivity(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
