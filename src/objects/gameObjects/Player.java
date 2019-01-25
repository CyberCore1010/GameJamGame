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
import java.security.Key;

public class Player extends GameObject{
    private double width, height;
    private double velX,velY;
    private boolean movable = true;
    private Camera camera;

    private boolean moving;
    private int moveTime = 1;
    private BufferedImage one;
    private BufferedImage two;
    private BufferedImage three;
    private BufferedImage four;
    private BufferedImage five;
    private BufferedImage six;
    private BufferedImage seven;
    private BufferedImage eight;
    private BufferedImage nine;
    private BufferedImage ten;

    public Player(double x, double y, int z, double width, double height, Game game) {
        super(x+(width/2),y+(height/2),z,0, GameObjectID.Player,game);
        camera = game.cameraMap.get(CameraID.Main);
        this.width = width;
        this.height = height;
        velX = 4;
        velY = 4;
        BufferedImageLoader loader = new BufferedImageLoader();
        one = loader.loadImage("/player/1.png");
        two = loader.loadImage("/player/2.png");
        three = loader.loadImage("/player/3.png");
        four = loader.loadImage("/player/4.png");
        five = loader.loadImage("/player/5.png");
        six = loader.loadImage("/player/6.png");
        seven = loader.loadImage("/player/7.png");
        eight = loader.loadImage("/player/8.png");
        nine = loader.loadImage("/player/9.png");
        ten = loader.loadImage("/player/10.png");
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
        if(KeyHandler.isKeyPressed("W") || KeyHandler.isKeyPressed("S") || KeyHandler.isKeyPressed("A") || KeyHandler.isKeyPressed("D")) {
            moving = true;
            moveTime++;
            if(moveTime > 10) {
                moveTime = 0;
            }
        } else {
            moving = false;
        }
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
            if(moving) {
                switch(moveTime) {
                    case 1:
                        graphics.drawImage(one, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 2:
                        graphics.drawImage(two, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 3:
                        graphics.drawImage(three, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 4:
                        graphics.drawImage(four, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 5:
                        graphics.drawImage(five, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 6:
                        graphics.drawImage(six, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 7:
                        graphics.drawImage(seven, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 8:
                        graphics.drawImage(eight, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 9:
                        graphics.drawImage(nine, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                    case 10:
                        graphics.drawImage(ten, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
                        break;
                }
            } else {
                graphics.drawImage(one, (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
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
