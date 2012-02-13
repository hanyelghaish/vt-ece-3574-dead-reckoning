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

public class mainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button map;
        map = (Button) findViewById(R.id.mapButton);
        
        
        map.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
		      Intent gotoMenu = new Intent("edu.vt.dr.MENUACTIVITY");
              startActivity(gotoMenu);
			}
        	
        });
        
        Button data;
        data = (Button) findViewById(R.id.dataButton);
        
        data.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent gotoData = new Intent("edu.vt.dr.RAWDATAACTIVITY");
				startActivity(gotoData);
				
				
			}
		});
        
        
        

    }
}