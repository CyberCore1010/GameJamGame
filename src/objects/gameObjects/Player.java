package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.handlers.MusicPlayer;
import objects.interfaces.Drawable;
import objects.handlers.KeyHandler;
import objects.misc.BufferedImageLoader;
import objects.misc.Camera;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject{
    private double width, height;
    private boolean movable = true;
    private Camera camera;
    private boolean moving;

    //music
    private MusicPlayer playerWalking;
    private MusicPlayer enemyLeft;
    private MusicPlayer enemyRight;
    private final int MAXDETECT=300;
    private final int TRANSITIONTIME=1;

    private int moveTime = 0;
    private int moveState = 1;
    private Map<Integer, BufferedImage> spriteMap;


    public Player(double x, double y, int z, double width, double height, Game game) {
        super(x, y, z,0, GameObjectID.Player,game);
        camera = game.cameraMap.get(CameraID.Main);
        this.width = width;
        this.height = height;
        velX = 2.5;
        velY = 2.5;
        spriteMap = new HashMap<>();
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteMap.put(0, loader.loadImage("/sprites/player/1.png"));
        spriteMap.put(1, loader.loadImage("/sprites/player/2.png"));
        spriteMap.put(2, loader.loadImage("/sprites/player/3.png"));
        spriteMap.put(3, loader.loadImage("/sprites/player/4.png"));
        spriteMap.put(4, loader.loadImage("/sprites/player/5.png"));
        spriteMap.put(5, loader.loadImage("/sprites/player/6.png"));
        spriteMap.put(6, loader.loadImage("/sprites/player/7.png"));
        spriteMap.put(7, loader.loadImage("/sprites/player/8.png"));
        spriteMap.put(8, loader.loadImage("/sprites/player/9.png"));
        spriteMap.put(9, loader.loadImage("/sprites/player/10.png"));

        //music
        playerWalking = new MusicPlayer(game.musicHandler.getAC(),game.musicHandler.getTrack("playerSteps"),1.5f,1,true);
        enemyLeft = new MusicPlayer(game.musicHandler.getAC(),game.musicHandler.getTrack("whisper1Left"),0,10,true);
        enemyRight = new MusicPlayer(game.musicHandler.getAC(),game.musicHandler.getTrack("whisper1Right"),0,10,true);

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void update() {
        camera.setX(x);
        camera.setY(y);
        if(movable) {
            collision();
            move();
        }
        if(moving){
            if(!playerWalking.isPlaying()){
                playerWalking.resume();
            }
        }else{
            if(playerWalking.isPlaying()){
                playerWalking.pause();
            }
        }

        //audio
        int closestLeft=Integer.MAX_VALUE;
        int closestRight=Integer.MAX_VALUE;
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Enemy){
                if(object.y >= y-MAXDETECT && object.y <= y+MAXDETECT) {
                    if(object.x > x && (object.x - x)<MAXDETECT){
                        //further right
                        closestRight=(int)(object.x - x);
                    }else if(object.x < x && (x - object.x)<MAXDETECT){
                        //further left
                        closestLeft=(int)(x - object.x);
                    }
                }
            }
        }

        if(closestLeft==Integer.MAX_VALUE){
            if(enemyLeft.isPlaying()){enemyLeft.pause();}

        }else{
            if(!enemyLeft.isPlaying()){ enemyLeft.resume();}
            float perc = 100-(closestLeft*100)/MAXDETECT;
            enemyLeft.fade((6.0f/100f)*perc+3,TRANSITIONTIME);
        }

        if(closestRight==Integer.MAX_VALUE){
            if(enemyRight.isPlaying()){enemyRight.pause();}

        }else{
            if(!enemyRight.isPlaying()){ enemyRight.resume();}
            float perc = 100-(closestRight*100)/MAXDETECT;
            enemyRight.fade((6.0f/100f)*perc+3,TRANSITIONTIME);
        }

    }

    private void collision() {
        for(GameObject object : game.objectHandler.objects) {
            if(this.isColliding(object)){
                switch (object.id){
                    case Wall:
                        resolveCollision(object);
                        break;
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

        if(KeyHandler.isKeyPressed("W") && KeyHandler.isKeyPressed("D")) {
            setRotation(-0.75);
        } else if(KeyHandler.isKeyPressed("S") && KeyHandler.isKeyPressed("D")) {
            setRotation(0.75);
        } else if(KeyHandler.isKeyPressed("W") && KeyHandler.isKeyPressed("A")) {
            setRotation(-2.5);
        } else if(KeyHandler.isKeyPressed("S") && KeyHandler.isKeyPressed("A")) {
            setRotation(2.5);
        }
    }

    public void kill(){
        PopupLose lose = new PopupLose(0,0,0,0,GameObjectID.Popup,game);
        game.objectHandler.clear();
        game.objectHandler.add(lose);
        game.repaint();
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

    public Point2D.Double getPoint(){
        return new Point2D.Double(x,y);
    }
}
