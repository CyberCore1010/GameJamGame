package objects.misc;

import java.awt.geom.AffineTransform;

public class Camera {
    private AffineTransform transform;
    private double x,y;

    public Camera(double x, double y, double zoom) {
        transform = new AffineTransform();
        transform.translate(-x,-y);
        transform.scale(zoom,zoom);
    }

    /**
     * @return - returns the x value of the camera
     */
    public double getX() {
        return x;
    }

    /**
     * @param x - takes the x position passed in and sets the camera's x to it
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return - returns the x value of the camera
     */
    public double getY() {
        return y;
    }

    /**
     * @param y - takes the y position passed in and sets the camera's y to it
     */
    public void setY(double y) {

    }

    public void setZoom(double zoom){
        transform.scale(zoom,zoom);
    }

    public AffineTransform getTransform(){
        return transform;
    }
}