package application.factory;

import application.observer.AlphaParticle;
import application.observer.CollisionData;
import application.observer.ParticleComponent;

public class AlphaParticleFactory extends ParticleFactory{
	CollisionData collisionData;
	ParticleComponent neighbors;
	
	public AlphaParticleFactory(
			ParticleCreator particleCreator, 
			CollisionData collisionData, 
			ParticleComponent neighbors) 
	{
		super(particleCreator);
		this.collisionData = collisionData;
		this.neighbors = neighbors;
	}
	
	@Override
	public ParticleComponent create() {
		ParticleComponent newAP = new AlphaParticle(pc.getX(), pc.getY(), pc.getXSpeed(), pc.getYSpeed(), this.neighbors);
		newAP.registerObserver(collisionData);
		return newAP;
	}
		 
	
}
