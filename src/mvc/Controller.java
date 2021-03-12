package mvc;

import application.observerAndComposite.ParticleComponent;
import application.templateMethod.Physics;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import application.observerAndComposite.AlphaParticle;

public class Controller implements ControllerInterface {
	View view;
	Experiment experiment;

	public Controller (Experiment experiment) {
		this.experiment = experiment;
		view = new View(this);
		Platform.startup(() -> {//launch JavaFx application 
			Stage stage = new Stage();
			try {
				view.start(stage);
				view.setGameInstructions(experiment.getInstructions());
				view.setButtonPress(view.experiment1);
			} catch (Exception ex) {ex.printStackTrace();}
		});		
	}


	@Override
	public void shootParticle() {	
		ParticleComponent newParticle = experiment.cannon.create();
		newParticle.registerObserver(view.collisionData);	
	}

	public void initializeLevel() {
		view.newCollisionData();
		view.setGameInstructions(experiment.getInstructions());
		AlphaParticle.resetParticleNumber();
		//		experiment.cannon.set
	}

	@Override
	public void collectData() {
		// Future method to allow the user to determine if a collision happened.
	}

	@Override
	public void update() {
		if (!(experiment == null && view.getGc()==null && view.getElapsedTime()==0)) {
			experiment.update(view.getGc(), view.getElapsedTime());
		}
	}

	public void handle(MouseEvent e) {
		double cannonAngle = Physics.getAngle(experiment.cannon.getX(), experiment.cannon.getY(), e.getX(), e.getY());
		if (cannonAngle > 50 && cannonAngle < 130) {
			experiment.cannon.setMouseX(e.getX());
			experiment.cannon.setMouseY(e.getY());
		}
		if (e.isPrimaryButtonDown())
			shootParticle();
	}


	@Override
	public void handleClickedExperiment1(ActionEvent e) {
		this.experiment = new Experiment1();
		this.initializeLevel();
		view.setButtonPress((Button)e.getSource());
	}


	@Override
	public void handleClickedExperiment2(ActionEvent e) {
		this.experiment = new Experiment2();
		this.initializeLevel();
		view.setButtonPress((Button)e.getSource());
	}


	@Override
	public void handleClickedExperiment3(ActionEvent e) {
		this.experiment = new Experiment3();
		this.initializeLevel();
		view.setButtonPress((Button)e.getSource());
	}


	@Override
	public void handleClickedExperiment4(ActionEvent e) {
		this.experiment = new Experiment4();
		this.initializeLevel();
		view.setButtonPress((Button)e.getSource());
	}


	@Override
	public void handleClickedExperiment5(ActionEvent e) {
		this.experiment = new Experiment5();
		this.initializeLevel();
		view.setButtonPress((Button)e.getSource());
	}

}
