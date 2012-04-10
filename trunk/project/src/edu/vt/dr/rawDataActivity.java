package edu.vt.dr;
import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/******************************************************************************
 * 
 * @author Roger Baker, Kim Do, Mike Hutton
 * 
 * This class is for reading input from the accelerometer. 
 * 
 * It is called upon when the Raw data button is pressed on the initial main screen.
 *
 * From here, the user must use a "Back" button on the phone to return to the maps menu
 *  (i.e. there is no back button in the GUI) 
 *
 * Last Revision:
 * 
 * Initials                 Date                Revisions
 * MH                       2/9/12              Initial writing, computations and implementation
 * RJB                      2/21/12          Cleaned code; documentation added 
 * 
 ******************************************************************************/



public class rawDataActivity extends Activity implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private int sampleCount;
	private long lastTime;
	private TextView tv, tv2, xview, yview, zview, mview;
	private ProgressBar p1, p2, p3, p4, p5, p6, p7, p8;
	private float [] samples = new float[8];
	int a= 0;
	
	private FloatFFT_1D fft = new FloatFFT_1D(8);
	
	
	/** when Activity is resumed **/
	public void onResume() {
		//call to super
		super.onResume();
		//re-register sensor manager to accelerometer
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
		//update last time, reset number of samples
		lastTime = SystemClock.uptimeMillis();
		sampleCount = 0;
	}
	
	/** when Activity is paused **/
	public void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	
	/**when Activity is first created **/
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readings);
        
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		tv = (TextView)findViewById(R.id.sampleCount);
		tv2 = (TextView)findViewById(R.id.frequency);
		xview = (TextView)findViewById(R.id.xval);
		yview = (TextView)findViewById(R.id.yval);
		zview = (TextView)findViewById(R.id.zval);
		mview = (TextView)findViewById(R.id.mval);
		p1 = (ProgressBar)findViewById(R.id.progressBar1);
		p2 = (ProgressBar)findViewById(R.id.progressBar2);
		p3 = (ProgressBar)findViewById(R.id.progressBar3);
		p4 = (ProgressBar)findViewById(R.id.progressBar4);
		p5 = (ProgressBar)findViewById(R.id.progressBar5);
		p6 = (ProgressBar)findViewById(R.id.progressBar6);
		p7 = (ProgressBar)findViewById(R.id.progressBar7);
		p8 = (ProgressBar)findViewById(R.id.progressBar8);
		
		p1.setMax(30);
		p2.setMax(30);
		p3.setMax(30);
		p4.setMax(30);
		p5.setMax(30);
		p6.setMax(30);
		p7.setMax(30);
		p8.setMax(30);
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
    
    /** when change is detected **/
    public void onSensorChanged(SensorEvent event) {
    	
    	++sampleCount;
    	long delta = SystemClock.uptimeMillis() - lastTime;
    	float freq = (sampleCount / (float)delta) * 1000;
    	
    	tv.setText(Integer.toString(sampleCount));
    	tv2.setText(String.format("%.2f Hz", freq));
    	
    	float x = event.values[0];
    	float y = event.values[1];
    	float z = event.values[2];
    	double mag = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z,2));
    	
    	xview.setText(String.format("%.2f m/s^2", x));
    	yview.setText(String.format("%.2f m/s^2", y));
    	zview.setText(String.format("%.2f m/s^2", z));
    	mview.setText(String.format("%.2f m/s^2", mag));
    	
    	//compute 8-point FFT of magnitude
    	samples[sampleCount % 8] = (float)mag;
    	if (sampleCount % 8 == 0) {
    		float results[] = samples;
    		fft.realForward(samples);
    		p1.setProgress((int)results[0]);
    		p2.setProgress((int)results[1]);
    		p3.setProgress((int)results[2]);
    		p4.setProgress((int)results[3]);
    		p5.setProgress((int)results[4]);
    		p6.setProgress((int)results[5]);
    		p7.setProgress((int)results[6]);
    		p8.setProgress((int)results[7]);
    	}
    }
 }