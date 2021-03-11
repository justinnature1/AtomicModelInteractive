package application.factory;

import application.observerAndComposite.ParticleComponent;

public abstract class ParticleFactory {
	
	public ParticleCreator pc;
	
	public ParticleFactory (ParticleCreator particleCreator) {
		this.pc = particleCreator;
	}
	
	abstract ParticleComponent create();
	
}
