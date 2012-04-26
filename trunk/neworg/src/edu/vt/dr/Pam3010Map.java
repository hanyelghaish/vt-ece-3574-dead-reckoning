package edu.vt.dr;

import edu.vt.dr.map.GLRenderer;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import edu.vt.dr.utilities.LocationUtil;
import edu.vt.dr.utilities.SensorUtil;

public class Pam3010Map extends Activity implements SensorEventListener{

	private GLSurfaceView mGLView;
 
	/**************************************************
	 * On Create
	**************************************************/
	
	private boolean sysOk;
	private SensorUtil SU;
	private boolean presslock = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SU = new SensorUtil(this);
		LocationUtil.init();
		if (SU.systemMeetsRequirements()) {
    	
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			requestWindowFeature(Window.FEATURE_NO_TITLE);	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);		
			sysOk = true;
    
		} else {
			//system does not need requirement
			sysOk = false;
		}

		mGLView = new PAM3010SurfaceView(this);
		setContentView(mGLView);		
	}
	
	/**************************************************
	 * On Pause
	**************************************************/
	@Override
	protected void onPause(){
		super.onPause();
		if (sysOk) {
    		SU.unregisterListeners();
    		mGLView.onPause();
    	}
	}
	
	/**************************************************
	 * On Resume
	**************************************************/
	@Override
	protected void onResume(){
		super.onResume();
		if (sysOk) {
    		SU.registerListeners();
    		mGLView.onResume();
    	}
	}

	@Override 
    public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getEventTime() - event.getDownTime() > 500)
		{
			if (!presslock) {
				LocationUtil.setAzimuthOffset(0);
				
				float a = LocationUtil.getCurrentAzimuth();
				LocationUtil.setAzimuthOffset((float)(Math.PI/2 - a));
				presslock = true;
			}
		}
		else
		{
			LocationUtil.initPosition(1.3f, -0.8f);
			presslock = false;
		}
		
        return true; 
    } 

    public void onSensorChanged(SensorEvent event) {
    	SU.routeEvent(event);
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
	
}

class PAM3010SurfaceView extends GLSurfaceView{

	public PAM3010SurfaceView(Context context) {
		super(context);
		setRenderer(new GLRenderer(context));
	}
}


