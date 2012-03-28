package edu.vt.dr.testing.utilities;

import android.hardware.SensorEvent;

public class AccelerationHandler implements EventHandler {
	public void service(SensorEvent e) {
		LocationUtil.accelerationUpdate(e);
	}
}
