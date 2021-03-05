package application.templateMethod;

import java.util.Iterator;

import application.observer.ParticleComponent;

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
		Iterator<ParticleComponent> it = particle.getNeighbor().getIterator();
		if (it.hasNext()) {
			while (it.hasNext())
				setAcceleration (it.next());
		}else {
			setAcceleration(null);
		}
		changeSpeed(elapsedTime);
		changePosition(elapsedTime);
	}

	abstract void setAcceleration(ParticleComponent neighbor);

	protected void changeSpeed (double time) {
		xSpeed = xSpeed + xAcc * time;
		ySpeed = ySpeed + yAcc * time;
	}

	protected void changePosition (double time) {
		particle.setX(particle.getX() + xSpeed * time);
		particle.setY(particle.getY() + ySpeed * time);
	}
}
