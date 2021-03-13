package application.factory;

import application.observerAndComposite.ParticleComponent;


public abstract class ParticleFactory {
	
	//Reference to the item that creates the particles
	public ParticleCreator pc;
	
	public ParticleFactory (ParticleCreator particleCreator) {
		this.pc = particleCreator;
	}
	
	abstract ParticleComponent create();
	
}
