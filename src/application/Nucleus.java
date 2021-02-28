package application;

import javafx.scene.canvas.GraphicsContext;

public class Nucleus extends Particle{

	public Nucleus(double x, double y, boolean moves) {
		super(x, y);
		if (moves) {
			this.move = new Path(this, 5, 0);
		} else {
//			xSpeed = ySpeed = 0;
			this.move = new Stationary(this);
		}
		this.charge = this.charge * 79;
		this.mass = this.mass * 197;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(this.x, this.y, 3, 3);
		
	}
}
