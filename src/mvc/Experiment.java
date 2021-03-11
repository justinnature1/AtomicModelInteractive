package mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import application.factory.NucleusFactory;
import application.factory.ParticleCannon;
import application.factory.ParticleCreator;
import application.observerAndComposite.Drawable;
import application.observerAndComposite.ParticleComponent;
import application.observerAndComposite.Particles;
import javafx.scene.canvas.GraphicsContext;

public abstract class Experiment implements ParticleCreator, Drawable {
	ParticleComponent particles;
	ParticleComponent nuclei;
	ParticleComponent alphaParticles;
	NucleusFactory nucleusFactory; 
	ParticleCannon cannon;
	String instructions;
	String fileName;
	String[] fileData; 

	public Experiment(String fileName) {
		try {
			this.fileName = fileName;
			construct();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void construct() throws FileNotFoundException {
		nuclei = new Particles();
		alphaParticles = new Particles(nuclei);
		particles = new Particles();
		particles.add(alphaParticles);
		particles.add(nuclei);
		nucleusFactory = new NucleusFactory(this, true);
		cannon = new ParticleCannon(250, 490, alphaParticles, nuclei);
		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
		String row;
		try {
			while ((row = csvReader.readLine()) != null) {
				fileData = row.split(",");
				nucleusFactory.setMoveable(this.getMoveable());
				nuclei.add(nucleusFactory.create());

			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	@Override
	public double getX() {
		return Double.parseDouble(fileData[0]);
	}

	@Override
	public double getY() {
		return Double.parseDouble(fileData[1]);
	}

	@Override
	public double getXSpeed() {
		return 0;
	}

	@Override
	public double getYSpeed() {
		return 0;
	}

	public boolean getMoveable() {

		if (fileData[2].equals("1"))
			return true;
		else
			return false;
	}

	public String getInstructions() {
		return instructions;
	}

	public void draw(GraphicsContext gc) {
		//Default: Don't Draw Anything.
	}

	public void update(GraphicsContext gc, double elapsedTime) {
		particles.move(elapsedTime);
		particles.draw(gc);
		this.draw(gc);
		cannon.draw(gc);
	}	
}
