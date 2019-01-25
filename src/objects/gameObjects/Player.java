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
    private int moveTime = 0;
    private int moveState = 1;
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
        super(x, y, z,0, GameObjectID.Player,game);
        camera = game.cameraMap.get(CameraID.Main);
        this.width = width;
        this.height = height;
        velX = 2;
        velY = 2;
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
            collision();
            move();
        }
    }

    void collision() {
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Wall) {
                if(getBounds().intersects(object.getBounds())) {
                    boolean xBlock = false;
                    boolean yBlock = false;
                    if(x <= object.getBounds().x || x >= object.getBounds().x+object.getBounds().width) {
                        xBlock = true;
                    }
                    if(y <= object.getBounds().y || y >= object.getBounds().y+object.getBounds().height) {
                        yBlock = true;
                    }
                    if(xBlock && !yBlock) {
                        if(x <= object.getBounds().x) {
                            x += velX * -1;
                        } if(x >= object.getBounds().x+object.getBounds().width) {
                            x -= velX * -1;
                        }
                    }
                    if(!xBlock && yBlock) {
                        if(y <= object.getBounds().y) {
                            y += velY * -1;
                        } if(y >= object.getBounds().y+object.getBounds().height) {
                            y -= velY * -1;
                        }
                    }
                }
            }
        }
    }

    private void move() {
        if(KeyHandler.isKeyPressed("W") || KeyHandler.isKeyPressed("S") || KeyHandler.isKeyPressed("A") || KeyHandler.isKeyPressed("D")) {
            moving = true;
            moveTime++;
            if(moveTime <= 50) {
                if(moveTime == 5) {
                    moveState = 2;
                } else if(moveTime == 10) {
                    moveState = 3;
                } else if(moveTime == 15) {
                    moveState = 4;
                } else if(moveTime == 20) {
                    moveState = 5;
                } else if(moveTime == 25) {
                    moveState = 6;
                } else if(moveTime == 30) {
                    moveState = 7;
                } else if(moveTime == 35) {
                    moveState = 8;
                } else if(moveTime == 40) {
                    moveState = 9;
                } else if(moveTime == 45) {
                    moveState = 10;
                }
            } else {
                moveState = 1;
                moveTime = 0;
            }
        } else {
            moving = false;
        }
        if(KeyHandler.isKeyPressed("W")){
            y-=velY;
            setRotation(-1.5);
        }
        if(KeyHandler.isKeyPressed("S")){
            y+=velY;
            setRotation(1.5);
        }
        if(KeyHandler.isKeyPressed("A")){
            x-=velX;
            setRotation(-3);
        }
        if(KeyHandler.isKeyPressed("D")){
            x+=velX;
            setRotation(0);
        }
    }

    @Override
    public void render(Graphics g) {
        Drawable player = (graphics)->{
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.rotate(getRotation(), x, y);
            if(moving) {
                switch(moveState) {
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
            graphics.rotate(-getRotation(), x, y);
        };
        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(player, g2d, camera);
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x-(width/4), y-(height/4), width/2, height/2);
    }
}
