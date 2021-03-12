package application.observerAndComposite;

import java.util.*;
import application.templateMethod.ElectricalCollision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlphaParticle extends Particle implements Subject {
	protected static int particleCount = 1;
	protected int rotationAngle = 0;
	List<Observer> observers;

	public AlphaParticle(double x, double y, double xSpeed, double ySpeed, ParticleComponent neighbors) {
		super(x, y);
		this.particleNumber = particleCount++;
		this.move = new ElectricalCollision(this, xSpeed, ySpeed);
		this.setCharge(this.getCharge() * 2);
		this.setMass(this.getMass() * 4);
		this.neighbors = neighbors;
		observers = new ArrayList<Observer>();
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

	public void notifyObservers() {
		synchronized(observers) {
			for (Observer o : observers) {
				o.update(this);
			}
		}
	}

	public static void resetParticleNumber() {
		particleCount = 1;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if (active) {
			int radius = 6;
			int centerX = (int)getX()-radius/2;
			int centerY = (int)getY()-radius/2;

			for (int i = 0; i < 4; i++) {
				if(i % 2 == 1)
					gc.setFill(Color.RED);
				else
					gc.setFill(Color.BLUE);
				int drawAngle = (rotationAngle + i * 90) % 360;
				gc.strokeOval(centerX + 2*Math.cos(drawAngle), centerY + 2*Math.sin(drawAngle), radius, radius);
				gc.fillOval(centerX + 2*Math.cos(drawAngle), centerY + 2*Math.sin(drawAngle), radius, radius);
			}
			this.rotationAngle +=45;
		}
	}
}
