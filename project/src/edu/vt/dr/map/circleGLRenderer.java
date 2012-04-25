package edu.vt.dr.map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.vt.dr.testing.utilities.FloatPoint;
import edu.vt.dr.testing.utilities.LocationUtil;
import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class circleGLRenderer implements Renderer{

	public Object room= new Object(edu.vt.dr.R.drawable.mapcircle,1.6f,1.6f,0.0f,0,0,0);
	
	public Object theman = new Object(edu.vt.dr.R.drawable.androidicon, 0.18f, 0.18f, 0, -0.50f, 0f,0);
	
	
	
    private Context		context;
    
    //private float	position[] = new float [3];

    /** Constructor to set the handed over context */
    public circleGLRenderer(Context context/*, float x, float y, float z*/) {
    	this.context = context;
       
    }
 
    public void onDrawFrame(GL10 gl) {
    	
    	FloatPoint p = LocationUtil.getCurrentLocation();
    	
        // clear Screen and Depth Buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        
 
        // Reset the Modelview Matrix
        gl.glLoadIdentity();
        
		
        // Drawing
        gl.glTranslatef(0.0f, 0.0f, -3.0f);     // move 5 units INTO the screen
                                                // is the same as moving the camera 5 units away
       
        room.draw(gl);
        //block moving dot from moving over the walls
        float x = p.getX();
        float y = p.getY();
        float R = 0.7f;
        float R_small = 0.3f;
        
        if (y<R || y>-R)
        {
        	if (x>0){
        		if(x> Math.sqrt(R*R - y*y)){
        			x = (float) Math.sqrt(R*R - y*y);
        		}
        		if(x< Math.sqrt(R_small*R_small - y*y)){
        			x = (float) Math.sqrt(R_small*R_small - y*y);
        		}
        	}
        	else{
        		if(x< -Math.sqrt(R*R - y*y)){
        			x = (float) -Math.sqrt(R*R - y*y);
        		}
        		if(x> -Math.sqrt(R_small*R_small - y*y)){
        			x = (float) -Math.sqrt(R_small*R_small - y*y);
        		}
        	}
        }
        else if (x<R || x>-R)
        {
        	if (y>0){
        		if(y> Math.sqrt(R*R - x*x)){
        			y = (float) Math.sqrt(R*R - x*x);
        		}
        		if(y< Math.sqrt(R*R - x*x)){
        			y = (float) Math.sqrt(R_small*R_small - x*x);
        		}
        	}
        	else{
        		if(y> -Math.sqrt(R*R - x*x)){
        			x = (float) -Math.sqrt(R*R - x*x);
        		}
        		if(y> -Math.sqrt(R*R - x*x)){
        			y = (float) -Math.sqrt(R_small*R_small - x*x);
        		}

        	}
        }
        
        //-------------------------------------------
        
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);
        theman.draw(gl);
        gl.glPopMatrix();
        
     // gl.glTranslatef(position[0], position[1], position[2]);
     		//  man.draw(gl);
        
 
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
    	// Load the texture for the square
    	    room.loadGLTexture(gl, this.context);
    	    theman.loadGLTexture(gl, context);
    	   
    	 
    	    gl.glEnable(GL10.GL_TEXTURE_2D);            //Enable Texture Mapping ( NEW )
    	    gl.glShadeModel(GL10.GL_SMOOTH);            //Enable Smooth Shading
    	    gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f);    //Black Background
    	    gl.glClearDepthf(1.0f);                     //Depth Buffer Setup
    	    gl.glEnable(GL10.GL_DEPTH_TEST);            //Enables Depth Testing
    	    gl.glDepthFunc(GL10.GL_LEQUAL);             //The Type Of Depth Testing To Do

    	    //Really Nice Perspective Calculations
    	    gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    	}

}

