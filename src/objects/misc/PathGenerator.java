package objects.misc;

import game.Game;
import objects.gameObjects.Node;

import java.awt.geom.Point2D;
import java.util.*;

public class PathGenerator {
    private Point2D.Double unitPos;
    private Point2D.Double target;
    private Game game;

    public Node goalNode;

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
        Node startNode = game.grid.getNearestNode(start);
        goalNode = game.grid.getNearestNode(goal);
        PathList returnList = new PathList(startNode,game);
        Node currentNode = startNode;
        ArrayList<Node> aStarResult = aStar(startNode,goalNode);
        Collections.reverse(aStarResult);
        returnList.addAll(aStarResult);
        return returnList;
    }

    private ArrayList<Node> reconstructPath(Map<Node,Node> cameFrom, Node current){
        ArrayList<Node> totalPath = new ArrayList<>();
        totalPath.add(current);
        while(cameFrom.keySet().contains(current)){
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        return totalPath;
    }

    private ArrayList<Node> aStar(Node startNode,Node goalNode){
        ArrayList<Node> closedList = new ArrayList<>();

        ArrayList<Node> openList = new ArrayList<>();
        openList.add(startNode);

        Map<Node,Node> cameFrom = new HashMap<>();
        Map<Node,Double> gscoreMap = new HashMap<>();
        Map<Node,Double> fscoreMap = new HashMap<>();
        gscoreMap.put(startNode,0.0);
        fscoreMap.put(startNode,startNode.getPoint().distance(goalNode.getPoint()));
        while (!openList.isEmpty()){
            Node smallest = null;
            double smallestNum = Double.MAX_VALUE;
            for(Node temp : openList){
                if(fscoreMap.getOrDefault(temp,Double.MAX_VALUE)< smallestNum){
                    smallest = temp;
                    smallestNum = fscoreMap.getOrDefault(temp,Double.MAX_VALUE);
                }
            }
            if(smallest.getPoint().equals(goalNode.getPoint())){
                return reconstructPath(cameFrom,smallest);
            }
            openList.remove(smallest);
            closedList.add(smallest);

            for(Node child : smallest.children){
                if(closedList.contains(child)){
                    continue;
                }
                double tempGScore = gscoreMap.get(smallest) + smallest.getPoint().distance(child.getPoint());
                if(!openList.contains(child)){
                    openList.add(child);
                }
                else if(tempGScore >= gscoreMap.getOrDefault(child,Double.MAX_VALUE)){
                    continue;
                }

                cameFrom.put(child,smallest);
                gscoreMap.put(child,tempGScore);
                fscoreMap.put(child,(gscoreMap.get(child)+child.getPoint().distance(goalNode.getPoint())));
            }
        }
        return null;


    }
}
