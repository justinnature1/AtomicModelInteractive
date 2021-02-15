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


public class Main extends Application 
{

	public static void main(String[] args) 
	{
		launch(args);
	}

	public void start(Stage theStage) 
	{
		theStage.setTitle( "Atoms: Solid or Empty?" );

		Group root = new Group();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );

		Canvas canvas = new Canvas( 512, 512 );
		root.getChildren().add( canvas );



		
		GraphicsContext gc = canvas.getGraphicsContext2D();

		LongValue lastNanoTime = new LongValue(System.nanoTime());

		ArrayList <ShotParticle> alphaParticles = new ArrayList<ShotParticle>();

//		for (int i = 0; i < alphaParticles.length; i++)
//			alphaParticles[i] = new AlphaParticle(225 + i * 1, 500, 0, -100);	

		//theScene.addEventHandler(<MouseEvent>);
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
                    	int xSpeed = (int) (175*(xDiff)/(Math.pow(xDiff*xDiff+yDiff*yDiff,.5)));
                    	int ySpeed = (int) (175*(yDiff)/(Math.pow(xDiff*xDiff+yDiff*yDiff,.5)));
                    	alphaParticles.add(new ShotParticle(250, 500, xSpeed, ySpeed));
//                        String code = e.getCode().toString();
//                        if ( !alphaParticles )
//                            alphaParticles.add( code );
                    }
                });
		
		ShotParticle b = new ShotParticle(250, 259, 0, 0);

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;	 
				gc.clearRect(0, 0, 512, 512);
				b.update(elapsedTime);

				for (int i = 0; i < alphaParticles.size(); i++) {
					alphaParticles.get(i).update(elapsedTime,b);
					gc.strokeOval(alphaParticles.get(i).x, alphaParticles.get(i).y, 5, 5);
					gc.fillOval(alphaParticles.get(i).x, alphaParticles.get(i).y, 5, 5);
				}
				gc.strokeOval(b.x, b.y, 3, 3);


				//b.render(gc);
			}
		}.start();

		theStage.show();
	}
}