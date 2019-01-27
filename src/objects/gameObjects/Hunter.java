package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.misc.BufferedImageLoader;
import objects.misc.PathGenerator;
import objects.misc.PathList;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Hunter extends Enemy {
    BufferedImage sprite;

    Point2D.Double lastPoint;

    public Hunter(int x, int y, Game game, PathList path, PathGenerator generator) {
        super(x, y, game, path, generator);
        lastPoint = new Point2D.Double(x, y);
        velX = 2;
        velY = 2;
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite = loader.loadImage("/sprites/player/1.png");
    }

    @Override
    public void update() {
        followPath();
    }

    @Override
    public void render(Graphics g) {
        Drawable enemy = (graphics)->{
            graphics.setColor(Color.blue);
            graphics.drawImage(sprite, (int)(x-width/2),(int)(y-height/2),(int)width,(int)height, null);
        };
        Graphics2D g2d = (Graphics2D)g;
        renderToCamera(enemy,g2d,game.cameraMap.get(CameraID.Main));

    }

}
