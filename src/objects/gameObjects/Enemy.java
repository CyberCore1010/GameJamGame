package objects.gameObjects;

import game.Game;
import objects.misc.PathGenerator;
import objects.misc.PathList;
import physics.MathsMethods;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Enemy extends GameObject {
    protected double width,height;

    //boolean switching cases
    protected boolean seeingPlayer = false;
    protected boolean seenPlayer = false;
    protected boolean onPath = true;
    protected boolean searching = false;

    //attributes for working out line of sight
    protected Point2D.Double playerLastPosition;
    protected Line2D line;
    protected int fieldOfView = 90;

    //attributes for pathing system
    private PathGenerator generator;
    private PathList path;
    protected Node nextPoint;
    protected Node currentPos;

    public Enemy(int x, int y, Game game,PathList path, PathGenerator generator) {
        super(x, y, 1, 0, GameObjectID.Enemy, game);
        width = 50;
        height = 50;
        this.generator = generator;
        this.path = path;

        playerLastPosition = new Point2D.Double();
        nextPoint = path.getClosetNode(getPoint());
        currentPos = new Node(x,y,game);
    }


    protected boolean canSeePlayer() {
        Point2D.Double playerPosition = getPlayer().getPoint();
        double playerRotation = findRotation(getPoint(),playerPosition);
        double angleDistance = getRotation()- playerRotation;
        double playerDistance = getPoint().distance(playerPosition);
        if(isSightClear() && playerDistance < 500000 && angleDistance < Math.PI/2) {
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * moves to the specified point
     * @param point
     */
    protected void moveToPoint(Point2D.Double point) {
        setRotation(findRotation(getPoint(),point));
        double[] unitVector = MathsMethods.getUnitVector(x,y,point.getX(),point.getY());
        if(MathsMethods.distance(x, y, point.getX(), point.getY())>1) {
            x+=unitVector[0]*velX;
            y+=unitVector[1]*velY;
        }
    }

    /**
     * works out if there are any walls in between the enemy and the player
     * @return
     */
    private boolean isSightClear(){
        try{
            Player player = getPlayer();
            line = new Line2D.Double(x,y,player.x,player.y);
            for(GameObject object : game.objectHandler.objects) {
                if(object.id == GameObjectID.Wall) {
                    if(line.intersects(object.getBounds())) {
                        return false;
                    }
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

    private double findRotation(Point2D.Double start , Point2D.Double point){
        double xDiff = point.x-start.x;
        double yDiff = point.y-start.y;
        double angle = Math.atan2(yDiff,xDiff);
        return angle;
    }

    /**
     * sets the last known position of the player
     */
    protected void setLastPlayerPosition(){
        Player player = getPlayer();
        playerLastPosition.setLocation(player.getX(), player.getY());
    }

    /**
     * follows
     */
    protected void followPath() {
        currentPos.getPoint().setLocation(x, y);
        if(path.hasReachedNext(currentPos)) {
            nextPoint = path.getNextNode();
            if(nextPoint.equals(generator.goalNode)) {
                path = generator.getPathList(nextPoint.getPoint(), game.grid.getRandomNode().getPoint());
            }
        }
        else {
            moveToPoint(nextPoint.getPoint());
        }

    }

    private Player getPlayer(){
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Player) {
                return (Player)object;
            }
        }
        return null;
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x-width/4, y-height/4, width/2, height/2);
    }

    private Point2D.Double getPoint(){
        return new Point2D.Double(x,y);
    }
}
