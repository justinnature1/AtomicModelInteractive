package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ShotParticle extends Particle {

	public ShotParticle(int x, int y, int xSpeed, int ySpeed) {
		super(x, y, xSpeed, ySpeed);
		this.image = new Image ("file:alpha.png");
	}


}
