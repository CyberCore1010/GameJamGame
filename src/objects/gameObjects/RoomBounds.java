package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import physics.MathsMethods;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RoomBounds extends GameObject{
    double width, height;
    int fade = 255;
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
                if(object.x > x-10 && object.x < x+width+10 && object.y > y-10 && object.y < y+height+10) {
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
                } else {
                    visible = false;
                }
            }
        }
        fade();
    }

    private void fade() {
        int fadeSpeed = 5;
        if(visible) {
            if(fade > 0) {
                fade-=fadeSpeed;
            }
        } else {
            if(fade < 255) {
                fade+=fadeSpeed;
            }
        }
        fade = (int)MathsMethods.clamp(fade, 0, 255);
    }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            graphics.setColor(new Color(0, 0, 0, fade));
            graphics.fillRect((int)x, (int)y, (int)width, (int)height);
        };

        Graphics2D g2d = (Graphics2D) g;
        //renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return bounds;
    }
}
