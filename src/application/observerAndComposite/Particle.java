package application.observerAndComposite;

import application.templateMethod.Move;
import javafx.scene.canvas.GraphicsContext;

public abstract class Particle extends ParticleComponent implements Subject {
	protected int particleNumber;
	protected double x;
	protected double y;
	protected Move move; //Reference to the class that controls how the particle moves
	private double charge = 1.6E-19; //Charge of 1 proton
	private double mass = 1.67E-27; //Mass of 1 proton/neutron
	protected boolean active = true; //If active, the particle will be drawn and move.
	protected boolean isCollision = false; //Tracks whether a particle collides or not.  All particles default to false
	protected ParticleComponent neighbors = new NoParticles(); //Neighbors default to NoParticles to avoid null references
	
	/**
	 * Sets the location of a new particle
	 * @param x The x coordinate of the particle
	 * @param y The y coordinate of the particle
	 */
	public Particle(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public int getParticleNumber () {
		return this.particleNumber;
	}
	
	public ParticleComponent getNeighbor() {
		return this.neighbors;
	}
	
	public void setNeighbor(ParticleComponent neighbors) {
		this.neighbors = neighbors;
	}
	
	/**
	 * Moves the particle only if the particle is active.
	 * @param elapsedTime The amount of time that has passed between moves
	 */
	public void move (double elapsedTime) {
		if (active)
			this.move.move(elapsedTime);
	}
	
	public double getX() {
		return x;
	}
	
	/**
	 * Sets the x coordinate.  Also determines if sets active to false and notifies observers if the particle goes off the screen 
	 * @param x The x coordinate
	 */
	public void setX(double x) {
		if (active && (x < 0 || x > 512)) {
			active = false;
			notifyObservers();
		}
		this.x = x;
	}

	public double getY() {
		return y;
	}
	
	/**
	 * Sets the y coordinate.  Also determines if sets active to false and notifies observers if the particle goes off the screen 
	 * @param y The y coordinate.
	 */
	public void setY(double y) {
		if (active && (y < 0 || y > 512)) {
			active = false;
			notifyObservers();
		}
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
	public void notifyObservers() {
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
	
	public boolean isActive() {
		return this.active;
	}
}
