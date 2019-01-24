package objects.misc;

import java.awt.geom.AffineTransform;

public class Camera {
    private AffineTransform transform;

    public Camera(double x, double y, double zoom) {
        transform = new AffineTransform();
        transform.translate(-x,-y);
        transform.scale(zoom,zoom);
    }

    /**
     * @return - returns the x value of the camera
     */
    public double getX() {
        return transform.getTranslateX();
    }

    /**
     * @param x - takes the x position passed in and sets the camera's x to it
     */
    public void setX(float x) {
        this.transform.translate(-x,transform.getTranslateY());
    }

    /**
     * @return - returns the x value of the camera
     */
    public double getY() {
        return transform.getTranslateY();
    }

    /**
     * @param y - takes the y position passed in and sets the camera's y to it
     */
    public void setY(float y) {
        transform.translate(transform.getTranslateX(), -y);
    }

    public void setZoom(float zoom){
        transform.scale(zoom,zoom);
    }

    public AffineTransform getTransform(){
        return transform;
    }
}