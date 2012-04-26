/*************************************************************************

  EventHandler
  
  	Sensor event routing interface
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.utilities;

import android.hardware.SensorEvent;

public interface EventHandler {
	public void service(SensorEvent e);
}
