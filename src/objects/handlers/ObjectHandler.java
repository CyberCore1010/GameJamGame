package objects.handlers;

import objects.gameObjects.GameObject;
import objects.misc.ObjectList;

import java.awt.*;
import java.util.List;

/**
 * a class to handle all the objects in the game
 */
public class ObjectHandler {
    //This is the collection that stores each game object, made public, allowing the game to access them easily
    //throughout the code
    public List<GameObject> objects = new ObjectList<>();

    public ObjectHandler() {

    }

    /**
     * The update method will just loop through all objects in the handler and update them
     */
    public void update() {
        for (GameObject object : objects) {
            object.update();
        }
    }

    /**
     * The render method will just loop through all objects in the handler and render them
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    public void render(Graphics g) {
        for (GameObject object : objects) {
            object.render(g);
        }
    }

    /**
     * @param object - The objects to add to the list
     */
    public void add(GameObject object) {
        //Adds objects to list
        objects.add(object);
    }

    public void remove(GameObject object){
        objects.remove(object);
    }

    public void clear(){
        objects.clear();
    }
}