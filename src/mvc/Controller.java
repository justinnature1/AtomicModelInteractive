package mvc;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller implements ControllerInterface {
	View view;
	Level level;
	
	public Controller (Level level) {
		this.level = level;
		view = new View(this);
		Platform.startup(() -> {//launch JavaFx application 
			Stage stage = new Stage();
			try {
				view.start(stage);
			} catch (Exception ex) {ex.printStackTrace();}
		});		
	}
	

//	public Controller(Level level, View view) {
//		this.view = view;
//		this.level = level;
//	}
	
	@Override
	public void shootParticle() {
		level.cannon.create();		
	}

	@Override
	public void collectData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		if (!(level == null && view.getGc()==null && view.getElapsedTime()==0)) {
			level.update(view.getGc(), view.getElapsedTime());
		}
	}

	public void handle(MouseEvent e) {
		level.cannon.setMouseX(e.getX());
		level.cannon.setMouseY(e.getY());
		if (e.isPrimaryButtonDown())
			shootParticle();
	}
	
}
