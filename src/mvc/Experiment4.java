package mvc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Experiment4 extends Experiment {

	public Experiment4() {
		super("levelFourParticles");
		instructions = "The year is 1900. J.J. Thomson had collected evidence to support Lord Kelvin's model of an atom. "
				+ "This model was known as the 'Plum Pudding Model' and was described as having equally dispersed positive charges "
				+ "to conteract the recently discovered negative particles called elctrons.  This experiment matches the expected "
				+ "results of this model when particles are shot at a layer of atoms.\n"
				+ "1. Shoot particles at this plum pudding model of the atom.\n"
				+ "2. Does the plum pudding model represent an atomic model that is solid, empty space, or somewhere in between?";
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DARKRED);
		gc.fillOval(20, 250-75, 512-40, 150);;
		int radius = 20;
		gc.setFill(Color.LIGHTBLUE);
		gc.fillOval(100, 200, radius, radius);
		gc.fillOval(300, 225, radius, radius);
		gc.fillOval(353, 285, radius, radius);
		gc.fillOval(270, 290, radius, radius);
		gc.fillOval(60, 250, radius, radius);
		gc.fillOval(450, 260, radius, radius);
		gc.fillOval(165, 290, radius, radius);
		gc.fillOval(415, 230, radius, radius);
		gc.fillOval(240, 200, radius, radius);
	}

}
