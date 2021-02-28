package application;

public class AlphaParticleFactory extends ParticleFactory{


	public AlphaParticleFactory(ParticleCreator particleCreator) {
		super(particleCreator);
	}
	
	@Override
	public AlphaParticle create() {
		return new AlphaParticle(pc.getX(), pc.getY(), pc.getXSpeed(), pc.getYSpeed());
	}
		 
	
}
