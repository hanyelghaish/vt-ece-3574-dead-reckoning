package edu.vt.dr;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListVMenu extends ListActivity {
	
	static final String[] mapOptions =new String[]{
		"PAM 3010", "Straight Map", "Circle Map", "LShapeMap"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setListAdapter( new ArrayAdapter<String>(this, R.layout.single_item, mapOptions));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String s = (String) ((TextView) arg1).getText();
				s=s.replace(" ","");
				s=s.toUpperCase();
			    String ns = "edu.vt.dr." + s;
				Intent gotoMenu = new Intent(ns);
				startActivity(gotoMenu);			
			}
			
			
		});
	}
	
	

}
