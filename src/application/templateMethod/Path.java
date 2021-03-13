package application.templateMethod;

import application.observerAndComposite.ParticleComponent;

/**
 * A class that creates a particle that moves along a predetermined path from the left to right side of the screen
 * @author Justin Keller
 */
public class Path extends Move {
	private final double xInitSpeed; //Stores the initial speed of the particle
	private final double yInitSpeed; //Stores the initial speed of the particle
	
	/**
	 * Creates an instance of the Path class that manages movement of a particle
	 * @param particle A reference to the particle this class manages movement for
	 * @param xSpeed The initial xSpeed of the particle
	 * @param ySpeed The initial ySpeed of the particle
	 */
	public Path(ParticleComponent particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
		xInitSpeed = xSpeed;
		yInitSpeed = ySpeed;
	}

	/**
	 * Sets the acceleration of the particle once it reaches the sides of the screen.  Switches direction of the particle
	 * @param neighbor An unnecessary parameter for this class.  Set to null.
	 */
	void setAcceleration(ParticleComponent neighbor) {
		//If the particle approaches the edge of the screen, the acceleration is reverses the movement of the particle
		if (particle.getX() > 512-50)
			xAcc = -xInitSpeed*2;
		else if (particle.getX() < 50)
			xAcc = xInitSpeed*2;
		//Due to rounding issues, the particles xSpeed could creep. The following conditions correct for this creeping
		else if (Math.abs(xSpeed) < Math.abs(xInitSpeed))
			xAcc = xSpeed/10;
		else if (Math.abs(xSpeed) > Math.abs(xInitSpeed))
			xAcc = -xSpeed/10;
		else
			xAcc = 0; //If the xSpeed matches the initial speed, the acceleration is zero.
		
		if (particle.getY() > 512-50 || particle.getY() < 50)
			yAcc = -ySpeed;
		else
			yAcc = 0;
	}
}
