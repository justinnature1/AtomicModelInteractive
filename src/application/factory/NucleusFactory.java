package application.factory;

import application.observerAndComposite.Nucleus;
import application.observerAndComposite.Particle;

public class NucleusFactory extends ParticleFactory {

	boolean moveable;
	
	public NucleusFactory(ParticleCreator particleCreator, boolean moveable) {
		super(particleCreator);
		this.moveable = moveable;
	}

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
