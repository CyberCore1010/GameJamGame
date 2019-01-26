package objects.misc;

import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class PathList extends CopyOnWriteArrayList<Point2D.Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int listPosition;
	private Point2D.Double nextPoint;
	
	public PathList(Point2D.Double nextPoint) {
		super();
		listPosition = 0;
		this.add(nextPoint);
		this.nextPoint = nextPoint;
	}
	
	
	public Point2D.Double getNextPoint() {
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
	
	public Point2D.Double getClosetPoint(Point2D.Double point) {
		Point2D.Double closest = new Point2D.Double();
		for(Point2D.Double p:this) {
			if(p.distance(point)<closest.distance(point)) {
				closest = p;
			}
		}
		return closest;
	}
	
	public boolean hasReachedNext(Point2D.Double point) {
		if(nextPoint.distance(point)<1.1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
