package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Particle implements Drawable {
	
	protected double x;
	protected double y;
	protected Move move;
	protected Image image = null; 
	protected double charge = 1.6E-19;
	protected double mass = 1.67E-27;
	
	public Particle(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return (int)x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public int getY() {
		return (int) y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public abstract void draw(GraphicsContext gc);
		//gc.drawImage(image, x, y);
}
