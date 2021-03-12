package application.observerAndComposite;

import application.templateMethod.Move;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Particle extends ParticleComponent implements Subject {
	protected int particleNumber;
	protected double x;
	protected double y;
	protected Move move;
	protected Image image = null; 
	private double charge = 1.6E-19;
	private double mass = 1.67E-27;
	protected boolean active = true;
	protected boolean isCollision = false;
	protected ParticleComponent neighbors = new NoParticles();
	
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
	
	public void move (double elapsedTime) {
		if (active)
			this.move.move(elapsedTime);
	}
	
	public double getX() {
		return x;
	}
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
