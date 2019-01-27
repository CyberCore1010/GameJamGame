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
    BufferedImage horizontalSpriteOpen;
    BufferedImage horizontalSpriteClose;
    BufferedImage verticalSpriteOpen;
    BufferedImage verticalSpriteClose;
    Boolean horizontal, open = false, openOnce = true, closeOnce = false;

    public Door(double x, double y, boolean horizontal, Game game) {
        super(x, y, 0, 0, GameObjectID.Door, game);
        BufferedImageLoader loader = new BufferedImageLoader();
        this.horizontal = horizontal;
        horizontalSpriteOpen = loader.loadImage("/sprites/horizontalDoorOpen.png");
        horizontalSpriteClose = loader.loadImage("/sprites/horizontalDoorClose.png");
        verticalSpriteOpen = loader.loadImage("/sprites/verticalDoorOpen.png");
        verticalSpriteClose = loader.loadImage("/sprites/verticalDoorClose.png");
    }

    @Override
    public void update() {
        open = false;
        boolean found = false;
        for(GameObject object : game.objectHandler.objects) {
            if(object.getId() == GameObjectID.Player || object.getId() == GameObjectID.Enemy) {
                if(object.getX() > x-23 && object.getX() < x+50+20 && object.getY() > y && object.getY() < y+50+20) {
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
            if(horizontal) {
                if(open) {
                    graphics.drawImage(horizontalSpriteOpen, (int)x, (int)y, 54, 50, null);
                } else {
                    graphics.drawImage(horizontalSpriteClose, (int)x, (int)y, 54, 50, null);
                }
            } else {
                if(open) {
                    graphics.drawImage(verticalSpriteOpen, (int)x, (int)y, 54, 50, null);
                } else {
                    graphics.drawImage(verticalSpriteClose, (int)x, (int)y, 54, 50, null);
                }
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
