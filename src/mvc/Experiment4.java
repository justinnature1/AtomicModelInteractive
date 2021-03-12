package mvc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Experiment4 extends Experiment {

	public Experiment4() {
		super("levelFourParticles");
		instructions = "The year is 1900. J.J. Thomson collected evidence to support the most recent "
				+ "model of an atom known as the 'Plum Pudding Model'. This model had positive charges "
				+ "in a sea of recently discovered negative charges called electrons.  On your screen, you see the plum pudding model. "
				+ "Just like scientists can't see atoms, you can't see the atoms here either. Let the data help you 'see'.\n"
				+ "1. Shoot particles at the plum pudding model of the atom.\n"
				+ "2. The data you collect shows the expected results of the plum pudding model. " 
				+ "Is this model of an atom solid, empty space, or somewhere in between?";
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
