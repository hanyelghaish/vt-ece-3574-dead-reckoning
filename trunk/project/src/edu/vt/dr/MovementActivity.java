

package edu.vt.dr;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Hutton
 * 
 * This class is for displaying filtered data from devices components. 
 * 
 *
 *
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * MH                  2/22/12              Initial creation, 
 ******************************************************************************/

public class MovementActivity extends Activity implements SensorEventListener {
	/** Called when the activity is first created. */
	private SensorManager mSensorManager;
	private Sensor mAccelerometer, mMagnet, mLinearAccel;
	private TextView xyBox, angleBox, linearAccelBox;
	private float accelArray[] = new float[3];
	private float magneticArray[] = new float[3];
	private float rotationArray[] = new float[16];//9
	private float inclinationArray[] = new float[9];
	private float worldAccelArray[] = new float[4];//3
	private float linearAccelArray[] = new float[4];//3
	private float positionArray[] = new float[3];
	private float velocityArray[] = new float[3];
	private static final float aScaleFactor= (float)0.03;
	private static final float vScaleFactor= (float)0.03;

	boolean buttonStateStart = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movement);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mMagnet = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mLinearAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		
		xyBox = (TextView) findViewById(R.id.xyDisplay);
		angleBox = (TextView) findViewById(R.id.angleDisplay);
		linearAccelBox = (TextView) findViewById(R.id.linearAccelDisplay);

		Button start = (Button) findViewById(R.id.button1);

		start.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (buttonStateStart) {
					((Button) v).setText("STOP");
					mSensorManager.registerListener(MovementActivity.this, mAccelerometer,
							SensorManager.SENSOR_DELAY_FASTEST);
					mSensorManager.registerListener(MovementActivity.this, mMagnet,
							SensorManager.SENSOR_DELAY_FASTEST);
					mSensorManager.registerListener(MovementActivity.this, mLinearAccel,
							SensorManager.SENSOR_DELAY_FASTEST);
				} else {
					mSensorManager.unregisterListener(MovementActivity.this, mAccelerometer);
					mSensorManager.unregisterListener(MovementActivity.this, mMagnet);
					mSensorManager.unregisterListener(MovementActivity.this, mLinearAccel);
					for(int i=0; i<3;i++){ positionArray[i]=0;}
					
					((Button) v).setText("START");
				}
				
				buttonStateStart = !buttonStateStart;

			}

		});

	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	public void onSensorChanged(SensorEvent event) {
		int type = event.sensor.getType();

		if (type == Sensor.TYPE_ACCELEROMETER) {
			accelArray[0] = event.values[0];
			accelArray[1] = event.values[1];
			accelArray[2] = event.values[2];

			
		} else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
			magneticArray[0] = event.values[0];
			magneticArray[1] = event.values[1];
			magneticArray[2] = event.values[2];
		}
		else if(type == Sensor.TYPE_LINEAR_ACCELERATION){
			linearAccelArray[0] = event.values[0];
			linearAccelArray[1] = event.values[1];
			linearAccelArray[2] = event.values[2];
			linearAccelBox.setText(String.format("%.2f, %.2f, %.2f", linearAccelArray[0], linearAccelArray[1], linearAccelArray[2]));
			
			
			for(int i=0; i<3;i++)
			{
				if(Math.abs(worldAccelArray[i])>.01)
					velocityArray[i]+=aScaleFactor*worldAccelArray[i];
				if(Math.abs(velocityArray[i])>.01)
					positionArray[i]+=vScaleFactor*velocityArray[i];
				
				if(Math.abs(velocityArray[i]) >= .01)
					velocityArray[i]-=(Math.signum(velocityArray[i])*.01);
			}
			
			xyBox.setText(String.format("%.2f, %.2f", positionArray[0],
					positionArray[1]));
			
			
		}

		SensorManager.getRotationMatrix(rotationArray, inclinationArray,
				accelArray, magneticArray);
		//SensorManager.getOrientation(rotationArray, worldAccelArray);
		
	
		Matrix.transposeM(rotationArray, 0, rotationArray, 0);
		
		Matrix.multiplyMV(worldAccelArray, 0, rotationArray, 0, linearAccelArray, 0);
		
		angleBox.setText(String.format("%.2f, %.2f, %.2f", worldAccelArray[0],
				worldAccelArray[1], worldAccelArray[2]));
		
		
		
		
		
		
		
		
		

	}

}