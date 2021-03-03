package application.observer;

import java.util.ArrayList;

import application.Drawable;
import application.templateMethod.Move;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Particle implements Drawable, Subject {
	
	protected double x;
	protected double y;
	protected Move move;
	protected Image image = null; 
	private double charge = 1.6E-19;
	private double mass = 1.67E-27;
	protected boolean active = true;
	private boolean isCollision = false;
	
	public Particle(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void move (Particle neighbor, double elapsedTime) {
		this.move.move(neighbor, elapsedTime);
	}
	
	public double getX() {
		if (active && (x < 0 || x > 500)) {
			active = false;
			notifyObservers(this);
		}
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		if (active && (y < 0 || y > 500)) {
			active = false;
			notifyObservers(this);
		}
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public void registerObserver(Observer o) {
		//Default: Do Nothing
	}

	@Override
	public void removeObserver(Observer o) {
		//Default: Do Nothing
	}

	@Override
	public void notifyObservers(Particle particle) {
		//Default:Do Nothing
	}
	
	public abstract void draw(GraphicsContext gc);
		//gc.drawImage(image, x, y);

	public boolean isCollision() {
		return isCollision;
	}

	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
}
