package mvc;

import application.observerAndComposite.ParticleComponent;
import application.templateMethod.Physics;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import application.observerAndComposite.AlphaParticle;

/**
 * This class acts as the controller in the MVC
 * @author Justin Keller
 */
public class Controller implements ControllerInterface {
	View view; //Reference to the view this controller handles
	Experiment experiment; //Reference to the model this controller handles

	/**
	 * Creates an instance of the controller class and initializes the view.
	 * @param experiment A reference to the model/experiment this controller handles
	 */
	public Controller (Experiment experiment) {
		this.experiment = experiment;
		view = new View(this);
		Platform.startup(() -> {//launch JavaFx application
			Stage stage = new Stage();
			try {
				view.start(stage); //Starts the Animation Timer/Game Loop
				view.setGameInstructions(experiment.getInstructions());
				view.setButtonPress(view.experiment1);
			} catch (Exception ex) {ex.printStackTrace();}
		});		
	}



	/**
	 * Allows the cannon to shoot a particle - If more particle shooters exist, need to pass in parameter for particle shooter.
	 * @Override
	 */
	public void shootParticle() {	
		ParticleComponent newParticle = experiment.cannon.shoot();
		newParticle.registerObserver(view.collisionData);	
	}

	/**
	 * Clears information when a new experiment is started  
	 */
	public void initializeExperiment() {
		view.newCollisionData();
		view.setGameInstructions(experiment.getInstructions());
		AlphaParticle.resetParticleNumber();
	}

	@Override
	public void collectData() {
		// Future method to allow the user to determine if a collision happened.
	}

	@Override
	/**
	 * Updates the model
	 */
	public void update() {
		if (!(experiment == null && view.getGc()==null && view.getElapsedTime()==0)) {
			experiment.update(view.getGc(), view.getElapsedTime());
		}
	}

	/**
	 * Handles mouse events for the ParticleCannon class
	 */
	public void handle(MouseEvent e) {
		double cannonAngle = Physics.getAngle(experiment.cannon.getX(), experiment.cannon.getY(), e.getX(), e.getY());
		//prevents the cannon angle from going beyond a certain angle
		if (cannonAngle > 50 && cannonAngle < 130) {
			experiment.cannon.setMouseX(e.getX());
			experiment.cannon.setMouseY(e.getY());
		}
		if (e.isPrimaryButtonDown())
			shootParticle();
	}


	/**
	 * Handles the clicking of the experiment1 button
	 * @Override
	 */
	public void handleClickedExperiment1(ActionEvent e) {
		this.experiment = new Experiment1();
		this.initializeExperiment();
		view.setButtonPress((Button)e.getSource());
	}

	/**
	 * Handles the clicking of the experiment2 button
	 * @Override
	 */
	public void handleClickedExperiment2(ActionEvent e) {
		this.experiment = new Experiment2();
		this.initializeExperiment();
		view.setButtonPress((Button)e.getSource());
	}


	/**
	 * Handles the clicking of the experiment3 button
	 * @Override
	 */
	public void handleClickedExperiment3(ActionEvent e) {
		this.experiment = new Experiment3();
		this.initializeExperiment();
		view.setButtonPress((Button)e.getSource());
	}


	/**
	 * Handles the clicking of the experiment4 button
	 * @Override
	 */
	public void handleClickedExperiment4(ActionEvent e) {
		this.experiment = new Experiment4();
		this.initializeExperiment();
		view.setButtonPress((Button)e.getSource());
	}


	/**
	 * Handles the clicking of the experiment5 button
	 * @Override
	 */
	public void handleClickedExperiment5(ActionEvent e) {
		this.experiment = new Experiment5();
		this.initializeExperiment();
		view.setButtonPress((Button)e.getSource());
	}

}
