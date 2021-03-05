package application.factory;

import application.observer.Particle;
import application.observer.ParticleComponent;

public abstract class ParticleFactory {
	
	public ParticleCreator pc;
	
	public ParticleFactory (ParticleCreator particleCreator) {
		this.pc = particleCreator;
	}
	
	abstract ParticleComponent create();
	
}
