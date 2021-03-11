package application.observerAndComposite;

import application.templateMethod.Path;
import application.templateMethod.Stationary;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Nucleus extends Particle{
	protected static int particleCount = 1;
	
	public Nucleus(double x, double y, boolean moves) {
		super(x, y);
		this.particleNumber = particleCount++;
		if (moves) {
			this.move = new Path(this, 100, 0);
		} else {
			//			xSpeed = ySpeed = 0;
			this.move = new Stationary(this);
		}
		this.setCharge(this.getCharge() * 79);
		this.setMass(this.getMass() * 197);

	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LIGHTGREY);
		gc.strokeOval(this.x-2, this.y-2, 4, 4);
		gc.fillOval(this.x-2, this.y-2, 4, 4);
	}


}
