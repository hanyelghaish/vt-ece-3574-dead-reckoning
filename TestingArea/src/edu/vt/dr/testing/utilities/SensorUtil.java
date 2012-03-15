package edu.vt.dr.testing.utilities;

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
	private static Sensor mMagnetometer;
	
	public static void init(Activity a){
		if (mSensorManager == null) {
			mSensorManager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
			mListener = (SensorEventListener)a;
		}
	}
	
	public static boolean systemMeetsRequirements() {
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
			if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null)
				if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null)
					return true;
		
		return false;
	}
	
	public static void registerListener() {
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	mSensorManager.registerListener(mListener, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    	
    	mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    	mSensorManager.registerListener(mListener, mMagnetometer, SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	public static void unregisterListener() {
		mSensorManager.unregisterListener(mListener);
	}
	
	public static void routeEvent(SensorEvent e) {

	}
}
