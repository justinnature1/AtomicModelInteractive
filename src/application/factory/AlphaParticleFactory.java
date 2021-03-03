package application.factory;

import application.observer.AlphaParticle;
import application.observer.CollisionData;

public class AlphaParticleFactory extends ParticleFactory{
	CollisionData collisionData;
	
	public AlphaParticleFactory(ParticleCreator particleCreator, CollisionData collisionData) {
		super(particleCreator);
		this.collisionData = collisionData;
	}
	
	@Override
	public AlphaParticle create() {
		AlphaParticle newAP = new AlphaParticle(pc.getX(), pc.getY(), pc.getXSpeed(), pc.getYSpeed());
		newAP.registerObserver(collisionData);
		return newAP;
	}
		 
	
}
