package application.factory;

import application.observer.Particle;

public abstract class ParticleFactory {
	
	public ParticleCreator pc;
	
	public ParticleFactory (ParticleCreator particleCreator) {
		this.pc = particleCreator;
	}
	
	abstract Particle create();
	
}
