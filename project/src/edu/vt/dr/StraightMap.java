package edu.vt.dr;

import edu.vt.dr.map.GLRenderer;
import edu.vt.dr.map.straightGLRenderer;
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



/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Hutton
 * 
 * This class is for displaying the Straight Map. 
 * 
 * It is called upon when the Straight button is pressed on the map selection screen.
 *
 * From here, the user must use a "Back" button on the phone to return to the maps menu
 *  (i.e. there is no back button in the GUI)
 *
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * RJB                      2/21/12          Cleaned code; documentation added 
 * KD                       BC #3
 * 
 ******************************************************************************/

public class StraightMap extends Activity implements SensorEventListener {

	private GLSurfaceView straightmGLView;
 
	

	
	
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
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//MIKE'S
		 SU = new SensorUtil(this);
	        LocationUtil.init();
	        if (SU.systemMeetsRequirements()) {
	        	
	        	requestWindowFeature(Window.FEATURE_NO_TITLE);
	        	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        	
	        
	        	sysOk = true;
	        
	        } else {
	        	//system does not need requirement
	        	sysOk = false;
	        }
	      //--------------------
		
		
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		straightmGLView = new straightSurfaceView(this);
		setContentView(straightmGLView); 
		
		 
	}

		
	
	/**************************************************
	 * On Pause
	**************************************************/
	@Override
	protected void onPause(){
		super.onPause();
		if (sysOk) {
    		SU.unregisterListeners();
    		straightmGLView.onPause();
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
    		straightmGLView.onResume();
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
    	
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
	
}

class straightSurfaceView extends GLSurfaceView{

	public straightSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//Set the Renderer for drawing on the GLSurfaceView
		setRenderer(new straightGLRenderer(context));
	}
}





