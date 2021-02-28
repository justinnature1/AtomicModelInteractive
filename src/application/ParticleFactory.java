package application;

public abstract class ParticleFactory {
	
	public ParticleCreator pc;
	
	public ParticleFactory (ParticleCreator particleCreator) {
		this.pc = particleCreator;
	}
	
	abstract Particle create();
	
}
