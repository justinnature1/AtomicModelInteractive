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
	//	Scene scene;

	ParticleComponent alphaparticles;

	double cannonX;
	double cannonY;

	double mouseX;
	double mouseY;

	double diffX = 0;
	double diffY = 0;

	double fireSpeed = 175;

	AlphaParticleFactory pf;

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

	public ParticleComponent create() {
		ParticleComponent newParticle = pf.create();
		alphaparticles.add(newParticle);
		return newParticle;
	}
	
//	public void handle(MouseEvent e)
//	{
//		setMouseX(e.getX());
//		setMouseY(e.getY());
//		if (e.isPrimaryButtonDown())
//			alphaparticles.add(pf.create());
//		
//	}

	public void draw(GraphicsContext gc) {
        
		gc.save();
		gc.translate(this.cannonX, this.cannonY);
		gc.rotate(Physics.getAngle(cannonX, cannonY, mouseX, mouseY));
		gc.translate(-this.cannonX, -this.cannonY);

		Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(.5, Color.LIGHTGREY), new Stop(1, Color.BLACK)};
        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        
        gc.setFill(lg1);
		//gc.setFill(Color.LIGHTGREY);
		gc.fillRect(cannonX-20, cannonY-7, 20, 14);
		gc.restore();
	}

}


