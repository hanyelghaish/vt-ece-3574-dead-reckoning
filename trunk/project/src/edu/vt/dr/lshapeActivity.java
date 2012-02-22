package edu.vt.dr;
import android.app.Activity;
import android.os.Bundle;

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

public class lshapeActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lshape);
	}

}
