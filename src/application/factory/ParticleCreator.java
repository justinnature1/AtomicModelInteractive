package application.factory;

/**
 * Interface used by classes that need to create a particle.
 */
public interface ParticleCreator {
	public double getX();
	public double getY();
	public double getXSpeed();
	public double getYSpeed();
}
