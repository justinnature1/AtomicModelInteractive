package application.observerAndComposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class NoParticles extends ParticleComponent{
	List<ParticleComponent> list;

	public NoParticles () {
		list = new ArrayList<ParticleComponent>(); 
	}
	
	@Override
	public void add(ParticleComponent particleComponent) {
		//list.add(particleComponent);
	}

	@Override
	public void remove(ParticleComponent particleComponent) {
		//list.remove(particleComponent);
	}

//	@Override
//	public ParticleComponent getChild(int i) {
//		//return (ParticleComponent)list.get(i);
//	}

	//	@Override
	//	public void move(Particles particle, double elapsedTime) {
	//		synchronized(list) { 
	//			Iterator<ParticleGroup> it = list.iterator();           
	//			while (it.hasNext()) 
	//				it.next().move(particle, elapsedTime); 
	//		} 
	//	}

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

//	@Override
//	public void draw(GraphicsContext gc) {
//		synchronized(list) { 
//			Iterator<ParticleComponent> it = list.iterator();           
//			while (it.hasNext()) 
//				it.next().draw(gc);
//		} 
//	}

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
