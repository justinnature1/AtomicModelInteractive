package mvc;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import application.factory.ParticleCannon;
import application.observer.AlphaParticle;
import application.observer.Particle;

public class View extends Application {

	HBox root;
	Scene theScene;
	Canvas gameCanvas;
	GraphicsContext gc;
	Separator separator;
	Canvas dataCanvas;
	TableView dataTable;
	TableColumn<Particle, Integer> column1;
	TableColumn<Particle, Boolean> column2;
	LongValue lastNanoTime;
	ControllerInterface controller;
	double elapsedTime;


	public View(ControllerInterface controller) {
		this.controller = controller;
	}

	public void start(Stage theStage) {

		createView(theStage);
		LongValue lastNanoTime = new LongValue(System.nanoTime());
        gameCanvas.setOnMousePressed(controller::handle);
        gameCanvas.setOnMouseMoved(controller::handle);
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
		theScene = new Scene( root );
		theStage.setScene( theScene );

		gameCanvas = new Canvas(512, 512);
		gc = gameCanvas.getGraphicsContext2D();
		separator = new Separator(Orientation.VERTICAL);
		dataCanvas = new Canvas(150,512);
		dataTable = new TableView();

		column1 = new TableColumn<>("Particle #");
		column1.setCellValueFactory(new PropertyValueFactory<>("particleNumber"));

		column2 = new TableColumn<>("Collision?");
		column2.setCellValueFactory(new PropertyValueFactory<>("collision"));

		dataTable.getColumns().add(column1);
		dataTable.getColumns().add(column2);

		root.getChildren().addAll( gameCanvas, separator, dataTable );		
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

	public TableView getDataTable() {
		return dataTable;
	}

	public void setDataTable(TableView dataTable) {
		this.dataTable = dataTable;
	}

}