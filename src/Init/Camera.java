package Init;

import Objects.GameObject;

public class Camera {
    private static float x, y;

    /**
     * @param x - Default x location of the player
     * @param y - Default y location of the player
     */
    Camera(float x, float y) {
        Camera.x = x;
        Camera.y = y;
    }

    /**
     * @param object - takes a GameObject as a parameter and sets it's x and y positions to that objects x and y,
     * taking away the game width and height divided by two to center the object on the screen
     */
    public void update(GameObject object) {

    }

    /**
     * @return - returns the x value of the camera
     */
    public float getX() {
        return x;
    }

    /**
     * @param x - takes the x position passed in and sets the camera's x to it
     */
    public void setX(float x) {
        Camera.x = x;
    }

    /**
     * @return - returns the x value of the camera
     */
    public float getY() {
        return y;
    }

    /**
     * @param y - takes the y position passed in and sets the camera's y to it
     */
    public void setY(float y) {
        Camera.y = y;
    }
}