package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.misc.PathGenerator;
import objects.misc.PathList;

import java.awt.*;

public class Hunter extends Enemy {

    public Hunter(int x, int y, Game game, PathList path, PathGenerator generator) {
        super(x, y, game, path, generator);
        velX = 2;
        velY = 2;
    }

    @Override
    public void update() {
        if(canSeePlayer()){

            seeingPlayer = true;
            seenPlayer = true;
            setLastPlayerPosition();
            moveToPoint(playerLastPosition);
        }
        else {
            followPath();
        }

    }

    @Override
    public void render(Graphics g) {
        Drawable enemy = (graphics)->{
            graphics.setColor(Color.blue);
            graphics.fillRect((int)(x-width/2),(int)(y-height/2),(int)width,(int)height);
        };
        Graphics2D g2d = (Graphics2D)g;
        renderToCamera(enemy,g2d,game.cameraMap.get(CameraID.Main));

    }

}
