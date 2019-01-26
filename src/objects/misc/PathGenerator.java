package objects.misc;

import game.Game;
import objects.gameObjects.Node;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

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

    public PathList getPathList(Point2D.Double start,Point2D.Double goal){
        boolean goalFound = false;

        ArrayList<Node> closedList = new ArrayList<>();
        Node startNode = game.grid.getNearestNode(start);
        Node goalNode = game.grid.getNearestNode(goal);
        PathList returnList = new PathList(startNode,game);
        Node currentNode = startNode;

        while (!goalFound){
            double shortestDistance = Double.MAX_VALUE;
            Node selectedNode = currentNode;
            for(Node child : currentNode.children){
                if(!closedList.contains(child)){
                    double distance = child.getPoint().distance(goalNode.getPoint());
                    if(goalNode.getPoint() == child.getPoint()) {
                        selectedNode = child;
                        goalFound = true;
                    } else if(distance < shortestDistance){
                        selectedNode = child;
                        shortestDistance = distance;
                    }
                }
            }
            closedList.add(currentNode);
            currentNode = selectedNode;
            returnList.add(selectedNode);
        }
        return returnList;
    }
    private ArrayList<Node> depthFirst(Node current,Node goal){
        if(current.getPoint().equals(goal.getPoint())){
            ArrayList<Node> list = new ArrayList<>();
            list.add(current);
            return list;
        }
        else{
            for(Node child : current.children){
                ArrayList childList = depthFirst(child,goal);
                if(childList != null){
                    childList.add(0,current);
                    return childList;
                }
            }
            return null;
        }
    }
}
