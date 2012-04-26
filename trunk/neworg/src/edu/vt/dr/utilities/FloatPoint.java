/*************************************************************************

  FloatPoint
  	
  	MH 3.27.2012
  	
*************************************************************************/

package edu.vt.dr.utilities;

public class FloatPoint {
	private float x;
	private float y;
	
	public float getX() { return x; }
	public float getY() { return y; }
	
	public FloatPoint(float ix, float iy) {
		x = ix;
		y = iy;
	}
	
	public void set(float newx, float newy) {
		x = newx;
		y = newy;
	}
	
	public void offset(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	public float distance() {
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
	}
	
	public float distanceFrom(FloatPoint other) {
		return (float)Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}
