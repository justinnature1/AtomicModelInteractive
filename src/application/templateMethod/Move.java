package application.templateMethod;

import java.util.Iterator;

import application.observerAndComposite.ParticleComponent;

public abstract class Move {
	protected ParticleComponent particle;
	protected double xSpeed;
	protected double ySpeed;
	protected double yAcc = 0;
	protected double xAcc = 0;


	public Move (ParticleComponent particle, double xSpeed, double ySpeed) {
		this.particle = particle;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public void move (double elapsedTime) {
		xAcc = yAcc = 0;
		setAcceleration();
		changeSpeed(elapsedTime);
		changePosition(elapsedTime);
	}

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
	
	private void setCollision() {
		double angle = Physics.getAngle(particle.getX(), 
				particle.getY(), 
				particle.getX() + this.xSpeed, 
				particle.getY() + this.ySpeed);
		double angleAdjustment = Physics.yComponent(150, Math.toRadians(angle));
		double accThreshold = 50 + Math.abs(angleAdjustment);
		double totAcc = Physics.hypothenuseLen(
				this.xAcc, 
				this.yAcc);
		if (totAcc>accThreshold && particle.isCollision() == false) {
			particle.setCollision(true);
		}
		
		
	}
	
	abstract void setAcceleration(ParticleComponent neighbor);

	public String getMoveName () {
		return this.getClass().getSimpleName();
	}
	
	protected void changeSpeed (double time) {
		xSpeed = xSpeed + xAcc * time;
		ySpeed = ySpeed + yAcc * time;
	}

	protected void changePosition (double time) {
		particle.setX(particle.getX() + xSpeed * time);
		particle.setY(particle.getY() + ySpeed * time);
	}
}
