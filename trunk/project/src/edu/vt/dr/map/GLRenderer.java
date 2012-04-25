package edu.vt.dr.map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.vt.dr.testing.utilities.FloatPoint;
import edu.vt.dr.testing.utilities.LocationUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class GLRenderer implements Renderer {
	
	private float ROW1_Y = -0.099f;
	private float ROW2_Y = 0.24f;
	private float ROW3_Y = 0.57f;
	private float ROW4_Y = 0.9f;
	
	
	
	public Object room= new Object(edu.vt.dr.R.drawable.room,3.2f,2.3f,0.0f,0,0,0);
	
	public Object theman = new Object(edu.vt.dr.R.drawable.androidicon, 0.3f, 0.3f, 0, 1.3f, -0.8f,0);
	
	public Object table= new Object(edu.vt.dr.R.drawable.table,0.7f,0.4f,0.0f,0.26667f,-0.61666f,0);
	
	public Object podium= new Object(edu.vt.dr.R.drawable.podium,0.9f,0.5f,0.0f,-0.8f,-0.61666f,0);
	
	
	public Object desk_1_1= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.838f,ROW1_Y,0);
	public Object desk_1_2= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.5985f,ROW1_Y,0);
	public Object desk_1_3= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.359f,ROW1_Y,0);
	public Object desk_1_4= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.1195f,ROW1_Y,0);
	public Object desk_1_5= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.1195f,ROW1_Y,0);
	public Object desk_1_6= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.359f,ROW1_Y,0);
	public Object desk_1_7= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.5985f,ROW1_Y,0);
	public Object desk_1_8= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.838f,ROW1_Y,0);
	
	public Object desk_2_0= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 1.096f,ROW2_Y,0);
	public Object desk_2_1= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.838f,ROW2_Y,0);
	public Object desk_2_2= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.5985f,ROW2_Y,0);
	public Object desk_2_3= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.359f,ROW2_Y,0);
	public Object desk_2_4= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.1195f,ROW2_Y,0);
	public Object desk_2_5= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.1195f,ROW2_Y,0);
	public Object desk_2_6= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.359f,ROW2_Y,0);
	public Object desk_2_7= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.5985f,ROW2_Y,0);
	public Object desk_2_8= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.838f,ROW2_Y,0);
	public Object desk_2_9= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -1.096f,ROW2_Y,0);
	
	public Object desk_3_0= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 1.096f,ROW3_Y,0);
	public Object desk_3_1= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.838f,ROW3_Y,0);
	public Object desk_3_2= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.5985f,ROW3_Y,0);
	public Object desk_3_3= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.359f,ROW3_Y,0);
	public Object desk_3_4= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.1195f,ROW3_Y,0);
	public Object desk_3_5= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.1195f,ROW3_Y,0);
	public Object desk_3_6= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.359f,ROW3_Y,0);
	public Object desk_3_7= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.5985f,ROW3_Y,0);
	public Object desk_3_8= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.838f,ROW3_Y,0);
	public Object desk_3_9= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -1.096f,ROW3_Y,0);
	
	public Object desk_4_00= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 1.354f,ROW4_Y,0);
	public Object desk_4_0= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 1.096f,ROW4_Y,0);
	public Object desk_4_1= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.838f,ROW4_Y,0);
	public Object desk_4_2= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.5985f,ROW4_Y,0);
	public Object desk_4_3= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.359f,ROW4_Y,0);
	public Object desk_4_4= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, 0.1195f,ROW4_Y,0);
	public Object desk_4_5= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.1195f,ROW4_Y,0);
	public Object desk_4_6= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.359f,ROW4_Y,0);
	public Object desk_4_7= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.5985f,ROW4_Y,0);
	public Object desk_4_8= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -0.838f,ROW4_Y,0);
	public Object desk_4_9= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -1.096f,ROW4_Y,0);
	public Object desk_4_10= new Object(edu.vt.dr.R.drawable.desk,0.2f,0.22f,0.0f, -1.354f,ROW4_Y,0);
	
    private Context		context;
    
    //private float	position[] = new float [3];

    /** Constructor to set the handed over context */
    public GLRenderer(Context context/*, float x, float y, float z*/) {
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
       
        gl.glPushMatrix();
        gl.glRotatef(180f, 0, 1.0f, 0.0f);
        room.draw(gl);
        gl.glPopMatrix();
        
        table.draw(gl);
        podium.draw(gl);
        
        
 
        desk_1_1.draw(gl);
        desk_1_2.draw(gl);
        desk_1_3.draw(gl);
        desk_1_4.draw(gl);
        desk_1_5.draw(gl);
        desk_1_6.draw(gl);
        desk_1_7.draw(gl);
        desk_1_8.draw(gl);
        
        desk_2_0.draw(gl);
        desk_2_1.draw(gl);
        desk_2_2.draw(gl);
        desk_2_3.draw(gl);
        desk_2_4.draw(gl);
        desk_2_5.draw(gl);
        desk_2_6.draw(gl);
        desk_2_7.draw(gl);
        desk_2_8.draw(gl);
        desk_2_9.draw(gl);
        
        desk_3_0.draw(gl);
        desk_3_1.draw(gl);
        desk_3_2.draw(gl);
        desk_3_3.draw(gl);
        desk_3_4.draw(gl);
        desk_3_5.draw(gl);
        desk_3_6.draw(gl);
        desk_3_7.draw(gl);
        desk_3_8.draw(gl);
        desk_3_9.draw(gl);
        
        desk_4_00.draw(gl);
        desk_4_0.draw(gl);
        desk_4_1.draw(gl);
        desk_4_2.draw(gl);
        desk_4_3.draw(gl);
        desk_4_4.draw(gl);
        desk_4_5.draw(gl);
        desk_4_6.draw(gl);
        desk_4_7.draw(gl);
        desk_4_8.draw(gl);
        desk_4_9.draw(gl);
        desk_4_10.draw(gl);
        
        
        //block moving dot from moving over the walls
        float x = p.getX();
        float y = p.getY();
        
        if (x>1.45f) x=1.45f-1.3f;
        if (x<-1.45f) x=-1.45f-1.3f;
        if (y>0.9f) y=0.9f+0.8f;
        
        if (y<-0.9f) y=-0.9f+0.8f;
        
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
    	   
    	    
    	    table.loadGLTexture(gl, context);
    	    podium.loadGLTexture(gl,context);
    	    desk_1_1.loadGLTexture(gl, context);
    	    desk_1_2.loadGLTexture(gl, context);
    	    desk_1_3.loadGLTexture(gl, context);
    	    desk_1_4.loadGLTexture(gl, context);
    	    desk_1_5.loadGLTexture(gl, context);
    	    desk_1_6.loadGLTexture(gl, context);
    	    desk_1_7.loadGLTexture(gl, context);
    	    desk_1_8.loadGLTexture(gl, context);
    	    
    	    desk_2_0.loadGLTexture(gl, context);
    	    desk_2_1.loadGLTexture(gl, context);
    	    desk_2_2.loadGLTexture(gl, context);
    	    desk_2_3.loadGLTexture(gl, context);
    	    desk_2_4.loadGLTexture(gl, context);
    	    desk_2_5.loadGLTexture(gl, context);
    	    desk_2_6.loadGLTexture(gl, context);
    	    desk_2_7.loadGLTexture(gl, context);
    	    desk_2_8.loadGLTexture(gl, context);
    	    desk_2_9.loadGLTexture(gl, context);
    	    
    	    desk_3_0.loadGLTexture(gl, context);
    	    desk_3_1.loadGLTexture(gl, context);
    	    desk_3_2.loadGLTexture(gl, context);
    	    desk_3_3.loadGLTexture(gl, context);
    	    desk_3_4.loadGLTexture(gl, context);
    	    desk_3_5.loadGLTexture(gl, context);
    	    desk_3_6.loadGLTexture(gl, context);
    	    desk_3_7.loadGLTexture(gl, context);
    	    desk_3_8.loadGLTexture(gl, context);
    	    desk_3_9.loadGLTexture(gl, context);
    	    
    	    desk_4_00.loadGLTexture(gl, context);
    	    desk_4_0.loadGLTexture(gl, context);
    	    desk_4_1.loadGLTexture(gl, context);
    	    desk_4_2.loadGLTexture(gl, context);
    	    desk_4_3.loadGLTexture(gl, context);
    	    desk_4_4.loadGLTexture(gl, context);
    	    desk_4_5.loadGLTexture(gl, context);
    	    desk_4_6.loadGLTexture(gl, context);
    	    desk_4_7.loadGLTexture(gl, context);
    	    desk_4_8.loadGLTexture(gl, context);
    	    desk_4_9.loadGLTexture(gl, context);
    	    desk_4_10.loadGLTexture(gl, context);
    	    
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

