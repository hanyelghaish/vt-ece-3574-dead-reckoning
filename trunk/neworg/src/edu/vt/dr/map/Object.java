package edu.vt.dr.map;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import edu.vt.dr.*;

public class Object {

	//private float DIMENSION_ = 0.2f;
	
	private FloatBuffer vertexBuffer;   // buffer holding the vertices
	public Wall boundaries_;
	private int iconID_;
	 
    private float vertices[] = {
            0.0f, 0.0f,  0.0f,        // V1 - bottom left
            0.0f, 0.0f,  0.0f,        // V2 - top left
            0.0f, 0.0f,  0.0f,        // V3 - bottom right
            0.0f,  0.0f,  0.0f         // V4 - top right
    };
    
    private FloatBuffer textureBuffer;  // buffer holding the texture coordinates
    private float texture[] = {
            // Mapping coordinates for the vertices
            0.0f, 0.0f,     // bottom left   (V2)
            0.0f, 1.0f,     // top left  (V1)
            1.0f, 0.0f,     // bottom right    (V4)
            1.0f, 1.0f      // top right (V3)
    };

    //CONSTRUCTOR
    public Object(int iconID, float dimension_x, float dimension_y, float dimension_z,
    		float position_x, float position_y, float position_z) {
    	
    	iconID_ = iconID;
    	
    	boundaries_ = new Wall(	position_y + dimension_y/2,
				position_y - dimension_y/2,
				position_x - dimension_x/2,
				position_x + dimension_x/2);
    	
    	//SET VERTICES
    	// V1 - bottom left
    	vertices[0] = (boundaries_.left_);
    	vertices[1] = (boundaries_.bottom_); 
    	// V2 - top left
    	vertices[3] = (boundaries_.left_);
    	vertices[4] = (boundaries_.top_); 
    	 // V3 - bottom right
    	vertices[6] = (boundaries_.right_);
    	vertices[7] = (boundaries_.bottom_);  
    	// V4 - top right
    	vertices[9] = (boundaries_.right_);
    	vertices[10] = (boundaries_.top_); 
    	
    	
    	//SET TEXTURE VERTICES
    	// texture_V1 - top left
    	//texture[0] = (-dimension_x/2);
    	//texture[1] = (dimension_y); 
    	// texture_V2 - bottom left
    	//texture[2] = (-dimension_x/2);
    	//texture[3] = (-dimension_y/2); 
    	 // texture_V3 - top right
    	//texture[4] = (dimension_x);
    	//texture[5] = (dimension_y);  
    	// texture_V4 - bottom right
    	//texture[6] = (dimension_x);
    	//texture[10] = (-dimension_y);
    	
    	
    	ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
    	byteBuffer.order(ByteOrder.nativeOrder());
    	vertexBuffer = byteBuffer.asFloatBuffer();
    	vertexBuffer.put(vertices);
    	vertexBuffer.position(0);
    	
    	byteBuffer = ByteBuffer.allocateDirect(texture.length*4);
    	byteBuffer.order(ByteOrder.nativeOrder());
    	textureBuffer = byteBuffer.asFloatBuffer();
    	textureBuffer.put(texture);
    	textureBuffer.position(0);
    }
 
    /** The draw method for the square with the GL context */

    public void draw(GL10 gl) {
    	
    	gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    	gl.glEnable(GL10.GL_BLEND);
    	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    	
    	//bind the previously generated texture
    	gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
    	
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        
        gl.glFrontFace(GL10.GL_CW);
        
        
        //gl.glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
       
 
        // Point to our vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        
        // Draw the vertices as triangle strip
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }
    
    
    //Create texture:
    private int[] textures = new int[1];

    public void loadGLTexture(GL10 gl, Context context){
    	
    	Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), iconID_);
    	
    	//generate one texture pointer
    	gl.glGenTextures(1, textures,0);
    	//... and bind it to array
    	gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
    	
    	//create nearest filtered texture
    	gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
    	gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
    	
    	// Use Android GLUtils to specify a two-dimensional texture image from our bitmap
    	 GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    	 
    	 //Clean up
    	 bitmap.recycle();

    }
    
    public class Wall{
    	public float top_;
    	public float bottom_;
    	public float left_;
    	public float right_;
    	
    	public Wall(float top, float bottom, float left, float right){
    		top_ = top;
    		bottom_ = bottom;
    		left_ = left;
    		right_ = right;
    	}
    }
    
}
