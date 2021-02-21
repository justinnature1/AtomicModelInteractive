package application;

public class Path extends Move {
	private double xOrigin;
	private double yOrigin;
	
	public Path(Particle particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
		xOrigin = particle.x;
		yOrigin = particle.y;
	}

	void setAcceleration(Particle neighbor) {
		if (particle.x - xOrigin > 25)
			xAcc = -5;
		else if (particle.x - xOrigin < -25)
			xAcc = 5;
		else
			xAcc = 0;
		
		if (particle.y - yOrigin > 25 || particle.y - yOrigin < 25)
			yAcc = -ySpeed;
		else
			yAcc = 0;
	}
	
	

}
