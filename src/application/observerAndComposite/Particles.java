package application.observerAndComposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Particles extends ParticleComponent{
	List<ParticleComponent> list;
	ParticleComponent neighbor = new NoParticles();

	public Particles () {
		list = new ArrayList<ParticleComponent>(); 
	}

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

	@Override
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


	//	@Override
	//	public double getX() {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}

	//	@Override
	//	public void setX(double x) {
	//		// TODO Auto-generated method stub
	//
	//	}

	//	@Override
	//	public double getY() {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}

	//	@Override
	//	public void setY(double y) {
	//		// TODO Auto-generated method stub
	//
	//	}

	@Override
	public void draw(GraphicsContext gc) {
		synchronized(list) { 
			Iterator<ParticleComponent> it = list.iterator();           
			while (it.hasNext()) 
				it.next().draw(gc);
		} 
	}

	//	@Override
	//	public boolean isCollision() {
	//		// TODO Auto-generated method stub
	//		return false;
	//	}

	//	@Override
	//	public void setCollision(boolean isCollision) {
	//		// TODO Auto-generated method stub
	//
	//	}

	//	@Override
	//	public double getMass() {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}

	//	@Override
	//	public double getCharge() {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}

	@Override
	public Iterator<ParticleComponent> getIterator(){
		return list.iterator();
	}

}
