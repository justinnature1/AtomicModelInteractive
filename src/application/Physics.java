package application;

public class Physics {

	public static double electricalForce(double k, double Q1, double Q2, double distance) {
		double force = (k * Math.abs(Q1) * Math.abs(Q2) / Math.pow(distance, 2));
		if ((Q1 >= 0 && Q2 < 0) || (Q2 >= 0 && Q1 < 0))
			return -force;
		else
			return force;
	}
	
	public static double xForce(double force, double radians) {
		return force * Math.cos(radians);
	}
	
	public static double yForce(double force, double radians) {
		return force * Math.sin(radians);
	}
	
	public static double acceleration(double force, double mass) {
		return force / mass;
	}
	
	public static double getAngle (double startX, double startY, double endX, double endY) {
		return Math.toDegrees(Math.atan2(startY - endY, startX - endX));
	}

	public static double distanceBetween (double startX, double startY, double endX, double endY) {
		return hypothenuseLen(startX - endX, startY - endY);
	}
	
	public static double hypothenuseLen(double a, double b) {
		return Math.pow(Math.pow(a, 2) + Math.pow(b, 2),.5);
	}
}
