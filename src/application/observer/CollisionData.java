package application.observer;

import javafx.scene.image.Image;

public class CollisionData implements Observer{
	boolean[] data = new boolean[20];
	private static int particleCount = 1;
	private int particleNumber;

	
	@Override
	public void update(Particle particle) {
		this.particleNumber = particleCount++;
		if (data.length>particleNumber-1) {
			data[particleNumber-1] = particle.isCollision();
		}
		//particle.removeObserver(this);
		for (boolean collision : data) {
			System.out.print(collision + ", ");
		}
		System.out.println();
	}

}
