package application.templateMethod;

import application.observerAndComposite.ParticleComponent;

/**
 * The class that handles the movement of particles that shouldn't move
 * @author Justin Keller
 */
public class Stationary extends Move {

	/**
	 * Creates an instance of the stationary class that handles particle movement so they remain stationary.
	 * @param particle The reference to the particle that this class handles the movement for
	 */
	public Stationary(ParticleComponent particle) {
		super(particle, 0, 0);
	}
	
	/**
	 * Sets the acceleration of the particles.  Acceleration will always be zero for the stationary class.
	 */
	protected void setAcceleration(ParticleComponent neighbor) {
		this.xAcc = 0;
		this.yAcc = 0;
	}
	
}
