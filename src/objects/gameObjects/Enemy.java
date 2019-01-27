package objects.gameObjects;

import game.Game;
import objects.misc.PathGenerator;
import objects.misc.PathList;
import physics.MathsMethods;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Enemy extends GameObject {
    protected double velX,velY;
    protected double width,height;

    //boolean switching cases
    protected boolean canSeePlayer = false;
    protected boolean seenPlayer = false;
    protected boolean onPath = true;
    protected boolean searching = false;

    //attributes for working out line of sight
    protected Point2D.Double playerDirection;
    protected Point2D.Double playerLastPosition;
    protected Point2D.Double position;
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
        position = new Point2D.Double(x, y);
        playerLastPosition = new Point2D.Double();
        nextPoint = path.getClosetNode(position);
        currentPos = new Node(x,y,game);
    }


    protected boolean canSeePlayer() {
        double angleToPlayer = Math.atan2(playerLastPosition.x-x,playerLastPosition.y-y);
        double angleDegrees = Math.toDegrees(angleToPlayer);
        double distance = MathsMethods.length(playerDirection.x, playerDirection.y);
        if((angleDegrees<fieldOfView/2)&&(distance<500)&& isSightClear()) {
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
        line = new Line2D.Double(position.x, position.y, playerLastPosition.x, playerLastPosition.y);
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Wall) {
                if(line.intersects(object.getBounds())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * sets the last known position of the player
     * @throws Exception
     */
    protected void setLastPlayerPosition() throws Exception {
        Player player = this.getPlayer();
        playerLastPosition.setLocation(player.getX(), player.getY());
    }

    /**
     * follows
     */
    protected void followPath() {
        currentPos.getPoint().setLocation(x, y);
        if(path.hasReachedNext(currentPos)) {
            if(currentPos.getPoint().equals(generator.getGoalNode().getPoint())){
                //System.out.println("Goal : "+generator.getGoalNode().getPoint());
                path = generator.getPathList(currentPos.getPoint());
            }
            nextPoint = path.getNextNode();
        }
        else {
            moveToPoint(nextPoint.getPoint());
        }

    }

    private Player getPlayer() throws Exception{
        for(GameObject object : game.objectHandler.objects) {
            if(object.id == GameObjectID.Player) {
                return (Player)object;
            }
        }
        Exception e = new Exception("player not found");
        throw e;
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x-width/2, y-height/2, width, height);
    }
}
