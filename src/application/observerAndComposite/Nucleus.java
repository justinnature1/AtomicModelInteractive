package application.observerAndComposite;

import application.templateMethod.Path;
import application.templateMethod.Stationary;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class handles specifics related to nuclei, the particles that can interact with alpha particles
 * @author Justin Keller
 */
public class Nucleus extends Particle{
	//Creates an ID for each nucleus.
	protected static int particleCount = 1;
	
	/**
	 * Creates a nucleus 
	 * @param x The x coordinate of the nucleus
	 * @param y The y coordinate of the nucleus
	 * @param moves Should your particle move (true) or not move (false)
	 */
	public Nucleus(double x, double y, boolean moves) {
		super(x, y);
		this.particleNumber = particleCount++;
		if (moves) {
			this.move = new Path(this, 100, 0);
		} else {
			this.move = new Stationary(this);
		}
		this.setCharge(this.getCharge() * 79); //A gold atom has 79 protons (79 positive charges)
		this.setMass(this.getMass() * 197); // A gold nucleus has 197 protons and neutrons (197 AMU)
	}

	/**
	 * Draws the nucleus
	 * @param gc A reference to the graphics context to draw the nucleus on
	 * @Override
	 */
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LIGHTGREY);
		gc.strokeOval(this.x-2, this.y-2, 4, 4);
		gc.fillOval(this.x-2, this.y-2, 4, 4);
	}
}
