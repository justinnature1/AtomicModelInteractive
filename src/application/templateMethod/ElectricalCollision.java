package application.templateMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import application.observerAndComposite.ParticleComponent;

/**
 * The class that handles particle movement and collisions with other particles
 * @author Justin Keller
 *
 */
public class ElectricalCollision extends Move {

	/**
	 * Creates an instance of the Electrical Collision class
	 * @param particle A reference to the particle the instance handles the movement for
	 * @param xSpeed The initial xSpeed of the particle
	 * @param ySpeed The initial ySpeed of the particle
	 */
	public ElectricalCollision(ParticleComponent particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
	}

	/**
	 * The implementation of setAcceleration for the template method.  This method updates the acceleration based on 
	 * the electrical repulsion of this particle compared to a neighbor particle.
	 * @param neighbor A reference to the neighbor particle that will repel the particle
	 * @Override
	 */
	public void setAcceleration(ParticleComponent neighbor) {
		//If neighbor is null, calculations can't be performed
		if (neighbor != null) {
			//Determines the distance between the particle and its neighbor
			double distance = Physics.distanceBetween(particle.getX(), 
					particle.getY(), 
					neighbor.getX(), 
					neighbor.getY());

			//Determine the electrical force/repulsion caused by the neighbor particle
			double force = Physics.electricalForce(
					particle.getCharge(), 
					neighbor.getCharge(), 
					distance/100); //Correction for scale of game compared to atomic scale (distance/100)

			//Determine the angle of the force between the particle and its neighbor.
			double radians = Physics.getAngle(
					particle.getX(), 
					particle.getY(), 
					neighbor.getX(), 
					neighbor.getY()
					)*Math.PI/180;

			//Updates the x Acceleration based on the trigonometric calculation of the x Component of the force
			this.xAcc += roundDouble(
					Physics.acceleration(
							Physics.xComponent(
									force, 
									radians),
							particle.getMass())
					,2);

			//Sets the y Acceleration based on the trigonometric calculation of the y Component of the force
			this.yAcc += roundDouble(
					Physics.acceleration(
							Physics.yComponent(
									force, 
									radians),
							particle.getMass())
					,5);
		}
	}

	/**
	 * A method that rounds a double number up or down based on the number of places specified
	 * @param number The number to round
	 * @param places The number of places to round to
	 * @return Rounded double number.
	 */
	private double roundDouble(double number, int places) {
		BigDecimal bd = new BigDecimal(Double.toString(number));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}

