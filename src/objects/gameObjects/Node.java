package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.misc.ObjectList;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Node extends GameObject {
    public static double size = 50;
    private Point2D.Double point;
    private Color color;
    public boolean junciton = false;
    public int heuristicValue;
    public ObjectList<Node> adjacentJunctions;

    public Node(Point2D.Double point, Game game){
        super(point.x,point.y,0,0,GameObjectID.Node,game);
        adjacentJunctions = new ObjectList<>();
        this.point = point;
    }

    public Node(double x,double y,Game game){
        super(x,y,0,0,GameObjectID.Node,game);
        this.point = new Point2D.Double(x,y);
    }

    public Point2D.Double getPoint(){
        return this.point;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        Drawable node = (graphics)->{
            graphics.setColor(color);
            graphics.fillRect((int)(x-size/2),(int)(y-size/2),(int)size,(int)size);
        };
        Graphics2D g2d = (Graphics2D)g;
        renderToCamera(node,g2d,game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
