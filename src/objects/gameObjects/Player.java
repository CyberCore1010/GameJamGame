package objects.gameObjects;

import init.Game;
import objects.interfaces.Drawable;
import objects.interfaces.Movable;
import objects.interfaces.Name;
import objects.interfaces.Positionable;
import objects.handlers.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements Name, Movable, Positionable{
    private String name;
    private float x, y, z, width, height;
    private float velX = 0, velY = 0;
    private boolean movable = true;
    private BufferedImage bufferedImage;
    private Game game;

    public Player(String name, float x, float y, float z, float width, float height,Game game) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    @Override
    public void update() {
        if(movable) {
            move(4);
        }
        x += velX;
        y += velY;
    }

    private void move(int walkSpeed) {
        if(KeyHandler.isKeyPressed("W")) {
            velY = -walkSpeed;
        } else if(!KeyHandler.isKeyPressed("S")){
            velX = 0;
        }
        if(KeyHandler.isKeyPressed("S")) {
            velY = walkSpeed;
        } else if(!KeyHandler.isKeyPressed("W")) {
            velY = 0;
        }
        if(KeyHandler.isKeyPressed("A")) {
            velX = -walkSpeed;
        } else if(!KeyHandler.isKeyPressed("D")) {
            velX = 0;
        }
        if(KeyHandler.isKeyPressed("D")) {
            velX = walkSpeed;
        } else if(!KeyHandler.isKeyPressed("A")) {
            velX = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        Drawable player = (graphics)->{
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.RED);
            graphics.fillRect((int)x, (int)y, (int)width, (int)height);
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(player, g2d, game.cameraList.get(0));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setMovable(boolean movable) {
        this.movable = movable;
    }
}
