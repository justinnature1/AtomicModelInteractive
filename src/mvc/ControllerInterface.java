package mvc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Interface implemented by the controller
 * @author Justin Keller
 */
public interface ControllerInterface extends EventHandler<MouseEvent>{
	void shootParticle();
	void collectData();
	void update();
	void handle(MouseEvent e);
	void initializeExperiment();
    void handleClickedExperiment1(ActionEvent e);
    void handleClickedExperiment2(ActionEvent e);
    void handleClickedExperiment3(ActionEvent e);
    void handleClickedExperiment4(ActionEvent e);
    void handleClickedExperiment5(ActionEvent e);
}
