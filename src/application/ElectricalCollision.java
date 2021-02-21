package application;

public class ElectricalCollision extends Move {

	public ElectricalCollision(Particle particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
	}

	@Override
	public void setAcceleration(Particle neighbor) {
		double distance = Physics.distanceBetween(particle.x, particle.y, neighbor.x, neighbor.y);
		double force = Physics.electricalForce(particle.charge, neighbor.charge, distance/100);
		double radians = Physics.getAngle(particle.x, particle.y, neighbor.x, neighbor.y)*Math.PI/180;
		this.xAcc = Physics.acceleration(Physics.xForce(force, radians),particle.mass);
		this.yAcc = Physics.acceleration(Physics.yForce(force, radians),particle.mass);
	}


	


	
}
