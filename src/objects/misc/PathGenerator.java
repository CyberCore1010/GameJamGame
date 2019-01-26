package objects.misc;

import game.Game;

import java.awt.geom.Point2D;

public class PathGenerator {
    private Point2D.Double unitPos;
    private Point2D.Double target;
    private Game game;

    public PathGenerator(Game game){
        this.game = game;
    }

    public void setTarget(Point2D.Double target){
        this.target = target;
    }

    public void setUnitPos(Point2D.Double unitPos){
        this.unitPos = unitPos;
    }

    public PathList getPathList(){
        
        return null;
    }


}
