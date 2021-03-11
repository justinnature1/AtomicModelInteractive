package application.templateMethod;

import application.observerAndComposite.ParticleComponent;

public class Stationary extends Move {

	public Stationary(ParticleComponent particle) {
		super(particle, 0, 0);
	}
	
	protected void setAcceleration(ParticleComponent neighbor) {
		this.xAcc = 0;
		this.yAcc = 0;
	}
	
}
