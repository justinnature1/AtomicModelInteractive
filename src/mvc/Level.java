package mvc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import application.factory.NucleusFactory;
import application.factory.ParticleCannon;
import application.observer.AlphaParticle;
import application.observer.Particle;
import application.observer.ParticleComponent;
import javafx.scene.canvas.GraphicsContext;

public abstract class Level  {
	ParticleComponent particles;
	ParticleComponent nuclei;
	ParticleComponent alphaParticles;
	NucleusFactory nucleusFactory; 
	ParticleCannon cannon;

	public Level() {
		try {
			construct();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract void construct() throws FileNotFoundException;

	public void update(GraphicsContext gc, double elapsedTime) {

		cannon.draw(gc);
		particles.move(elapsedTime);
		particles.draw(gc);
//		Iterator<ParticleComponent> itNuclei = nuclei.getIterator();
//		ParticleComponent nucleus;
//		while (itNuclei.hasNext()) {
//			nucleus = itNuclei.next();
//			nucleus.move(null, elapsedTime);
//			nucleus.draw(gc);					
//		}
//
//		Iterator<ParticleComponent>itAlpha = alphaParticles.getIterator();
//		ParticleComponent alpha;
//		while (itAlpha.hasNext()) {
//			alpha = itAlpha.next();
//			itNuclei = nuclei.getIterator();
//			while (itNuclei.hasNext()) {
//				alpha.move(itNuclei.next(),elapsedTime);	
//			}
//			alpha.draw(gc);
//		}
	}	
}
