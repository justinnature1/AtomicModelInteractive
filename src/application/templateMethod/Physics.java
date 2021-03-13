package application.templateMethod;

public class Physics {

	/**
	 * Determines the electrical force that exists between two charged objects
	 * @param Q1 The charge of the first object
	 * @param Q2 The charge of the second object
	 * @param distance The distance between the two objects
	 * @return double - The amount of electrical force that exists between two objects
	 */
	public static double electricalForce(double Q1, double Q2, double distance) {
		double k = 9E9;
		double force = (k * Math.abs(Q1) * Math.abs(Q2) / Math.pow(distance, 2));
		if ((Q1 >= 0 && Q2 < 0) || (Q2 >= 0 && Q1 < 0))
			return -force;
		else
			return force;
	}
	
	/**
	 * This determines the x component of a vector (a number that has a magnitude/size and direction/angle)
	 * @param value The total magnitude of the vector to split into an x portion
	 * @param radians The angle in radians of the vector
	 * @return double - The x portion of a vector
	 */
	public static double xComponent(double value, double radians) {
		return value * Math.cos(radians);
	}
	
	/**
	 * This determines the y component of a vector (a number that has a magnitude/size and direction/angle)
	 * @param value The total magnitude of the vector to split into an y portion
	 * @param radians The angle in radians of the vector
	 * @return double - The y portion of a vector
	 */
	public static double yComponent(double value, double radians) {
		return value * Math.sin(radians);
	}
	
	/**
	 * Determines the acceleration of an object given a force and mass.
	 * @param force The amount of force exerted on the object
	 * @param mass The amount of mass an object has
	 * @return double - The acceleration of an object
	 */
	public static double acceleration(double force, double mass) {
		return force / mass;
	}
	
	/**
	 * This method determine the angle between two points
	 * @param startX The x coordinate of the starting point
	 * @param startY The y coordinate of the starting point
	 * @param endX The x coordinate of the ending point
	 * @param endY The y coordinate of the ending point
	 * @return double - The angle between the two points
	 */
	public static double getAngle (double startX, double startY, double endX, double endY) {
		return Math.toDegrees(Math.atan2(startY - endY, startX - endX));
	}

	/**
	 * This method determines the distance between two points
	 * @param startX The x coordinate of the starting point
	 * @param startY The y coordinate of the starting point
	 * @param endX The x coordinate of the ending point
	 * @param endY The y coordinate of the ending point
	 * @return double - The distance between two points
	 */
	public static double distanceBetween (double startX, double startY, double endX, double endY) {
		return hypothenuseLen(startX - endX, startY - endY);
	}
	
	/**
	 * This method calculates the hypothenuse of a vector given two sides of a right triangle
	 * @param a First side of the triangle
	 * @param b Second side of the triangle
	 * @return double - The hypothenuse of the triangle
	 */
	public static double hypothenuseLen(double a, double b) {
		return Math.pow(Math.pow(a, 2) + Math.pow(b, 2),.5);
	}
	
}
