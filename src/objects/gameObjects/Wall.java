package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Wall extends GameObject{
    private int width, height;
    public Wall(int x, int y, int width, int height, Game game) {
        super(x, y, 0, 0, GameObjectID.Wall, game);
        this.width = width;
         this.height = height;
    }

    @Override
    public void update() { }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            g.setColor(Color.DARK_GRAY);
            g.fillRect((int)x, (int)y, width, height);
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}
