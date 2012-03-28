package edu.vt.dr.testing.utilities;

import android.hardware.SensorEvent;

public class RotationHandler implements EventHandler {
	public void service(SensorEvent e) {
		LocationUtil.rotationUpdate(e);
	}
}
