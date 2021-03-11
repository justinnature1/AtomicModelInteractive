package mvc;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import application.observerAndComposite.CollisionData;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class View extends Application {

	Stage stage;
	HBox root;
	Scene theScene;
	Canvas gameCanvas;
	GraphicsContext gc;
	Button experiment1;
	Button experiment2;
	Button experiment3;
	Button experiment4;
	Button experiment5;
	TilePane buttonPane;
	Separator separator;
	VBox vBox1;
	VBox vBox2;
	TabPane dataView;
	LongValue lastNanoTime;
	ControllerInterface controller;
	double elapsedTime;
	CollisionData collisionData;
	Label gameInstructions;

	public View(ControllerInterface controller) {
		this.controller = controller;
	}

	public void start(Stage theStage) {
		this.stage = theStage;
		createView(stage);
		LongValue lastNanoTime = new LongValue(System.nanoTime());
        gameCanvas.setOnMousePressed(controller::handle);
        gameCanvas.setOnMouseMoved(controller::handle);
        experiment1.setOnAction(controller::handleClickedExperiment1);
        experiment2.setOnAction(controller::handleClickedExperiment2);
        experiment3.setOnAction(controller::handleClickedExperiment3);
        experiment4.setOnAction(controller::handleClickedExperiment4);
        experiment5.setOnAction(controller::handleClickedExperiment5);
        
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;	 
				gc.clearRect(0, 0, 512, 512);
				controller.update();
			}
		}.start();

		theStage.show();


	}

	public void createView(Stage theStage) {
		theStage.setTitle( "Atoms: Solid or Empty?" );
		theStage.getIcons().add(new Image("file:alpha.png"));

		root = new HBox();
		theScene = new Scene(root);
		theStage.setScene(theScene);

		gameCanvas = new Canvas(512, 512);
		gc = gameCanvas.getGraphicsContext2D();
		
		experiment1 = new Button("Experiment 1");
		experiment2 = new Button("Experiment 2");
		experiment3 = new Button("Experiment 3");
		experiment4 = new Button("Experiment 4");
		experiment5 = new Button("Experiment 5");
		
		Button[] buttons = {experiment1, experiment2, experiment3, experiment4, experiment5};
		
		buttonPane = new TilePane();
		buttonPane.setAlignment(null);
		buttonPane.setHgap(10);
		buttonPane.setPadding(new Insets(10, 0, 10, 0));;
		buttonPane.getChildren().addAll(buttons);
		buttonPane.setAlignment(Pos.CENTER);
		
		vBox1 = new VBox();
		vBox1.getChildren().addAll(gameCanvas, buttonPane);
		vBox1.setStyle("-fx-background-color: black");
		
		separator = new Separator(Orientation.VERTICAL);
		
		vBox2 = new VBox();
		dataView = new TabPane();
		collisionData = new CollisionData(dataView);
		
		gameInstructions = new Label("Loading Instructions...");
		gameInstructions.setMaxWidth(200);
		gameInstructions.setWrapText(true);
		
		vBox2.getChildren().addAll(dataView,gameInstructions);
		
		
		
		root.getChildren().addAll(vBox1, separator, vBox2);
		//controller.initializeLevel();
	}

	public void newCollisionData() {
		collisionData.clearData();
	}
	
	public void setGameInstructions (String instructions) {
		this.gameInstructions.setText(instructions);
	}
	
	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public TabPane getDataTable() {
		return dataView;
	}

	public void setDataTable(TabPane dataView) {
		this.dataView = dataView;
	}

}