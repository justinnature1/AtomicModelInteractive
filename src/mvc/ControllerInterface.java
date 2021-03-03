package mvc;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface ControllerInterface extends EventHandler<MouseEvent>{
	void shootParticle();
	void collectData();
	void update();
	void handle(MouseEvent e);
}
