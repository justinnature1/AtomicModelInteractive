package application.observerAndComposite;

import java.util.*;
import application.templateMethod.ElectricalCollision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class handles specific items related to alpha particles.  These are the particles that are shot
 * around the screen and bounce off of other particles called neighbors.  Implements the subject interface
 * so it can notify observers when the particle leaves the screen.
 * @author Justin Keller
 */
public class AlphaParticle extends Particle implements Subject {
	protected static int particleCount = 1;//Static variables that count how many particles have been created so far.
	protected int rotationAngle = 0;//Tracks the rotation the particle needs to be drawn at.
	List<Observer> observers;//Tracks observers of the particle.

	/**
	 * Creates an alpha particle
	 * @param x The x coordinate of the particle
	 * @param y The y coordinate of the particle
	 * @param xSpeed The xSpeed of the particle at creation
	 * @param ySpeed The ySpeed of the particle at creation
	 * @param neighbors A reference to the neighbors of the particle that it can collide with
	 */
	public AlphaParticle(double x, double y, double xSpeed, double ySpeed, ParticleComponent neighbors) {
		super(x, y);
		this.particleNumber = particleCount++;
		this.move = new ElectricalCollision(this, xSpeed, ySpeed);
		this.setCharge(this.getCharge() * 2);
		this.setMass(this.getMass() * 4);
		this.neighbors = neighbors;
		observers = new ArrayList<Observer>();
	}


	/**
	 * Registers an observer of this particle
	 * @param o Reference to the observer of this particle
	 * @Override
	 */
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Removes an observer of this particle
	 * @param Reference to the observer of this particle
	 * @Override
	 */
	public void removeObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Notifies all observers of this particle
	 * @Override
	 */	
	public void notifyObservers() {
		synchronized(observers) {
			for (Observer o : observers) {
				o.update(this);
			}
		}
	}

	/**
	 * Used to reset the particle number at 1 again.
	 */
	public static void resetParticleNumber() {
		particleCount = 1;
	}
	
	/**
	 * Draws the alpha particle on the graphics context only if the particle is set to active
	 * @param gc Reference to the graphic context to draw on
	 * @Override
	 */
	public void draw(GraphicsContext gc) {
		//Only draws the particle if it is active.
		if (active) {
			int radius = 6;
			//Determines the center of the particle
			int centerX = (int)getX()-radius/2;
			int centerY = (int)getY()-radius/2;

			for (int i = 0; i < 4; i++) {
				//Alternates color between red and blue depending on an odd or even i value
				if(i % 2 == 1)
					gc.setFill(Color.RED);
				else
					gc.setFill(Color.BLUE);
				//Draws a particle at one of four positions rotated around the center of the particle.
				int drawAngle = (rotationAngle + i * 90) % 360;
				gc.strokeOval(centerX + 2*Math.cos(drawAngle), centerY + 2*Math.sin(drawAngle), radius, radius);
				gc.fillOval(centerX + 2*Math.cos(drawAngle), centerY + 2*Math.sin(drawAngle), radius, radius);
			}
			//Advances the angle of the particle so the next frame is drawn slightly rotated.
			this.rotationAngle = (this.rotationAngle + 45) % 360;
		}
	}
}
