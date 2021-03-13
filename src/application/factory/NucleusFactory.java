package application.factory;

import application.observerAndComposite.Nucleus;
import application.observerAndComposite.Particle;

public class NucleusFactory extends ParticleFactory {
	boolean moveable;
	
	/**
	 * This method creates particles (nuclei) that are either stationary or move along a path
	 * @param particleCreator A reference to the entity that will be creating the particles.
	 * @param moveable True - particles that move along a path. False - particles that are stationary.
	 */
	public NucleusFactory(ParticleCreator particleCreator, boolean moveable) {
		super(particleCreator);
		this.moveable = moveable;
	}

	/**
	 * Creates a nucleus
	 * @return a nucleus (implements particle component)
	 */
	@Override
	public Particle create() {
		return new Nucleus(pc.getX(), pc.getY(), this.moveable);
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}
	
}
