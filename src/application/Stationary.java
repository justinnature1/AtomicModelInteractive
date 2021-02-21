package application;

public class Stationary extends Move {

	public Stationary(Particle particle) {
		super(particle, 0, 0);
	}
	
	protected void setAcceleration(Particle neighbor) {
		//Do Nothing as the Acceleration and Speed need to stay zero.
	}
	
}
