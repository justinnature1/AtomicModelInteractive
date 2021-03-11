package application.templateMethod;

import application.observerAndComposite.ParticleComponent;

public class ElectricalCollision extends Move {

	public ElectricalCollision(ParticleComponent particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
	}

	@Override
	public void setAcceleration(ParticleComponent neighbor) {
		if (neighbor != null) {
			double distance = Physics.distanceBetween(particle.getX(), 
					particle.getY(), 
					neighbor.getX(), 
					neighbor.getY());

			double force = Physics.electricalForce(
					particle.getCharge(), 
					neighbor.getCharge(), 
					distance/100);

			double radians = Physics.getAngle(
					particle.getX(), 
					particle.getY(), 
					neighbor.getX(), 
					neighbor.getY()
					)*Math.PI/180;

			this.xAcc += Physics.acceleration(
					Physics.xComponent(
							force, 
							radians),
					particle.getMass());

			this.yAcc += Physics.acceleration(
					Physics.yComponent(
							force, 
							radians),
					particle.getMass());
		}
	}


}

