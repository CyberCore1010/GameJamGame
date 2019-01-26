package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.misc.BufferedImageLoader;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Floor extends GameObject{
    double width;
    double height;
    private TexturePaint texture;

    public Floor(double x, double y, double width, double height, Game game) {
        super(x, y, 0, 0, GameObjectID.Floor, game);
        this.width = width;
        this.height = height;
        BufferedImageLoader loader = new BufferedImageLoader();
        texture = new TexturePaint(loader.loadImage("/floorTexture.png"), new Rectangle(0,0, 32, 32));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            graphics.setPaint(texture);
            graphics.fillRect((int)x, (int)y, (int)width, (int)height);
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
