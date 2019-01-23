package Objects;

import java.awt.*;

public abstract class GameObject {

    /**This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
     * methods to be used with every game object. The constructor simply sets the variables that will be used by each
     * GameObject, such as the x and y position.
     */
    public GameObject() {
    }

    //These are the abstract methods and other methods used in every game object.
    public abstract void update();
    public abstract void render (Graphics g);

}