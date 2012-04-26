/*************************************************************************

  LocationUtil
  
  	Convenience functions for calculating location based on compass
  	heading and acceleration
  	
  	MH - 3.27.2012
  	
*************************************************************************/
package edu.vt.dr.utilities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class LocationUtil {
	
	//variables and constants
	//*************************************************************************
	//*************************************************************************
	
	//step parameters
	//*************************************************************************
	private static final float	STEP_INCREMENT = 0.015f;
	private static final float	STEP_ACCEL_THRESHOLD = 1.0f;
	private static final int	NUM_ACCEL_SAMPLES = 8;
	private static final float	SCALE_TO_FEET = 7.0f;
	
	//visual trail parameters
	//*************************************************************************
	private static final float	CRUMB_RADIUS = 0.09f;
	private static final int	MAX_POINTS = 250;

	//location and orientation
	//*************************************************************************
	private static FloatPoint mLocation = new FloatPoint(0,0);
	private static float[] mRotationVector = new float[4];
	private static float[] mRotationMatrix = new float[16];
	private static float mAccelerationAvg = 0;
	private static float mAzimuth;
	private static float mAzimuthOffset = 0;
	private static float mSpeed;
	private static float mTotalDistance = 0;
	
	//crumb trail
	//*************************************************************************
	private static Deque<FloatPoint> mBreadCrumbs;
	private static FloatBuffer crumbBuffer;
	private static float crumbCoords[] = new float[MAX_POINTS * 3];
	
	//averaging
	//*************************************************************************
	private static int sampleIndex = 0;
	private static boolean averageReady = false;
	private static float samples[] = new float[NUM_ACCEL_SAMPLES];
	
	//member functions
	//*************************************************************************
	//*************************************************************************
	
	//reset and init
	//*************************************************************************
	public static void reset() {
		initPosition(0,0);
	}
	
	public static void initPosition(float x, float y) {
		mBreadCrumbs.clear();
		mBreadCrumbs.add(new FloatPoint(x,y));
		mLocation.set(x, y);
		mTotalDistance = 0;		
	}
	
	public static void init() {
		mBreadCrumbs = new LinkedList<FloatPoint>();
		mBreadCrumbs.add(new FloatPoint(0,0));
		mTotalDistance = 0;
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(crumbCoords.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		crumbBuffer = cbb.asFloatBuffer();
		crumbBuffer.put(crumbCoords);
		crumbBuffer.position(0);
	}
	
	//rotationUpdate
	//
	//Takes a 3d rotation vector and isolates the rotation about the z-axis
	//*************************************************************************
	public static void rotationUpdate(SensorEvent e) {
		float projected[] = new float[3];
		
		mRotationVector = Arrays.copyOf(e.values, e.values.length);
		SensorManager.getRotationMatrixFromVector(mRotationMatrix, e.values);
		SensorManager.getOrientation(mRotationMatrix, projected);
		
		mAzimuth = -projected[0];
		updateLocation();
	}

	//accelerationUpdate
	//
	//Keeps a running average of the magnitude of the phone's acceleration.
	//When the instantaneous acceleration exceeds the average acceleration by
	//a threshold value, a step is recorded
	//*************************************************************************
	public static void accelerationUpdate(SensorEvent e) {
		float magnitude = (float)Math.sqrt(
			Math.pow(e.values[0],2) + 
			Math.pow(e.values[1],2) + 
			Math.pow(e.values[2],2)
			);
		
		samples[(sampleIndex++) % NUM_ACCEL_SAMPLES] = magnitude;
		
		if (sampleIndex > NUM_ACCEL_SAMPLES || averageReady) {
			averageReady = true;
			mAccelerationAvg = samples[0];
			for (int i=1; i<NUM_ACCEL_SAMPLES; ++i)
				mAccelerationAvg += samples[i];
			
			mAccelerationAvg /= (float)NUM_ACCEL_SAMPLES;
			
			if (Math.abs(magnitude-mAccelerationAvg) > STEP_ACCEL_THRESHOLD) {
				takeStep();
				updateLocation();
			}
		}
	}
	
	//takeStep / speedDecay
	//Control the velocity associated with taking a step
	//*************************************************************************
	public static void takeStep() {
		mSpeed = STEP_INCREMENT;
	}
	
	public static void speedDecay() {
		mSpeed = 0;
	}
	
	public static void setLocation(float x, float y) {
		
		mLocation.set(x, y);
		
		FloatPoint last = mBreadCrumbs.getLast();
		float dd = mLocation.distanceFrom(last);
		
		updateCrumbs(dd);
	}
	
	//updateLocation
	//*************************************************************************
	public static void updateLocation() {
		float dx = (float)Math.cos(getCurrentAzimuth()) * getCurrentSpeed();
		float dy = (float)Math.sin(getCurrentAzimuth()) * getCurrentSpeed();
		float dd = (float)Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
		
		mLocation.offset(dx, dy);
		speedDecay();
		updateCrumbs(dd);
	}
	
	private static void updateCrumbs(float dd) {
		FloatPoint last = mBreadCrumbs.getLast();		
		mTotalDistance += dd;
		
		if ( mLocation.distanceFrom(last) >= CRUMB_RADIUS) {
			
			if (mBreadCrumbs.size() >= MAX_POINTS)
				mBreadCrumbs.removeFirst();
			
			mBreadCrumbs.addLast(new FloatPoint(
				mLocation.getX(),
				mLocation.getY())
				);
			
			int i=0;
			for( FloatPoint p : mBreadCrumbs) {
				crumbCoords[i++] = p.getX();
				crumbCoords[i++] = p.getY();
				crumbCoords[i++] = 0;
			}
			
			crumbBuffer.put(crumbCoords);
			crumbBuffer.position(0);
		}
	}
	
	//Generic accessor functions
	//*************************************************************************
	public static float[] getCurrentRotationMatrix() {
		return mRotationMatrix;
	}
	
	public static float[] getCurrentRotationVector() {
		return mRotationVector;
	}
	
	public static void setAzimuthOffset(float o) {
		mAzimuthOffset = o;
	}
	
	public static float getCurrentAzimuth() {
		return mAzimuth + mAzimuthOffset;
	}
	
	public static float getCurrentAzimuthDegrees() {
		return getCurrentAzimuth() * (float)(180/Math.PI);
	}
	
	public static float getCurrentSpeed() {
		return mSpeed;
	}
	
	public static FloatPoint getCurrentLocation() {
		return mLocation;
	}
	
	public static float getAverageAcceleration() {
		return mAccelerationAvg;
	}
	
	public static FloatBuffer getCrumbBuffer() {
		return crumbBuffer;
	}
	
	public static int getCrumbBufferSize() {
		return mBreadCrumbs.size();
	}
	
	public static float getTotalDistance() {
		return mTotalDistance;
	}
	
	public static float getTotalDistanceFeet() {
		return mTotalDistance * SCALE_TO_FEET;
	}
}
