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

/**
 * This class acts as the model that instantiates and manages the back end in the MVC.
 * @author Justin Keller
 */
public abstract class Experiment implements ParticleCreator, Drawable {
	
	ParticleComponent particles; //Collection of all particles
	ParticleComponent nuclei; //Collection of all nuclei
	ParticleComponent alphaParticles; //Collection of all alpha particles
	NucleusFactory nucleusFactory; //Factory used to create nuclei
	ParticleCannon cannon; //Cannon to shoot particles
	String instructions; //Experiment instructions
	
	//Used by the text reader to create nuclei for each experiment
	String fileName;
	String[] fileData; 

	/**
	 * This instantiates the level and constructs the various components need to run the back end
	 * @param fileName The name of the text file that contains a CSV list of nuclei to create for a level
	 */
	public Experiment(String fileName) {
		try {
			this.fileName = fileName;
			construct();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * The method that instantiates all the relevant components of the model
	 * @throws FileNotFoundException
	 */
	public void construct() throws FileNotFoundException {
		nuclei = new Particles();
		alphaParticles = new Particles(nuclei);
		particles = new Particles();
		particles.add(alphaParticles);
		particles.add(nuclei);
		nucleusFactory = new NucleusFactory(this, true);
		cannon = new ParticleCannon(250, 490, alphaParticles, nuclei);
		
		//This Reader creates the nuclei from a text file of relevant parameters.
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

	/**
	 * This method updates the various components of the experiment.  
	 * This includes moving and drawing all particles and the cannon
	 * @param gc A reference to the graphics context to draw objects on
	 * @param elapsedTime The amount of elapsed time since the last call
	 */
	public void update(GraphicsContext gc, double elapsedTime) {
		particles.move(elapsedTime);
		particles.draw(gc);
		this.draw(gc);
		cannon.draw(gc);
	}	
}
