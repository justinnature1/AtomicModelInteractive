package application.factory;

import application.observerAndComposite.AlphaParticle;
import application.observerAndComposite.CollisionData;
import application.observerAndComposite.ParticleComponent;

public class AlphaParticleFactory extends ParticleFactory{
	CollisionData collisionData;
	ParticleComponent neighbors;
	
	public AlphaParticleFactory(
			ParticleCreator particleCreator, 
			ParticleComponent neighbors) 
	{
		super(particleCreator);
		this.neighbors = neighbors;
	}
	
	@Override
	public ParticleComponent create() {
		ParticleComponent newAP = new AlphaParticle(pc.getX(), pc.getY(), pc.getXSpeed(), pc.getYSpeed(), this.neighbors);
		return newAP;
	}
		 
	
}
