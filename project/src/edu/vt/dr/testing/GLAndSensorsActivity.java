/*************************************************************************

  GlAndSensorsActivity
  
  	Top-level activity for OpenGL location tracker.
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.testing;

import edu.vt.dr.R;
import edu.vt.dr.testing.MySurfaceRenderer;
import edu.vt.dr.testing.utilities.LocationUtil;
import edu.vt.dr.testing.utilities.SensorUtil;
import android.app.Activity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.Window;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class GLAndSensorsActivity extends Activity implements SensorEventListener {
	
	private MySurfaceView GLView;
	private boolean sysOk;
	private SensorUtil SU;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SU = new SensorUtil(this);
        LocationUtil.init();
        if (SU.systemMeetsRequirements()) {
        	
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        	
        	GLView = new MySurfaceView(this);
        	setContentView(GLView);
        	sysOk = true;
        
        } else {
        	
        	setContentView(R.layout.main);
        	sysOk = false;
        }
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	if (sysOk) {
    		SU.registerListeners();
    		GLView.onResume();
    	}
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	if (sysOk) {
    		SU.unregisterListeners();
    		GLView.onPause();
    	}
    }
    
    @Override 
    public boolean onTouchEvent(MotionEvent event) {
    	LocationUtil.reset();
        return true; 
    } 

    public void onSensorChanged(SensorEvent event) {
    	SU.routeEvent(event);
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
}

class MySurfaceView extends GLSurfaceView {
	public MySurfaceView(Context context) {
		super(context);
		
		setRenderer(new MySurfaceRenderer());
	}
}