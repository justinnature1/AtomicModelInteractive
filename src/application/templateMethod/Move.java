package application.templateMethod;

import java.util.Iterator;

import application.observerAndComposite.ParticleComponent;

public abstract class Move {
	protected ParticleComponent particle; //Reference to the particle the instance manages movement for
	protected double xSpeed; //The current xSpeed of a particle
	protected double ySpeed; //The current ySpeed of a particle
	protected double yAcc = 0; //The current acceleration in the y direction. Should be set to zero every frame.
	protected double xAcc = 0; //The current acceleration in the x direction. Should be set to zero every frame.

	/**
	 * Creates a movement instance that manages the movement of a particle
	 * @param particle The reference to the particle that this instance manages movement for
	 * @param xSpeed The initial xSpeed of the particle
	 * @param ySpeed The initial ySpeed of the particle
	 */
	public Move (ParticleComponent particle, double xSpeed, double ySpeed) {
		this.particle = particle;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	/**
	 * The method that handles the movement of the particle
	 * @param elapsedTime The amound of time that has passed since the last movement.
	 */
	public void move (double elapsedTime) {
		xAcc = yAcc = 0;
		setAcceleration();
		changeSpeed(elapsedTime);
		changePosition(elapsedTime);
	}

	/**
	 * Manages the calls to the setAcceleration method if a collection of neighbors exists.
	 */
	private void setAcceleration() {
		Iterator<ParticleComponent> it = particle.getNeighbor().getIterator();
		if (it.hasNext()) {
			while (it.hasNext()) {
				setAcceleration(it.next());
			}
			setCollision();
		}else {
			setAcceleration(null);
		}
	}
	
	/**
	 * A method that is called to determine if a particle has collided with another particle
	 */
	private void setCollision() {
		//Determines the angle the particle was shot at.  More extreme angles produce lower accelerations than direct shots
		double angle = Physics.getAngle(particle.getX(), 
				particle.getY(), 
				particle.getX() + this.xSpeed, 
				particle.getY() + this.ySpeed);

		//Adjusts the threshold for acceleration based on the shooting angle. 
		//Threshold for direct shots (200)
		//Threshold for an indirect shot (50)
		double angleAdjustment = Physics.yComponent(150, Math.toRadians(angle));
		double accThreshold = 50 + Math.abs(angleAdjustment);
		
		//Calculates the total acceleration based on both the xAcc and yAcc.
		double totAcc = Physics.hypothenuseLen(
				this.xAcc, 
				this.yAcc);

		//If the total acceleration exceeds the threshold, the particle has collided.
		if (totAcc>accThreshold && particle.isCollision() == false) {
			particle.setCollision(true);
		}
		
		
	}
	
	//Abstract method that all subclasses must implement
	abstract void setAcceleration(ParticleComponent neighbor);

	/**
	 * 
	 * @return The simple name of the class.
	 */
	public String getMoveName () {
		return this.getClass().getSimpleName();
	}
	
	/**
	 * Updates the x and y speed based on the time that has passed
	 * @param time The amount of time that has passed
	 */
	protected void changeSpeed (double time) {
		xSpeed = xSpeed + xAcc * time;
		ySpeed = ySpeed + yAcc * time;
	}

	/**
	 * Updates the x and y position of the particle based on the time that has elapsed and the speed.
	 * @param time The amount of time that has passed
	 */
	protected void changePosition (double time) {
		particle.setX(particle.getX() + xSpeed * time);
		particle.setY(particle.getY() + ySpeed * time);
	}
}
