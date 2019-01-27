package objects.gameObjects;

import game.Game;
import objects.misc.Camera;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
 * methods to be used with every game object. The constructor simply sets the variables that will be used by each
 * GameObject, such as the x and y position.
 */
public abstract class GameObject {
    protected double x,y;
    protected double velX,velY;
    private double rotation;
    protected int z;
    protected GameObjectID id;
    protected Game game;

    public GameObject(double x, double y , int z ,double rotation, GameObjectID id, Game game) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.velX = 0;
        this.velY = 0;
        this.rotation = rotation;
        this.id = id;
        this.game = game;
    }

    //These are the abstract methods and other methods used in every game object.
    public abstract void update();
    public abstract void render (Graphics g);

    //method for getting the bounding rectangle of the object
    public abstract Rectangle2D.Double getBounds();

    //method for rendering the game object to a specific camera
    protected void renderToCamera(Drawable item, Graphics2D g2d, Camera camera){
        g2d.setTransform(camera.getTransform());
        item.draw(g2d);
    }

    protected boolean isColliding(GameObject g){
        if(g.getBounds() == null){
            return false;
        }
        else if(getBounds().intersects(g.getBounds())){
            return true;
        }
        else{
            return false;
        }
    }

    protected void resolveCollision(GameObject g){
        if(x <= g.getBounds().x) {
            x += velX * -1;
        } if(x >= g.getBounds().x+g.getBounds().width) {
            x -= velX * -1;
        }
        if(y <= g.getBounds().y) {
            y += velY * -1;
        } if(y >= g.getBounds().y+g.getBounds().height) {
            y -= velY * -1;
        }
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public GameObjectID getId() {
        return id;
    }

    public void setId(GameObjectID id) {
        this.id = id;
    }
}