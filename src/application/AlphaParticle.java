package application;

import javafx.scene.image.Image;

public class AlphaParticle extends Particle {

//	protected Particle neighbor new NoParticle
	
	public AlphaParticle(int x, int y, int xSpeed, int ySpeed) {
		super(x, y);
		this.move = new ElectricalCollision(this, xSpeed, ySpeed);
		this.image = new Image ("file:alpha.png");
		this.charge = this.charge * 2;
		this.mass = this.mass * 4;
	}


}
