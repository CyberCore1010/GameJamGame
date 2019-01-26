package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RoomBounds extends GameObject{
    double width, height;
    Rectangle2D.Double bounds;

    private boolean visible = false;
    Point2D.Double middlePoint;
    Line2D.Double line = new Line2D.Double(0, 0, 0, 0);

    public RoomBounds(double x, double y, double width, double height, Game game) {
        super(x, y, 0, 0, GameObjectID.RoomBounds, game);
        this.width = width;
        this.height = height;
        middlePoint = new Point2D.Double(x+(width/2), y+(height/2));
        bounds = new Rectangle2D.Double(x, y, width, height);
    }

    @Override
    public void update() {
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Player) {
                line.x1 = middlePoint.x;
                line.y1 = middlePoint.y;
                line.x2 = object.x;
                line.y2 = object.y;
                visible = true;
                for(GameObject block : game.objectHandler.objects) {
                    if(block.id == GameObjectID.Wall) {
                        if(line.intersects(block.getBounds())) {
                            visible = false;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(!visible) {
            Drawable drawable = (graphics)->{
                graphics.setColor(new Color(0, 0, 0, 230));
                graphics.fillRect((int)x, (int)y, (int)width, (int)height);
            };

            Graphics2D g2d = (Graphics2D) g;
            renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Main));
        }
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return bounds;
    }
}
