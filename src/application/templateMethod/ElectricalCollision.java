package application.templateMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

			//Correction for scale of game compared to atomic scale (distance/100)
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

			this.xAcc += roundDouble(
					Physics.acceleration(
							Physics.xComponent(
									force, 
									radians),
							particle.getMass())
					,2);

			this.yAcc += roundDouble(
					Physics.acceleration(
							Physics.yComponent(
									force, 
									radians),
							particle.getMass())
					,5);
		}
	}

	private double roundDouble(double number, int places) {
		BigDecimal bd = new BigDecimal(Double.toString(number));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}

