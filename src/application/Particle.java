package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Particle {

	
	protected double x;
	protected double y;
	protected double xSpeed;
	protected double ySpeed;
	protected Image image = null; 
	protected double yAcc = 0;
	protected double xAcc = 0;
	
	public Particle(double x, double y, double xSpeed, double ySpeed){
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public int getX() {
		return (int)x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public int getY() {
		return (int) y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}

	public void update(double elapsedTime, ShotParticle a) {
		this.xAcc = this.yAcc = 0;
		this.setXAccelerate(a);
		this.setYAccelerate(a);
		update(elapsedTime);
		this.xAcc = this.yAcc = 0;
	}

	public void update(double elapsedTime) {
		this.xSpeed = this.xSpeed + this.xAcc * elapsedTime;
		this.ySpeed = this.ySpeed + this.yAcc * elapsedTime;
		x += xSpeed * elapsedTime;
		y += ySpeed * elapsedTime;
	}

	public double calcForce(double distance, boolean repulsive) {
		double force = (10000 / Math.pow(distance, 2));
		if (repulsive)
			return -force;
		else
			return force;
	}

	public void setXAccelerate(ShotParticle a) {
		this.xAcc = calcForce(getDistance(a),true) * Math.cos(getAngle(a)*Math.PI/180);
	}

	public void setYAccelerate(ShotParticle a) {
		this.yAcc = calcForce(getDistance(a),true) * Math.sin(getAngle(a)*Math.PI/180);
	}

	public double getAngle (ShotParticle a) {
		return Math.toDegrees(Math.atan2(a.y - this.y, a.x - this.x));
	}

	public double getDistance (ShotParticle a) {
		return Math.pow(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2),.5);
	}
	//protected Move move;
	
}
