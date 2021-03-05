package mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import application.factory.NucleusFactory;
import application.factory.ParticleCannon;
import application.factory.ParticleCreator;
import application.observer.AlphaParticle;
import application.observer.Particle;
import application.observer.ParticleComponent;
import application.observer.Particles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.PieChart.Data;
import application.observer.Nucleus;

public class LevelOne extends Level implements ParticleCreator {
	String[] fileData;

	public LevelOne() {
		super();
	}

	public void construct() throws FileNotFoundException {
		nuclei = new Particles();
		alphaParticles = new Particles(nuclei);
		particles = new Particles();
		particles.add(alphaParticles);
		particles.add(nuclei);
		nucleusFactory = new NucleusFactory(this, true);
		cannon = new ParticleCannon(250, 490, alphaParticles, nuclei);
		BufferedReader csvReader = new BufferedReader(new FileReader("levelOneParticles"));
		String row;
		try {
			while ((row = csvReader.readLine()) != null) {
				fileData = row.split(",");
//				nucleusFactory.setMoveable(data[2] );
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
}
