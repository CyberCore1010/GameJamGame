package objects.gameObjects;

import game.Game;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TempObject extends GameObject{
    private boolean canSee = false;

    private Point2D.Double myPoint = new Point2D.Double(x+50, y+50);
    private Point2D.Double playerPoint = new Point2D.Double(0, 0);

    private Line2D line = new Line2D.Double(myPoint.x, myPoint.y, playerPoint.x, playerPoint.y);

    public TempObject(int x, int y, Game game) {
        super(x, y, 1, 0, GameObjectID.Enemy, game);
    }

    public void update() {
        myPoint = new Point2D.Double(x+50, y+50);

        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Player) {
                playerPoint = new Point2D.Double(object.x, object.y);
            }
        }

        canSee = true;
        line = new Line2D.Double(myPoint.x, myPoint.y, playerPoint.x, playerPoint.y);
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Wall) {
                if(line.intersects(object.getBounds())) {
                    canSee = false;
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            //graphics.
        };
        if(canSee) {
            g.setColor(Color.BLUE);
            g.fillRect((int)x, (int)y, 100, 100);
        }
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, 100, 100);
    }
}
