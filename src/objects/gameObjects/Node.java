package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;
import objects.misc.ObjectList;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Node extends GameObject {
    public static double size = 50;
    private Point2D.Double point;
    private Color color;
    public boolean junction = false;
    public Node parent;
    public ObjectList<Node> children;
    public int score = Integer.MAX_VALUE;

    public Node(Point2D.Double point, Game game){
        super(point.x,point.y,0,0,GameObjectID.Node,game);
        children = new ObjectList<>();
        parent = null;
        this.point = point;
    }

    public Node(double x,double y,Game game){
        super(x,y,0,0,GameObjectID.Node,game);
        this.point = new Point2D.Double(x,y);
        parent = null;
        children = new ObjectList<>();
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
            graphics.setColor(Color.black);
            graphics.fillOval((int)x,(int)y,1,1);
        };
        Graphics2D g2d = (Graphics2D)g;
        renderToCamera(node,g2d,game.cameraMap.get(CameraID.Main));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(point, node.point);
    }

    @Override
    public int hashCode() {

        return Objects.hash(point);
    }
}
