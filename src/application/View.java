package application;

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

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage theStage) {
		theStage.setTitle( "Atoms: Solid or Empty?" );
		theStage.getIcons().add(new Image("file:alpha.png"));

		HBox root = new HBox();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );

		Canvas gameCanvas = new Canvas(512, 512);
		GraphicsContext gc = gameCanvas.getGraphicsContext2D();
		Separator separator = new Separator(Orientation.VERTICAL);
		Canvas dataCanvas = new Canvas(150,512);
		TableView dataTable = new TableView();
		
		TableColumn<Particle, Integer> column1 = new TableColumn<>("Particle #");
		column1.setCellValueFactory(new PropertyValueFactory<>("particleNumber"));
		
		TableColumn<Particle, Boolean> column2 = new TableColumn<>("Collision?");
		column2.setCellValueFactory(new PropertyValueFactory<>("collision"));
		
		dataTable.getColumns().add(column1);
		dataTable.getColumns().add(column2);
		
		root.getChildren().addAll( gameCanvas, separator, dataTable );

		LongValue lastNanoTime = new LongValue(System.nanoTime());

		ArrayList <AlphaParticle> alphaParticles = new ArrayList<AlphaParticle>();

		ArrayList <Particle> nuclei = new ArrayList<>();
		LevelOne level = new LevelOne(nuclei);
		try {
			level.construct();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ParticleCannon cannon = new ParticleCannon(250, 490, alphaParticles);
		
        gameCanvas.setOnMousePressed(cannon::handle);
        gameCanvas.setOnMouseMoved(cannon::handle);

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;	 
				gc.clearRect(0, 0, 512, 512);
				
				cannon.draw(gc);
				
				for (Particle nucleus : nuclei) {
					nucleus.move(null, elapsedTime);
					nucleus.draw(gc);					
				}

				for (Particle alphaParticle: alphaParticles) {
					for (Particle nucleus : nuclei) {
						alphaParticle.move(nucleus,elapsedTime);	
					}
					alphaParticle.draw(gc);
				}
				//b.render(gc);
			}
		}.start();

		theStage.show();
	}
}