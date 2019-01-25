package objects.misc;

import java.awt.geom.AffineTransform;

public class Camera {
    private AffineTransform transform;
    private double x,y;
    private double zoom;
    private double width,height;

    public Camera(double x, double y, double zoom, double width, double height) {
        transform = new AffineTransform();
        transform.setToTranslation(width/2,height/2);
        transform.translate(-x,-y);
        transform.scale(zoom,zoom);
        this.x = x;
        this.y = y;
        this.zoom = zoom;
        this.width = width;
        this.height = height;
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
        this.y = y;
    }

    public void setZoom(double zoom){
        this.zoom = zoom;
    }

    public double getZoom(){
        return zoom;
    }

    public AffineTransform getTransform(){
        transform.setToScale(0,0);
        transform.setToTranslation(width/2,height/2);
        transform.scale(zoom,zoom);
        transform.translate(-x,-y);
        return transform;
    }
}