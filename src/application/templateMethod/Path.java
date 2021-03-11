package application.templateMethod;

import application.observerAndComposite.ParticleComponent;

public class Path extends Move {
	private double xInitSpeed;
	private double yInitSpeed;
	
	public Path(ParticleComponent particle, double xSpeed, double ySpeed) {
		super(particle, xSpeed, ySpeed);
		xInitSpeed = xSpeed;
		yInitSpeed = ySpeed;
	}

	void setAcceleration(ParticleComponent neighbor) {
		if (particle.getX() > 512-50)
			xAcc = -xInitSpeed*2;
		else if (particle.getX() < 50)
			xAcc = xInitSpeed*2;
		else if (Math.abs(xSpeed) < Math.abs(xInitSpeed))
			xAcc = xSpeed/10;
		else if (Math.abs(xSpeed) > Math.abs(xInitSpeed))
			xAcc = -xSpeed/10;
		else
			xAcc = 0;
		
		if (particle.getY() > 512-50 || particle.getY() < 50)
			yAcc = -ySpeed;
		else
			yAcc = 0;
	}
	
	

}
