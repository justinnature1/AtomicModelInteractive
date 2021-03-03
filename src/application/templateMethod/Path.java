package application.templateMethod;

import application.observer.Particle;

public class Path extends Move {
	private double xOrigin;
	private double yOrigin;
	
	public Path(Particle particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
		xOrigin = particle.getX();
		yOrigin = particle.getY();
	}

	void setAcceleration(Particle neighbor) {
		if (particle.getX() - xOrigin > 25)
			xAcc = -5;
		else if (particle.getX() - xOrigin < -25)
			xAcc = 5;
		else
			xAcc = 0;
		
		if (particle.getY() - yOrigin > 25 || particle.getY() - yOrigin < 25)
			yAcc = -ySpeed;
		else
			yAcc = 0;
	}
	
	

}
