/*************************************************************************

  RotationHandler
  
  	Event routing for events of TYPE_ROTATION_VECTOR
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.utilities;

import android.hardware.SensorEvent;

public class RotationHandler implements EventHandler {
	public void service(SensorEvent e) {
		LocationUtil.rotationUpdate(e);
	}
}
