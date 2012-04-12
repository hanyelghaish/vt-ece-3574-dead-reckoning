package edu.vt.dr;

import edu.vt.dr.map.GLRenderer;
import edu.vt.dr.testing.utilities.FloatPoint;
import edu.vt.dr.testing.utilities.LocationUtil;
import edu.vt.dr.testing.utilities.SensorUtil;
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
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import edu.vt.dr.testing.*;

public class Pam3010Map extends Activity implements SensorEventListener{

	private GLSurfaceView mGLView;
 
	

	
	
	/**************************************************
	 * On Create
	**************************************************/
	
	private boolean sysOk;
	private SensorUtil SU;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		//MIKE'S
		 SU = new SensorUtil(this);
	        LocationUtil.init();
	        if (SU.systemMeetsRequirements()) {
	        	
	        	requestWindowFeature(Window.FEATURE_NO_TITLE);
	        	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        	
	        
	        	sysOk = true;
	        
	        } else {
	        	//system does not need requirement
	        	sysOk = false;
	        }
	      //--------------------
		
		
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		mGLView = new PAM3010SurfaceView(this);
		setContentView(mGLView); 
		/*
	    tv = new TextView(this);
		tv.setText("(0.00, 0.00)");
		tv.setTextSize(25);
		
		this.addContentView(tv,  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		*/
		
		
		
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
    	LocationUtil.reset();
        return true; 
    } 

    public void onSensorChanged(SensorEvent event) {
    	
    	FloatPoint f = LocationUtil.getCurrentLocation();
    	
    	SU.routeEvent(event);
    	//tv.setText(Float.toString(f.getX()) + "," + Float.toString(f.getY()));
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
	
}

class PAM3010SurfaceView extends GLSurfaceView{

	public PAM3010SurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//Set the Renderer for drawing on the GLSurfaceView
		setRenderer(new GLRenderer(context));
	}
}


