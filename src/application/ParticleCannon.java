package application;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class ParticleCannon implements ParticleCreator, EventHandler<MouseEvent>, Drawable {
	//	Scene scene;

	ArrayList <AlphaParticle> alphaparticles;

	double cannonX;
	double cannonY;

	double mouseX;
	double mouseY;

	double diffX = 0;
	double diffY = 0;

	double fireSpeed = 75;

	AlphaParticleFactory pf;

	public ParticleCannon (double x, double y, ArrayList <AlphaParticle> alphaParticles) {
		//		this.scene = scene;
		this.cannonX = x;
		this.cannonY = y;
		this.alphaparticles = alphaParticles;
		pf = new AlphaParticleFactory(this);
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

	protected void setMouseX(double mouseX) {
		this.mouseX = mouseX;
		this.setDiffX();
	}

	protected void setMouseY(double mouseY) {
		this.mouseY = mouseY;
		this.setDiffY();
	}


	protected void setDiffX() {
		this.diffX = this.mouseX - this.cannonX;
	}

	protected void setDiffY() {
		this.diffY = this.mouseY - this.cannonY;
	}

	public double getXSpeed() {
		return (this.fireSpeed*this.diffX/Physics.hypothenuseLen(diffX, diffY));
	}


	public double getYSpeed() {
		return (this.fireSpeed*this.diffY/Physics.hypothenuseLen(diffX, diffY));
	}


	public void handle(MouseEvent e)
	{
		setMouseX(e.getX());
		setMouseY(e.getY());
		if (e.isPrimaryButtonDown())
			alphaparticles.add(pf.create());
		
	}

	public void draw(GraphicsContext gc) {
		gc.save();
		gc.translate(this.cannonX, this.cannonY);
		gc.rotate(Physics.getAngle(cannonX, cannonY, mouseX, mouseY));
		gc.translate(-this.cannonX, -this.cannonY);

		gc.fillRect(cannonX-20, cannonY-5, 20, 10);
		gc.restore();
	}

}


