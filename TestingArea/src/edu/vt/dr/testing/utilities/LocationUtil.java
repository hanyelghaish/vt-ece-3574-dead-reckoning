package edu.vt.dr.testing.utilities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class LocationUtil {
	
	private static final float CRUMB_RADIUS = 0.25f;
	private static final float STEP_ACCEL_THRESHOLD = 1f;
	private static final int MAX_POINTS = 250;
	private static final int NUM_ACCEL_SAMPLES = 4;
	
	private static float[] mRotationVector = new float[4];
	private static float[] mRotationMatrix = new float[16];
	private static float mAccelerationAvg = 0;
	private static float mAzimuth;
	private static float mSpeed;
	
	private static Deque<FloatPoint> mBreadCrumbs;
	private static FloatBuffer crumbBuffer;
	private static float crumbCoords[] = new float[MAX_POINTS * 3];
	private static FloatPoint mLocation = new FloatPoint(0,0);
	
	private static int sampleIndex = 0;
	private static boolean averageReady = false;
	private static float samples[] = new float[NUM_ACCEL_SAMPLES];
	
	public static void reset() {
		mBreadCrumbs.clear();
		mBreadCrumbs.add(new FloatPoint(0,0));
		mLocation.set(0, 0);
	}
	
	public static void init() {
		mBreadCrumbs = new LinkedList<FloatPoint>();
		mBreadCrumbs.add(new FloatPoint(0,0));
		//mSpeed = 0.2f;
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(crumbCoords.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		crumbBuffer = cbb.asFloatBuffer();
		crumbBuffer.put(crumbCoords);
		crumbBuffer.position(0);
	}
	
	public static FloatBuffer getCrumbBuffer() {
		return crumbBuffer;
	}
	
	public static int getCrumbBufferSize() {
		return mBreadCrumbs.size();
	}
	
	public static void accelerationUpdate(SensorEvent e) {
		float magnitude = (float)Math.sqrt(Math.pow(e.values[0], 2) + Math.pow(e.values[1],2) + Math.pow(e.values[2],2));
		
		samples[(sampleIndex++) % NUM_ACCEL_SAMPLES] = magnitude;
		
		if (sampleIndex > 31 || averageReady) {
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
	
	public static void takeStep() {
		mSpeed = 0.03f;
	}
	
	public static void speedDecay() {
		/*mSpeed *= 0.8f;
		if (mSpeed < 0.001f)
			mSpeed = 0.0f;*/
		mSpeed = 0;
	}
	
	public static float getAverageAcceleration() {
		return mAccelerationAvg;
	}
	
	public static void rotationUpdate(SensorEvent e) {
		float projected[] = new float[3];
		
		mRotationVector = Arrays.copyOf(e.values, e.values.length);
		SensorManager.getRotationMatrixFromVector(mRotationMatrix, e.values);
		SensorManager.getOrientation(mRotationMatrix, projected);
		
		mAzimuth = projected[0];
		updateLocation();
	}
	
	public static float[] getCurrentRotationMatrix() {
		return mRotationMatrix;
	}
	
	public static float[] getCurrentRotationVector() {
		return mRotationVector;
	}
	
	public static float getCurrentAzimuth() {
		return mAzimuth;
	}
	
	public static float getCurrentAzimuthDegrees() {
		return mAzimuth * (float)(180/Math.PI);
	}
	
	public static float getCurrentSpeed() {
		return mSpeed;
	}
	
	public static FloatPoint getCurrentLocation() {
		return mLocation;
	}
	
	public static void updateLocation() {
		float dx = (float)Math.cos(getCurrentAzimuth()) * getCurrentSpeed();
		float dy = (float)Math.sin(getCurrentAzimuth()) * getCurrentSpeed();
		
		mLocation.offset(dx, dy);
		speedDecay();
		
		FloatPoint last = mBreadCrumbs.getLast();
		if (mLocation.distanceFrom(last) >= CRUMB_RADIUS) {
			
			if (mBreadCrumbs.size() >= MAX_POINTS)
				mBreadCrumbs.removeFirst();
			
			mBreadCrumbs.addLast(new FloatPoint(mLocation.getX(), mLocation.getY()));
			
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
}
