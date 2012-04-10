package edu.vt.dr;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Hutton
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
 * RJB                      3/10/12           Compacted code a bit
 * RJB                      3/12/12
 ******************************************************************************/
public class menuActivity extends Activity {
	
        private static final String CIRCLEACTIVITY = "edu.vt.dr.CIRCLEMAP";
		private static final String LSHAPEACTIVITY = "edu.vt.dr.LSHAPEMAP";
		private static final String STRAIGHTACTIVITY = "edu.vt.dr.STRAIGHTMAP";

	protected void onCreate(Bundle savedInstanceState) {
		
		// call to super, sets GUI
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
        //associate buttons with IDs 
        Button straight = (Button) findViewById(R.id.straightButton);
        Button lshape = (Button) findViewById(R.id.lshapeButton);
        Button circle = (Button) findViewById(R.id.circleButton);
        
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
