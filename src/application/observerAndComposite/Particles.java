package application.observerAndComposite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class allows collections of particles to be created.  The move and draw method will move and draw a collection
 * of particles with one call
 * @author Justin Keller
 */
public class Particles extends ParticleComponent{
	List<ParticleComponent> list;//Manages the collection of particles

	//References the neighbors of a collection of particles to interact/collide with
	ParticleComponent neighbor = new NoParticles();

	/**
	 * Creates a collection of particles with no neighbors
	 */
	public Particles () {
		list = new ArrayList<ParticleComponent>(); 
	}

	/**
	 * Creates a collection of particles that share a common set of neighbors to interact with
	 * @param neighbor A reference to the neighbors the particles interact/collide with.
	 */
	public Particles (ParticleComponent neighbor) {
		this(); 
		this.neighbor = neighbor;
	}

	@Override
	public ParticleComponent getNeighbor(){
		return neighbor;
	}

	@Override
	public void add(ParticleComponent particleComponent) {
		list.add(particleComponent);
	}

	@Override
	public void remove(ParticleComponent particleComponent) {
		list.remove(particleComponent);
	}

	@Override
	public ParticleComponent getChild(int i) {
		return (ParticleComponent)list.get(i);
	}

	/**
	 * Iterates through a collection of particles and handles the movement of each individual component
	 * @param elapsedTime The amount of time that the movement has occurred for. 
	 * @Override
	 */
	public void move(double elapsedTime) {
		synchronized(list) { 
			Iterator<ParticleComponent> it = list.iterator();           
			ParticleComponent p;
			while (it.hasNext()) {
				p = it.next();
				p.move(elapsedTime);
			}
		}
	} 

	/**
	 * Iterates through a collection of particles and handles the drawing of each individual component
	 * @param gc The graphics context to draw the particles on 
	 * @Override
	 */
	public void draw(GraphicsContext gc) {
		synchronized(list) { 
			Iterator<ParticleComponent> it = list.iterator();           
			while (it.hasNext()) 
				it.next().draw(gc);
		} 
	}

	@Override
	public Iterator<ParticleComponent> getIterator(){
		return list.iterator();
	}

}
