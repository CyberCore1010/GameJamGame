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
import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject{
    private double width, height;
    private double velX,velY;
    private boolean movable = true;
    private Camera camera;

    private boolean moving;
    private int moveTime = 0;
    private int moveState = 1;
    private Map<Integer, BufferedImage> spriteMap;


    public Player(double x, double y, int z, double width, double height, Game game) {
        super(x, y, z,0, GameObjectID.Player,game);
        camera = game.cameraMap.get(CameraID.Main);
        this.width = width;
        this.height = height;
        velX = 2;
        velY = 2;
        spriteMap = new HashMap<>();
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteMap.put(0, loader.loadImage("/player/1.png"));
        spriteMap.put(1, loader.loadImage("/player/2.png"));
        spriteMap.put(2, loader.loadImage("/player/3.png"));
        spriteMap.put(3, loader.loadImage("/player/4.png"));
        spriteMap.put(4, loader.loadImage("/player/5.png"));
        spriteMap.put(5, loader.loadImage("/player/6.png"));
        spriteMap.put(6, loader.loadImage("/player/7.png"));
        spriteMap.put(7, loader.loadImage("/player/8.png"));
        spriteMap.put(8, loader.loadImage("/player/9.png"));
        spriteMap.put(9, loader.loadImage("/player/10.png"));
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
                    if(x <= object.getBounds().x) {
                        x += velX * -1;
                    } if(x >= object.getBounds().x+object.getBounds().width) {
                        x -= velX * -1;
                    }
                    if(y <= object.getBounds().y) {
                        y += velY * -1;
                    } if(y >= object.getBounds().y+object.getBounds().height) {
                        y -= velY * -1;
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
                    moveState = 1;
                } else if(moveTime == 10) {
                    moveState = 2;
                } else if(moveTime == 15) {
                    moveState = 3;
                } else if(moveTime == 20) {
                    moveState = 4;
                } else if(moveTime == 25) {
                    moveState = 5;
                } else if(moveTime == 30) {
                    moveState = 6;
                } else if(moveTime == 35) {
                    moveState = 7;
                } else if(moveTime == 40) {
                    moveState = 8;
                } else if(moveTime == 45) {
                    moveState = 9;
                }
            } else {
                moveState = 0;
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
                graphics.drawImage(spriteMap.get(moveState), (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
            } else {
                graphics.drawImage(spriteMap.get(0), (int)(x-(width/2)), (int)(y-(width/2)), (int)width, (int)height, null);
            }
            graphics.rotate(-getRotation(), x, y);
            graphics.setColor(Color.RED);
            graphics.drawRect((int)getBounds().x, (int)getBounds().y, (int)getBounds().width, (int)getBounds().height);
        };
        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(player, g2d, camera);
    }

    @Override
    public Rectangle2D.Double getBounds() {
        if(getRotation() == 0 || getRotation() == -3) {
            return new Rectangle2D.Double(x-(width/4), (y-(height/4))-5, width/2, (height/2)+10);
        }
        return new Rectangle2D.Double((x-(width/4)-5), y-(height/4), (width/2)+10, height/2);
    }
}
