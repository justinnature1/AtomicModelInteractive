package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AlphaParticle extends Particle {

	public AlphaParticle(double x, double y, double xSpeed, double ySpeed) {
		super(x, y);
		this.move = new ElectricalCollision(this, xSpeed, ySpeed);
		this.image = new Image ("file:alpha.png");
		this.charge = this.charge * 2;
		this.mass = this.mass * 4;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(this.x, this.y, 5, 5);
		gc.fillOval(this.x, this.y, 5, 5);
	}
}
