package application;

public abstract class Move {
	protected Particle particle;
	protected double xSpeed;
	protected double ySpeed;
	protected double yAcc = 0;
	protected double xAcc = 0;


	public Move (Particle particle, double xSpeed, double ySpeed) {
		this.particle = particle;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void move (Particle neighbor, double elapsedTime) {
		xAcc = yAcc = 0;
		setAcceleration (neighbor);
		changeSpeed(elapsedTime);
		changePosition(elapsedTime);
	}
	
	abstract void setAcceleration(Particle neighbor);
	
	protected void changeSpeed (double time) {
		xSpeed = xSpeed + xAcc * time;
		ySpeed = ySpeed + yAcc * time;
	}
	
	protected void changePosition (double time) {
		particle.x += xSpeed * time;
		particle.y += ySpeed * time;
	}
}
