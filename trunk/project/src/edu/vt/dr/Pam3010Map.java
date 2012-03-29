package edu.vt.dr;

import edu.vt.dr.map.GLRenderer;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class Pam3010Map extends Activity{

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

class PAM3010SurfaceView extends GLSurfaceView{

	public PAM3010SurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//Set the Renderer for drawing on the GLSurfaceView
		setRenderer(new GLRenderer(context));
	}
}
