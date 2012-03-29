/*************************************************************************

  PedometerActivity
  
  	Top-level activity for pedometer without visual tracking
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.testing;

import edu.vt.dr.R;
import edu.vt.dr.testing.utilities.LocationUtil;
import edu.vt.dr.testing.utilities.SensorUtil;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class PedometerActivity extends Activity implements SensorEventListener {
	
	private TextView distanceText;
	private SensorUtil SU;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
    	SU = new SensorUtil(this);
	    LocationUtil.init(); 
	    setContentView(R.layout.pedometer);
	    
	    distanceText = (TextView) findViewById(R.id.textView2);
	    distanceText.setText("Traveled 0 feet");
	}
	
    @Override
    public void onResume() {
    	super.onResume();
    	SU.registerListeners();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	SU.unregisterListeners();
    }
    
    @Override 
    public boolean onTouchEvent(MotionEvent event) {
    	LocationUtil.reset();
        return true; 
    } 
    
    public void onSensorChanged(SensorEvent event) {
    	SU.routeEvent(event);
    	float d = LocationUtil.getTotalDistanceFeet();
    	distanceText.setText(
    			"Traveled " + String.format("%.2f", d) + " feet");
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
}
