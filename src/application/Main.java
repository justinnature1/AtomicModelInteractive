package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage theStage) {
		theStage.setTitle( "Atoms: Solid or Empty?" );

		Group root = new Group();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );

		Canvas canvas = new Canvas( 512, 512 );
		root.getChildren().add( canvas );

		GraphicsContext gc = canvas.getGraphicsContext2D();

		LongValue lastNanoTime = new LongValue(System.nanoTime());

		ArrayList <AlphaParticle> alphaParticles = new ArrayList<AlphaParticle>();

        theScene.setOnMousePressed(
                
        		new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	System.out.println(e.getX() + " " + e.getY());
                    	int xCannon = 250;
                    	int yCannon = 500;
                    	int xDiff = (int)e.getX()-xCannon;
                    	int yDiff = (int)e.getY()-yCannon;
                    	int xSpeed = (int) (125*(xDiff)/(Math.pow(xDiff*xDiff+yDiff*yDiff,.5)));
                    	int ySpeed = (int) (125*(yDiff)/(Math.pow(xDiff*xDiff+yDiff*yDiff,.5)));
                    	alphaParticles.add(new AlphaParticle(250, 500, xSpeed, ySpeed));
//                        String code = e.getCode().toString();
//                        if ( !alphaParticles )
//                            alphaParticles.add( code );
                    }
                });
		
        ArrayList <Particle> nuclei = new ArrayList<>();
        nuclei.add(new Nucleus(250, 250, true));
        nuclei.add(new Nucleus(100, 100, false));

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;	 
				gc.clearRect(0, 0, 512, 512);
				
				for (Particle nucleus : nuclei) {
					nucleus.move.move(null, elapsedTime);
					gc.strokeOval(nucleus.x, nucleus.y, 3, 3);					
				}

				for (int i = 0; i < alphaParticles.size(); i++) {
					for (Particle nucleus : nuclei) {
						alphaParticles.get(i).move.move(nucleus,elapsedTime);	
					}
					gc.strokeOval(alphaParticles.get(i).x, alphaParticles.get(i).y, 5, 5);
					gc.fillOval(alphaParticles.get(i).x, alphaParticles.get(i).y, 5, 5);
				}
				//b.render(gc);
			}
		}.start();

		theStage.show();
	}
}