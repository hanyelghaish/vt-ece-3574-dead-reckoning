package edu.vt.dr;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Nathan Gentzen, Mike Sutton
 * 
 * This class is for displaying the L-Shaped Map. 
 * 
 * It is called upon when the L-Shaped button is pressed on the map selection screen.
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

public class LShapeMap extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.lshape);
	}

}
