package objects.gameObjects;

import game.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Wall extends GameObject{
    private int width, height;
    public Wall(int x, int y, int width, int height, Game game) {
        super(x, y, 0, 0, GameObjectID.Wall, game);
        this.width = width;
         this.height = height;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}
