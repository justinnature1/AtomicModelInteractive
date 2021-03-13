package application.observerAndComposite;

import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class creates an interface for all particle classes to use.  It creates the framework for the composite pattern
 * that manages collections of particles or individual particles.
 * @author Justin Keller
 */
public abstract class ParticleComponent implements Drawable, Subject {

	public int getParticleNumber() {
		throw new UnsupportedOperationException();
	}
	
	public ParticleComponent getNeighbor() {
		throw new UnsupportedOperationException();
	}
	
	public void setNeighbor(ParticleComponent neighbor) {
		throw new UnsupportedOperationException();
	}

	public void add(ParticleComponent particleComponent) {
		throw new UnsupportedOperationException();
	}

	public void remove(ParticleComponent particleComponent) {
		throw new UnsupportedOperationException();
	}

	public ParticleComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void move (double elapsedTime) {
		throw new UnsupportedOperationException();
	}

	public double getX(){
		throw new UnsupportedOperationException();
	}

	public void setX(double x){
		throw new UnsupportedOperationException();
	}

	public double getY(){
		throw new UnsupportedOperationException();
	}

	public void setY(double y){
		throw new UnsupportedOperationException();
	}

	public void draw(GraphicsContext gc){
		throw new UnsupportedOperationException();
	}

	public boolean isCollision(){
		throw new UnsupportedOperationException();
	}

	public void setCollision(boolean isCollision){
		throw new UnsupportedOperationException();
	}

	public double getMass(){
		throw new UnsupportedOperationException();
	}

	public double getCharge(){
		throw new UnsupportedOperationException();
	}

	public Iterator<ParticleComponent> getIterator(){
		throw new UnsupportedOperationException();
	}

	@Override
	public void registerObserver(Observer o) {
		throw new UnsupportedOperationException();	
	}

	@Override
	public void removeObserver(Observer o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void notifyObservers() {
		throw new UnsupportedOperationException();	
	}

}
