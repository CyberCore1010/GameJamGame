package objects.misc;

import game.Game;
import objects.gameObjects.Node;

import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class PathList extends CopyOnWriteArrayList<Node> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int listPosition;
	private Node nextPoint;
	private Game game;
	
	public PathList(Node nextPoint, Game game) {
		super();
		this.game = game;
		listPosition = 0;
		this.add(nextPoint);
		this.nextPoint = nextPoint;
	}
	
	
	public Node getNextNode() {
		if((listPosition+1)>=this.size()) {
			listPosition = 0;
			nextPoint = this.get(listPosition);
			return nextPoint;
		}
		else {
			listPosition++;
			nextPoint = this.get(listPosition);
			return nextPoint;
		}
		
	}
	
	public Node getClosetNode(Node inputNode) {
		Node closest = new Node(1,1,game);
		for(Node node :this) {
			if(node.getPoint().distance(inputNode.getPoint())<closest.getPoint().distance(inputNode.getPoint())) {
				closest = node;
			}
		}
		return closest;
	}

	public Node getClosetNode(Point2D.Double point) {
		Node closest = new Node(1,1,game);
		for(Node node :this) {
			if(node.getPoint().distance(point)<closest.getPoint().distance(point)) {
				closest = node;
			}
		}
		return closest;
	}
	
	public boolean hasReachedNext(Node node) {
		if(nextPoint.getPoint().distance(node.getPoint())<1.1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
