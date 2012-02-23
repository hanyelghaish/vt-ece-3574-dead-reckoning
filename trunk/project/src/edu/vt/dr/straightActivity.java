package edu.vt.dr;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import edu.vt.dr.map.*;


/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Nathan Gentzen, Mike Sutton
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
 * 
 ******************************************************************************/

public class straightActivity extends Activity {


	private GLSurfaceView mGLView;
	
	/**************************************************
	 * On Create
	**************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mGLView = new straightActivitySurfaceView(this);
		
		setContentView(mGLView);
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

}

class straightActivitySurfaceView extends GLSurfaceView{

	public straightActivitySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//Set the Renderer for drawing on the GLSurfaceView
		setRenderer(new GLRenderer(/*context,2.0f,1.0f,-5.0f*/));
	}
	
}
