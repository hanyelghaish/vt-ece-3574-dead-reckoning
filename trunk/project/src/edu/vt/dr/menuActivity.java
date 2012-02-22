package edu.vt.dr;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Nathan Gentzen, Mike Sutton
 * 
 * This class is for the menu of selecting a map. 
 * 
 * It is called upon when the Maps button is pressed on the initial main screen.
 *
 * From here, the user will navigate to one of three maps (straight, L-shape, or circle).
 *
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * RJB                      2/21/12          Cleaned code; documentation added 
 * 
 ******************************************************************************/
public class menuActivity extends Activity {
	
        private static final String CIRCLEACTIVITY = "edu.vt.dr.CIRCLEACTIVITY";
		private static final String LSHAPEACTIVITY = "edu.vt.dr.LSHAPEACTIVITY";
		private static final String STRAIGHTACTIVITY = "edu.vt.dr.STRAIGHTACTIVITY";
		Button circle, lshape, straight;        


	protected void onCreate(Bundle savedInstanceState) {
		
		// call to super, sets GUI
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
        //associate buttons with IDs 
        straight = (Button) findViewById(R.id.straightButton);
        lshape = (Button) findViewById(R.id.lshapeButton);
        circle = (Button) findViewById(R.id.circleButton);
        
        //associates listener for button for straight map
        straight.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
		      Intent gotoMenu = new Intent(STRAIGHTACTIVITY);
              startActivity(gotoMenu);
			}
        	
        });
        
        //associates listener for button for L-shaped map
        lshape.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
		      Intent gotoMenu = new Intent(LSHAPEACTIVITY);
              startActivity(gotoMenu);
			}	
        });
        
        //associates listener for button circle map
        circle.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
		      Intent gotoMenu = new Intent(CIRCLEACTIVITY);
              startActivity(gotoMenu);
			}	
        });
        
	}

}
