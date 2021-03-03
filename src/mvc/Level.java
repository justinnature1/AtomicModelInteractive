package mvc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import application.factory.NucleusFactory;
import application.factory.ParticleCannon;
import application.observer.AlphaParticle;
import application.observer.Particle;
import javafx.scene.canvas.GraphicsContext;

public abstract class Level  {
	ArrayList<Particle> nuclei;
	ArrayList <AlphaParticle> alphaParticles;
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

		for (Particle nucleus : nuclei) {
			nucleus.move(null, elapsedTime);
			nucleus.draw(gc);					
		}

		for (Particle alphaParticle: alphaParticles) {
			for (Particle nucleus : nuclei) {
				alphaParticle.move(nucleus,elapsedTime);	
			}
			alphaParticle.draw(gc);
		}
	}	
}
