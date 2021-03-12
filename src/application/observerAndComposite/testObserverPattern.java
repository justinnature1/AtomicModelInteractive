package application.observerAndComposite;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

class testObserverPattern {

	static int totalCount = 0;
	static int collisionCount = 0; 

	@Test
	void test() {
		Platform.startup(() -> {//launch JavaFx application 
			Stage stage = new Stage();
			try {
				start(stage);
			} catch (Exception ex) {ex.printStackTrace();}
		});		

		AlphaParticle apRight = new AlphaParticle(250,250,50,0,new NoParticles());
		apRight.setCollision(false);
		AlphaParticle apLeft = new AlphaParticle(250,250,-50,0,new NoParticles());
		apLeft.setCollision(true);
		AlphaParticle apUp = new AlphaParticle(250,250,0,-50,new NoParticles());
		apUp.setCollision(true);
		AlphaParticle apDown = new AlphaParticle(250,250,0,50,new NoParticles());
		apDown.setCollision(false);

		AlphaParticle[] alphaParticles = {apRight, apLeft, apUp, apDown};

		TabPane tabPane = new TabPane();
		CollisionData collisionData = new CollisionData(tabPane);

		//No observers registered yet.  CollisionData should not have any particles
		assertTrue(collisionData.getTotalCount() == totalCount);
		assertTrue(collisionData.getCollisionCount() == collisionCount);
		assertTrue(apRight.isActive()==true);


		//Register Observers
		apRight.registerObserver(collisionData);
		apLeft.registerObserver(collisionData);
		apUp.registerObserver(collisionData);
		apDown.registerObserver(collisionData);

		//Observers will not be notified since the particle is still on the 512x512 screen.
		assertTrue(collisionData.getTotalCount() == totalCount);
		assertTrue(collisionData.getCollisionCount() == collisionCount);
		assertTrue(apRight.isActive()==true);


		//Moving for 1 second is not enough to move the particle off the screen. Observer's aren't notified yet.
		apRight.move(1);
		assertTrue(collisionData.getTotalCount() == totalCount);
		assertTrue(collisionData.getCollisionCount() == collisionCount);
		assertTrue(apRight.isActive()==true);

		for (AlphaParticle alphaParticle: alphaParticles) {
			do {
				alphaParticle.move(1);
				//When the particle goes off the screen, it is set to inactive.  This also notifies the observers so they can 
				//count particles and collisions
				if (!alphaParticle.isActive())
					countParticle(alphaParticle);
				if (alphaParticle.getX() >= 0 
						&& alphaParticle.getX() <= 512 
						&& alphaParticle.getY() >= 0 
						&& alphaParticle.getY() <= 512) {
					assertTrue(collisionData.getTotalCount() == totalCount);
					assertTrue(collisionData.getCollisionCount() == collisionCount);
					assertTrue(alphaParticle.isActive()==true);

				} else {
					assertTrue(collisionData.getTotalCount() == totalCount);
					assertTrue(collisionData.getCollisionCount() == collisionCount);
					assertTrue(alphaParticle.isActive()==false);
				}
			} while (alphaParticle.isActive()); //While the particle is on the screen still.
		}
	}

	private void countParticle(AlphaParticle alphaParticle) {
		totalCount++;
		if (alphaParticle.isCollision())
			collisionCount++;
	}


	public void start(Stage primaryStage) {
		//initialize JavaFX so TabPane can be created.
	}

}
