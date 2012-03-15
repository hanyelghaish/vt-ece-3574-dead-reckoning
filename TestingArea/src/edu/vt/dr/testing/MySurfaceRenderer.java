package edu.vt.dr.testing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.vt.dr.testing.utilities.SensorUtil;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class MySurfaceRenderer implements GLSurfaceView.Renderer {
	
	private FloatBuffer shapeBuffer;
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
		
		initShapes();
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);
	}

	private void initShapes() {	
		float shapeCoords[] = {
			-0.5f, -0.25f, 0.0f,
			 0.5f, -0.25f, 0.0f,
			 0.0f,	0.559016994f, 0.0f
		};
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(shapeCoords.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		shapeBuffer = vbb.asFloatBuffer();
		shapeBuffer.put(shapeCoords);
		shapeBuffer.position(0);
	}
	
	public void onDrawFrame(GL10 gl) {
		//clear the frame
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
        GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        
        //set up the points that DrawArrays will use
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, shapeBuffer);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
	}
}
