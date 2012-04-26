package edu.vt.dr.map;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import edu.vt.dr.utilities.FloatPoint;
import edu.vt.dr.utilities.LocationUtil;

public class straightGLRenderer implements Renderer{
	
	
	private Object 	room = new Object(edu.vt.dr.R.drawable.mapstraight_ptwo,1.0f,4.0f,0.0f,0,0,0);
	private Object 	theman = new Object(edu.vt.dr.R.drawable.androidicon_ptwo, 1.0f, 1.0f, 0, 0f, 0f,0);
    private Context	context;
	private FloatBuffer shapeBuffer;

	private void initShapes() {	
		
		float[] shapeCoords =
			{
				-0.25f, 0.0f, 0.0f,		//pointer triangle
				 0.0f,  0.5f, 0.0f,
				 0.25f, 0.0f, 0.0f,
				-0.5f, -0.5f, 0.0f,		//home square
				 0.5f, -0.5f, 0.0f,
				 0.5f,  0.5f, 0.0f,
				-0.5f,  0.5f, 0.0f
			};
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(shapeCoords.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		shapeBuffer = vbb.asFloatBuffer();
		shapeBuffer.put(shapeCoords);
		shapeBuffer.position(0);
	}

    /** Constructor to set the handed over context */
    public straightGLRenderer(Context context/*, float x, float y, float z*/) {
    	this.context = context;
    }
 
    public void onDrawFrame(GL10 gl) {
    	
    	FloatPoint p = LocationUtil.getCurrentLocation();
    	
        // clear Screen and Depth Buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        
        // Reset the Modelview Matrix
        gl.glLoadIdentity();
        
        
        // Draw the room
        gl.glTranslatef(0.0f, 0.0f, -3.0f);
        gl.glColor4f(1f, 1f, 1f, 1f);
        room.draw(gl);
        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glColor4f(0f, 0f, 0f, 1f);
        
        //draw pointer
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glPushMatrix();
	        gl.glTranslatef(p.getX(), p.getY(), 0.0f);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, shapeBuffer);
			gl.glRotatef(LocationUtil.getCurrentAzimuthDegrees()-90, 0, 0, 1);
			gl.glScalef(0.2f, 0.2f, 1f);
			gl.glTranslatef(0.0f, 0.8f, 0.0f);
	        gl.glColor4f(0f, 0.5f, 0f, 1f);
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 3);
		gl.glPopMatrix();
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		//draw trail
		gl.glPushMatrix();
			gl.glTranslatef(0f, 0f, 0.0f);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, LocationUtil.getCrumbBuffer());
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, LocationUtil.getCrumbBufferSize());
		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        
        //block moving dot from moving over the walls
        float x = p.getX();
        float y = p.getY();
        
        boolean setloc = false;
        
        if (x>0.28) 	{x=0.28f; 	setloc = true;}
        if (x<-0.28) 	{x=-0.28f;	setloc = true;}
        if (y>0.95)		{y=0.95f;	setloc = true;}
        if (y<-0.95)	{y=-0.95f; 	setloc = true;}
        
        if (setloc) LocationUtil.setLocation(x, y);
        
        gl.glColor4f(1f, 1f, 1f, 1f);
        //draw the avatar
        gl.glPushMatrix();
        	gl.glTranslatef(x, y, 0);
        	gl.glScalef(0.5f, 0.5f, 0.5f);
        	
        	gl.glPushMatrix();
        		gl.glRotatef(180 ,0.0f, 0.0f, 1.0f);
        		theman.draw(gl);
        	gl.glPopMatrix();
        gl.glPopMatrix();
    }
 
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if(height == 0) {                       //Prevent A Divide By Zero By
            height = 1;                         //Making Height Equal One
        }
 
        gl.glViewport(0, 0, width, height);     //Reset The Current Viewport
        gl.glMatrixMode(GL10.GL_PROJECTION);    //Select The Projection Matrix
        gl.glLoadIdentity();                    //Reset The Projection Matrix
 
        //Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
 
        gl.glMatrixMode(GL10.GL_MODELVIEW);     //Select The Modelview Matrix
        gl.glLoadIdentity();                    //Reset The Modelview Matrix
    }
 
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    	initShapes();
    	
    	// Load the texture for the square
    	    room.loadGLTexture(gl, context);
    	    theman.loadGLTexture(gl, context);
    	   
    	    gl.glEnable(GL10.GL_TEXTURE_2D);            //Enable Texture Mapping ( NEW )
    	    gl.glShadeModel(GL10.GL_FLAT);            //Enable Smooth Shading
    	    gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f);    //Black Background
    	    gl.glClearDepthf(1.0f);                     //Depth Buffer Setup
    	    gl.glEnable(GL10.GL_DEPTH_TEST);            //Enables Depth Testing
    	    gl.glDepthFunc(GL10.GL_LEQUAL);             //The Type Of Depth Testing To Do

    	    //Really Nice Perspective Calculations
    	    gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    	}

}

