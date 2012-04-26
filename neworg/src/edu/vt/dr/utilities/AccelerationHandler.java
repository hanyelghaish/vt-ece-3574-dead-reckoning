/*************************************************************************

  AccelerationHandler
  
  	Event routing for events of TYPE_ACCELEROMETER
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.utilities;

import android.hardware.SensorEvent;

public class AccelerationHandler implements EventHandler {
	public void service(SensorEvent e) {
		LocationUtil.accelerationUpdate(e);
	}
}
