package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Floor extends GameObject{
    double width;
    double height;

    public Floor(double x, double y, double width, double height, Game game) {
        super(x, y, 0, 0, GameObjectID.Floor, game);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            g.setColor(Color.WHITE);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}