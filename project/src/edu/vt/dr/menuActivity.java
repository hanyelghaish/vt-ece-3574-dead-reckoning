//Roger Baker Kim Do
//Nathan Gentzen Mike Sutton
//ECE 3574 - HW4
//02/07/2012
package edu.vt.dr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
        Button straight;
        straight = (Button) findViewById(R.id.straightButton);
        
        Button lshape;
        lshape = (Button) findViewById(R.id.lshapeButton);
        
        Button circle;
        circle = (Button) findViewById(R.id.circleButton);
        
        straight.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent gotoMenu = new Intent("edu.vt.dr.STRAIGHTACTIVITY");
              startActivity(gotoMenu);
			}
        	
        });
        
        lshape.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent gotoMenu = new Intent("edu.vt.dr.LSHAPEACTIVITY");
              startActivity(gotoMenu);
			}
        	
        });
        
        
        circle.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent gotoMenu = new Intent("edu.vt.dr.CIRCLEACTIVITY");
              startActivity(gotoMenu);
			}
        	
        });
        
        
	}
	
	

}
