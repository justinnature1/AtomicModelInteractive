package application.factory;

import application.observerAndComposite.AlphaParticle;
import application.observerAndComposite.ParticleComponent;

public class AlphaParticleFactory extends ParticleFactory{
	ParticleComponent neighbors;
	
	/**
	 * This class handles the creation of Alpha Particles that will collide with other particles.
	 * @param particleCreator A reference to the entity that will create particles
	 * @param neighbors A reference to the ParticleComponent(s) that the created particles could collide with.
	 */
	public AlphaParticleFactory(ParticleCreator particleCreator, ParticleComponent neighbors) 
	{
		super(particleCreator);
		this.neighbors = neighbors;
	}
	
	
	/**
	 * This method will allow your particle creator to create an alphaParticle
	 * @return an alpha particle (implements particle component)
	 */
	@Override
	public AlphaParticle create() {
		return new AlphaParticle(pc.getX(), pc.getY(), pc.getXSpeed(), pc.getYSpeed(), this.neighbors);
	}
		 
	
}
