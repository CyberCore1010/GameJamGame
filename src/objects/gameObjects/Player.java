package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.handlers.KeyHandler;
import objects.misc.BufferedImageLoader;
import objects.misc.Camera;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
    private double width, height;
    private double velX,velY;
    private boolean movable = true;
    private Camera camera;

    private BufferedImage upSprite;
    private BufferedImage downSprite;
    private BufferedImage leftSprite;
    private BufferedImage rightSprite;

    private int direction = 1;

    public Player(double x, double y, int z, double width, double height, Game game) {
        super(x+(width/2),y+(height/2),z,0, GameObjectID.Player,game);
        camera = game.cameraMap.get(CameraID.Main);
        this.width = width;
        this.height = height;
        velX = 4;
        velY = 4;
        BufferedImageLoader loader = new BufferedImageLoader();
        upSprite = loader.loadImage("/player/playerUp.png");
        downSprite = loader.loadImage("/player/playerDown.png");
        leftSprite =  loader.loadImage("/player/playerLeft.png");
        rightSprite = loader.loadImage("/player/playerRight.png");
    }

    @Override
    public void update() {
        camera.setX(x);
        camera.setY(y);
        if(movable) {
            move();
        }
    }

    private void move() {
        if(KeyHandler.isKeyPressed("W")){
            y-=velY;
            direction = 1;
        }
        if(KeyHandler.isKeyPressed("S")){
            y+=velY;
            direction = 2;
        }
        if(KeyHandler.isKeyPressed("A")){
            x-=velX;
            direction = 3;
        }
        if(KeyHandler.isKeyPressed("D")){
            x+=velX;
            direction = 4;
        }
    }

    @Override
    public void render(Graphics g) {
        Drawable player = (graphics)->{
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            switch(direction) {
                case 1:
                    graphics.drawImage(upSprite, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                    break;
                case 2:
                    graphics.drawImage(downSprite, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                    break;
                case 3:
                    graphics.drawImage(leftSprite, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                    break;
                case 4:
                    graphics.drawImage(rightSprite, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                    break;
            }
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(player, g2d,camera);
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
