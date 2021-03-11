package mvc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Experiment5 extends Experiment {

	public Experiment5() {
		super("levelFiveParticles");
		instructions = "The year is 1911.  You and your colleague, Ernest Rutherford planned an experiment to test J.J. Thomson's "
				+ "'Plum Pudding Model' of the atom represented by Experiment 4.  Famously called the 'Gold Foil Experiment', "
				+ "alpha particles were shot at an extremely thin piece of gold foil.  This foil was in theory the thickness of a "
				+ "couple atoms.  This experiment led to our current understanding of the atomic model.\n"
				+ "1. Shoot particles at the gold foil.\n"
				+ "2. Does your data support or reject the Plum Pudding Model?\n"
				+ "3. Does your data suggest that the current atomic model is empty space, solid, or somewhere in between?";
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GOLD);
		gc.fillRect(20, 250-40, 512-40, 80);
		int x;
		int y;
		gc.setFill(Color.WHITE);
		for (int i = 0; i < 10; i++) {
			x = (int)(Math.random()*(512-40)+20);
			y = (int)(Math.random()*80+250-40);
			gc.fillOval(x,y,2,2);
		}

	}

}
