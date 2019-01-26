package physics;

import static java.lang.Math.*;

import java.awt.geom.Point2D;
/*
 * a class for general Physics and Math functions
 */
public class MathsMethods {
	
	// Newton's Gravitational Constant
	private static final double G = 6.67 * pow(10,-11);
	
	/**
	 * Get the gravitational force between two objects
	 * F = (G * m1 * m2) / (r * r), with r being the distance between the two objects
	 * @param M The first mass
	 * @param m The second mass
	 * @param r The distance between the centre point of the two masses 
	 * @return The gravitational force between the objects
	 */
	public static double force(double M, double m, double r){
		return (G * M * m) / pow(r, 2);
	}

	/*
	 * Get the distance between two points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return The distance between the two points
	 */
	public static double distance(double x1, double y1, double x2, double y2){
		double dx = x2 - x1;
		double dy = y2 - y1;
		return sqrt((dx * dx) + (dy * dy));
	}
	/*
	 * gets the length of a line (x and y)
	 * @param x
	 * @param y
	 * @returns length
	 */
	public static double length(double x, double y){
		double length = sqrt((x*x) +(y*y));
		return length;
		
	}
	/*
	 *gets the dot product of the two 2d vectors inputed
	 *@param vector1
	 *@param vector2
	 *@returns product
	 */
	public static double dotProduct2D(double[] vector1,double[] vector2){
		double product = (vector1[0]*vector2[0])+(vector1[1]*vector2[1]);
		return product;
	}
	
	/*
	 * adds the two inputed vectors together f
	 */
	public static double[] vectorAdd(double[] vector1, double[] vector2){
		double[] added ={(vector1[0]+vector2[0]),(vector1[1]+vector2[1])};
		return added;
	}
	
	/*
	 *Subtracts the two inputed vectors 
	 */
	public static double[] vectorSubtract(double[] vector1, double[] vector2){
		double[] subracted = vectorAdd(vector1,vectorScale(vector2,-1));
		return subracted;
	}
	
	/*
	 * multiplies the inputed vector by the scaler quantity
	 */
	public static double[] vectorScale(double[] vector1, double scalar){
		double[] scaled ={(vector1[0]*scalar),(vector1[1]*scalar)};
		return scaled;
	}
	
	public static boolean linesIntersect(double x1,double y1,double x2,double y2,double x3,double y3, double x4,double y4){
		double uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
		double uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
		
		if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public static double[] getUnitVector(double x1,double y1,double x2,double y2){
		double xDist = x2-x1;
		double yDist = y2-y1;
		double length = MathsMethods.length(xDist, yDist);
		double[] unitVector = {xDist/length,yDist/length};
		return unitVector;
	}
	
	public static double getVectorAngle(Point2D.Double vector1,Point2D.Double vector2) {
		double dotProduct = (vector1.getX()*vector2.getX())+(vector1.getY()*vector2.getY());
		double vector1Len =	Math.sqrt(Math.pow(vector1.getX(), 2)+Math.pow(vector1.getY(), 2));
		double vector2Len = Math.sqrt(Math.pow(vector2.getX(), 2)+Math.pow(vector2.getY(), 2));
		double part1 = (dotProduct/(vector1Len*vector2Len));
		double result = Math.acos(part1);
		return result;
	}
	
	/*
	 * Gets the angle between the two points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return The angle between the two points
	 */
	public static double angle(double x1, double y1, double x2, double y2){
		double dx = abs(x2 - x1);
		double dy = abs(y2 - y1);
		return Math.atan(dy / dx);
		
	}
	/*
	 * finds the angle between two lines
	 */
	public static double angle(double gradient1,double gradient2){
		double angle = Math.atan((gradient2-gradient1)/(1+(gradient2*gradient1)));
		return angle;
	}
	
	/*
	 * Gives you the mass of a spherical object, given its density and radius
	 * @param density
	 * @param radius
	 * @return The mass of the object
	 */
	public static double mass(double density, double radius){
		double volume = (4 / 3) * Math.PI * pow(radius, 3);
		return density * volume;
	}
	
	/*
	 * Checks if two circles are intersecting, or INSIDE one another
	 * Returns true if they are intersecting
	 * @param x1
	 * @param y1
	 * @param r1
	 * @param x2
	 * @param y2
	 * @param r2
	 * @return Whether or not the two circles would be intersecting
	 */
	public static boolean intersectCircle(double x1, double y1, double r1, double x2, double y2, double r2){
		return distance(x1, y1, x2, y2) < (r1 + r2);
	}
	
	/*
	 * Gets the midpoint of two coordinates
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return The coordinate of the midpoint
	 */
	public static double[] midpoint(double x1, double y1, double x2, double y2){
		double midx = (x2 + x1) / 2;
		double midy = (y2 + y1) / 2;
		double[] midpoint = {midx, midy};
		return midpoint;
	}
	
	/*
	 * gets the area of the circle
	 * @param radius
	 * @return area
	 */
	public static double circleArea(double radius){
		double area = Math.PI*pow(radius,2);
		return area;
	}
	
	public static double clamp(double value,double lowerLim,double upperLim){
		if(value>upperLim){
			return upperLim;
		}
		else if(value<lowerLim){
			return lowerLim;
		}
		else{
			return value;
		}
	}
}


