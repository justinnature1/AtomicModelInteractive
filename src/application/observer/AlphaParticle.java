package application.observer;

import java.util.*;

import application.templateMethod.ElectricalCollision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AlphaParticle extends Particle implements Subject {
	List<Observer> observers;
	private static CollisionData collisionData = new CollisionData();

	public AlphaParticle(double x, double y, double xSpeed, double ySpeed) {
		super(x, y);
		this.move = new ElectricalCollision(this, xSpeed, ySpeed);
		this.image = new Image ("file:alpha.png");
		this.setCharge(this.getCharge() * 2);
		this.setMass(this.getMass() * 4);
		observers = Collections.synchronizedList(new ArrayList<Observer>());
	}


	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.add(o);
	}

	@Override
	
	public void notifyObservers(Particle particle) {
		synchronized(observers) {
			for (Observer o : observers) {
				o.update(this);
			}
		}
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(getX(), getY(), 5, 5);
		gc.fillOval(getX(), getY(), 5, 5);
	}
}
