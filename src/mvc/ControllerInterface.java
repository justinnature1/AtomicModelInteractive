package mvc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface ControllerInterface extends EventHandler<MouseEvent>{
	void shootParticle();
	void collectData();
	void update();
	void handle(MouseEvent e);
	void initializeLevel();
    void handleClickedExperiment1(ActionEvent e);
    void handleClickedExperiment2(ActionEvent e);
    void handleClickedExperiment3(ActionEvent e);
    void handleClickedExperiment4(ActionEvent e);
    void handleClickedExperiment5(ActionEvent e);
}
