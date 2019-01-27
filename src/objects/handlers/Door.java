package objects.handlers;

import game.CameraID;
import game.Game;
import objects.gameObjects.GameObject;
import objects.gameObjects.GameObjectID;
import objects.interfaces.Drawable;
import objects.misc.BufferedImageLoader;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Door extends GameObject {
    BufferedImage spriteOpen;
    BufferedImage spriteClose;
    Boolean open = false, openOnce = true, closeOnce = false;

    public Door(double x, double y, double rotation, Game game) {
        super(x, y, 0, rotation, GameObjectID.Door, game);
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteOpen = loader.loadImage("/sprites/doorOpen.png");
        spriteClose = loader.loadImage("/sprites/doorClose.png");
    }

    @Override
    public void update() {
        open = false;
        boolean found = false;
        for(GameObject object : game.objectHandler.objects) {
            if(object.getId() == GameObjectID.Player || object.getId() == GameObjectID.Enemy) {
                if(object.getX() > x-20 && object.getX() < x+50+20 && object.getY() > y+20 && object.getY() < y+50+20) {
                    found = true;
                    open = true;
                    closeOnce = true;
                    if(openOnce) {
                        openOnce = false;
                        MusicPlayer player = new MusicPlayer(game.musicHandler.getAC(), game.musicHandler.getTrack("doorOpen"), 2, 0, false);
                        player.resume();
                    }
                }
            }
        }
        if(!found && closeOnce) {
            closeOnce = false;
            openOnce = true;
            MusicPlayer player = new MusicPlayer(game.musicHandler.getAC(), game.musicHandler.getTrack("doorClose"), 2, 0, false);
            player.resume();
        }
    }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            if(open) {
                graphics.drawImage(spriteOpen, (int)x, (int)y, 54, 50, null);
            } else {
                graphics.drawImage(spriteClose, (int)x, (int)y, 54, 50, null);
            }
        };
        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
