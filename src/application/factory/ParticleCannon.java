package application.factory;

import application.observerAndComposite.Drawable;
import application.observerAndComposite.ParticleComponent;
import application.templateMethod.Physics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ParticleCannon implements ParticleCreator, Drawable {

	ParticleComponent alphaparticles;

	double cannonX;
	double cannonY;

	double mouseX;
	double mouseY;

	double diffX = 0;
	double diffY = 0;

	double fireSpeed = 175;

	AlphaParticleFactory pf;

	/**
	 * This class allows the user to shoot alpha particles in the game.
	 * @param x The x coordinate of the cannon
	 * @param y The y coordinate of the cannon
	 * @param alphaParticles The collection of alpha particles this cannon will add created particles to
	 * @param neighbors The collection of neighbor particles alphaParticles will collide with.
	 */
	public ParticleCannon (double x, double y, ParticleComponent alphaParticles, ParticleComponent neighbors) {
		this.cannonX = x;
		this.cannonY = y;
		this.alphaparticles = alphaParticles;
		pf = new AlphaParticleFactory(this, neighbors);
	}

	public double getX() {
		return this.cannonX;
	}

	public void setY (int y) {
		this.cannonY = y;
	}

	public double getY() {
		return this.cannonY;
	}


	public void setX (int x) {
		this.cannonX = x;
	}

	
	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
		this.setDiffX();
	}

	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
		this.setDiffY();
	}

	private void setDiffX() {
		this.diffX = this.mouseX - this.cannonX;
	}

	private void setDiffY() {
		this.diffY = this.mouseY - this.cannonY;
	}

	/**
	 * Determine the xSpeed of a shot particle based on the angle of the cannon.
	 */
	public double getXSpeed() {
		return (this.fireSpeed*this.diffX/Physics.hypothenuseLen(diffX, diffY));
	}

	/**
	 * Determines the ySpeed of a shot particle based on the angle of the cannon.
	 */
	public double getYSpeed() {
		return (this.fireSpeed*this.diffY/Physics.hypothenuseLen(diffX, diffY));
	}

	/**
	 * This method is used to shoot an alpha particle
	 * @return new shot particle using the 
	 */
	public ParticleComponent shoot() {
		ParticleComponent newParticle = pf.create();
		alphaparticles.add(newParticle);
		return newParticle;
	}
	
	/**
	 * Draws the cannon on a graphics context
	 * @param gc A reference to the graphics context to draw on
	 */
	public void draw(GraphicsContext gc) {
		//Rotates the and moves the graphics context so the cannon can be drawn at the correct angle.
		gc.save();
		gc.translate(this.cannonX, this.cannonY);
		gc.rotate(Physics.getAngle(cannonX, cannonY, mouseX, mouseY));
		gc.translate(-this.cannonX, -this.cannonY);

		//Creates a gradient to make the cannon look more 3D
		Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(.5, Color.LIGHTGREY), new Stop(1, Color.BLACK)};
        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        
        //Creates the rectangle using the gradient.
        gc.setFill(lg1);
		gc.fillRect(cannonX-20, cannonY-7, 20, 14);

		//Restores the position of the graphics context.
		gc.restore();
	}

}


