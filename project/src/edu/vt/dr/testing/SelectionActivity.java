/*************************************************************************

  PedometerActivity
  
  	Top-level activity for pedometer without visual tracking
  	
  	MH 3.27.2012
  	
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
		      Intent i = new Intent("edu.vt.dr.testing.PedometerActivity");
              startActivity(i);
			}
        });
        
        trackButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
		      Intent i = new Intent("edu.vt.dr.testing.GLAndSensorsActivity");
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
