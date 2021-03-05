package application.observer;

import application.templateMethod.Path;
import application.templateMethod.Stationary;
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
		this.setCharge(this.getCharge() * 79);
		this.setMass(this.getMass() * 197);

	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(this.x-2, this.y-2, 4, 4);

	}


}
