package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import application.factory.NucleusFactory;
import application.factory.ParticleCreator;
import application.observer.Particle;
import javafx.scene.chart.PieChart.Data;

public class LevelOne implements ParticleCreator {
    ArrayList <Particle> nuclei;
    String[] data;
    NucleusFactory nucleusFactory = new NucleusFactory(this, true);
    
    public LevelOne(ArrayList <Particle> nuclei) {
    	this.nuclei = nuclei;
    }
	
	public void construct() throws FileNotFoundException {
		BufferedReader csvReader = new BufferedReader(new FileReader("levelOneParticles"));
		String row;
		try {
			while ((row = csvReader.readLine()) != null) {
				data = row.split(",");
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
		return Double.parseDouble(data[0]);
	}

	@Override
	public double getY() {
		return Double.parseDouble(data[1]);
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
