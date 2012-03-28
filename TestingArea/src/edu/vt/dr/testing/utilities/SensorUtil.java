/*************************************************************************

  SensorUtil
  
  	Convenience functions for routing sensor events
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.testing.utilities;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorUtil {
	
	private static SensorManager mSensorManager;
	private static SensorEventListener mListener;
	private static Sensor mAccelerometer;
	private static Sensor mVector;
	private static Map<Integer, EventHandler> mHandlers;
	
	public static void init(Activity a){
		if (mSensorManager == null) {
			mSensorManager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
			mListener = (SensorEventListener)a;
			mHandlers = new HashMap<Integer, EventHandler>();
		}
	}
	
	public static boolean systemMeetsRequirements() {
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
			if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null)
				return true;
		
		return false;
	}
	
	public static void registerListeners() {
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	mSensorManager.registerListener(mListener, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    	
    	mVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    	mSensorManager.registerListener(mListener, mVector, SensorManager.SENSOR_DELAY_FASTEST);
    	registerHandlers();
	}
	
	public static void unregisterListeners() {
		mSensorManager.unregisterListener(mListener);
		unregisterHandlers();
	}
	
	public static void registerHandlers() {
		mHandlers.put(Integer.valueOf(Sensor.TYPE_ROTATION_VECTOR), new RotationHandler());
		mHandlers.put(Integer.valueOf(Sensor.TYPE_ACCELEROMETER), new AccelerationHandler());
	}
	
	public static void unregisterHandlers() {
		mHandlers.clear();
	}
	
	public static void routeEvent(SensorEvent e) {
		EventHandler h = mHandlers.get(Integer.valueOf(e.sensor.getType()));
		if (h != null)
			h.service(e);
	}
}
