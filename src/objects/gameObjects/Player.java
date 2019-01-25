package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.handlers.KeyHandler;
import objects.misc.Camera;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
    private double x, y,width, height;
    private int z;
    private double velX,velY;
    private boolean movable = true;
    private BufferedImage bufferedImage;
    private Camera camera;


    public Player(double x, double y, int z, double width, double height,Game game) {
        super(x,y,z,0, GameObjectID.Player,game);
        this.width = width;
        this.height = height;
        velX = 4;
        velY = 4;
    }

    @Override
    public void update() {
        camera = game.cameraMap.get(CameraID.Main);
        camera.setX(x);
        camera.setY(y);
        if(movable) {
            move();
        }
    }

    private void move() {
        if(KeyHandler.isKeyPressed("W")){
            y-=velY;
        }
        if(KeyHandler.isKeyPressed("S")){
            y+=velY;
        }
        if(KeyHandler.isKeyPressed("A")){
            x-=velX;
        }
        if(KeyHandler.isKeyPressed("D")){
            x+=velX;
        }
    }

    @Override
    public void render(Graphics g) {
        Drawable player = (graphics)->{
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.RED);
            graphics.fillRect((int)x, (int)y, (int)width, (int)height);
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(player, g2d, game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
