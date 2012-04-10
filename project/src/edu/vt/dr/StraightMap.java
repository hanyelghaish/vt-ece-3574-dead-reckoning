package edu.vt.dr;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import edu.vt.dr.map.*;


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

public class StraightMap extends Activity {


	private GLSurfaceView mGLView;
 
	

	
	
	/**************************************************
	 * On Create
	**************************************************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		mGLView = new straightActivitySurfaceView(this);
		setContentView(mGLView);     
	    TextView tv = new TextView(this);
		tv.setText("(0.00, 0.00)");
		tv.setTextSize(25);
		this.addContentView(tv,  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		
	}
	
	/**************************************************
	 * On Pause
	**************************************************/
	@Override
	protected void onPause(){
		super.onPause();
		mGLView.onPause();
	}
	
	/**************************************************
	 * On Resume
	**************************************************/
	@Override
	protected void onResume(){
		super.onResume();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}

class straightActivitySurfaceView extends GLSurfaceView{

	public straightActivitySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//Set the Renderer for drawing on the GLSurfaceView
		setRenderer(new GLRenderer(context));
	}
}
