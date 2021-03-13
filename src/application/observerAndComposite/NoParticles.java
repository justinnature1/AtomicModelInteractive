package application.observerAndComposite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A class used to prevent null references specifically for the neighbors of particles.  If a particle shouldn't interact
 * with other particles, use this class to prevent null reference errors.
 * @author Justin Keller
 *
 */
public class NoParticles extends ParticleComponent{
	List<ParticleComponent> list;//Should always be a list containing no items.

	public NoParticles () {
		list = new ArrayList<ParticleComponent>(); 
	}
	
	@Override
	public Iterator<ParticleComponent> getIterator(){
		return list.iterator();
	}
	
}
