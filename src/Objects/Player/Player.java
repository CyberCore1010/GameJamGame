package Objects.Player;

import Objects.GameObject;
import Objects.Interfaces.Movable;
import Objects.Interfaces.Name;
import Objects.Interfaces.Positionable;
import Objects.Utility.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements Name, Movable, Positionable{
    private String name;
    private float x, y, z, width, height;
    private float velX = 0, velY = 0;
    private boolean movable = true;
    private BufferedImage bufferedImage;

    public Player(String name, float x, float y, float z, float width, float height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        g2d.fillRect((int)x, (int)y, (int)width, (int)height);
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
