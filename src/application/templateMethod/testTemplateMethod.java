package application.templateMethod;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import application.observerAndComposite.AlphaParticle;
import application.observerAndComposite.Nucleus;
import application.observerAndComposite.Particle;
import application.observerAndComposite.ParticleComponent;
import application.observerAndComposite.Particles;

class testTemplateMethod {

	@Test
	void test() {
		ParticleComponent nuclei = new Particles();
		ParticleComponent alphaParticles = new Particles();

		ParticleComponent particles = new Particles();
		particles.add(nuclei);
		particles.add(alphaParticles);

		double nucleusX = 250;
		double nucleusY = 250;
		ParticleComponent nucleusNoMove = new Nucleus(nucleusX, nucleusY, false);
		nuclei.add(nucleusNoMove);
		assertTrue("Stationary".equalsIgnoreCase(getMove(nucleusNoMove).getMoveName()));

		//One second of elapsed time.
		nuclei.move(1);

		//A nucleus that uses the Stationary class, should stay in the same location even if the move method is called.
		assertTrue(nucleusX == nuclei.getChild(0).getX());
		assertTrue(nucleusY == nuclei.getChild(0).getY());	

		//Alpha Particle to the left of the nucleus that causes leftward acceleration
		ParticleComponent particleLeft = new AlphaParticle(220,250,0,0,nuclei);
		alphaParticles.add(particleLeft);

		//Alpha Particle above the nucleus that causes upward acceleration
		ParticleComponent particleUp = new AlphaParticle(250,220,0,0,nuclei);
		alphaParticles.add(particleUp);

		//Alpha Particle to the right of the nucleus that causes rightward acceleration
		ParticleComponent particleRight = new AlphaParticle(280,250,0,0,nuclei);
		alphaParticles.add(particleRight);

		//Alpha Particle underneath the nucleus that causes downward acceleration
		ParticleComponent particleDown = new AlphaParticle(250,280,0,0,nuclei);
		alphaParticles.add(particleDown);

		//Alpha Particle to the 'NW' of the nucleus that causes upward and leftward acceleration
		ParticleComponent particleUpLeft = new AlphaParticle(220,220,0,0,nuclei);
		alphaParticles.add(particleUpLeft);

		//Alpha Particle to the 'SE' of the nucleus that causes downward and rightward acceleration
		ParticleComponent particleDownRight = new AlphaParticle(280,280,0,0,nuclei);
		alphaParticles.add(particleDownRight);

		//Alpha Particle to the 'NE' of the nucleus that causes upward and rightward acceleration
		ParticleComponent particleUpRight = new AlphaParticle(280,220,0,0,nuclei);
		alphaParticles.add(particleUpRight);

		//Alpha Particle to the 'SW' of the nucleus that causes downward and leftward acceleration
		ParticleComponent particleDownLeft = new AlphaParticle(220,280,0,0,nuclei);
		alphaParticles.add(particleDownLeft);

		//Alpha Particle to the 'SW' of the nucleus that moves just passes by the nucleus
		ParticleComponent particleMovingUpRight = new AlphaParticle(0,512,20,-21,nuclei);
		alphaParticles.add(particleMovingUpRight);

		//Alpha Particle to the 'SW' of the nucleus that passes by the nucleus
		ParticleComponent particleMovingDownLeft = new AlphaParticle(512,0,-21,20,nuclei);
		alphaParticles.add(particleMovingDownLeft);

		Iterator<ParticleComponent> it = alphaParticles.getIterator();
		while (it.hasNext()) {
			assertTrue("ElectricalCollision".equalsIgnoreCase(getMove(it.next()).getMoveName()));
		}

		it = alphaParticles.getIterator();
		double prevXSpeed, prevYSpeed;
		ParticleComponent ap;
		Move particleMove;
		boolean correctXMove;
		boolean correctYMove;
		boolean correctMove;
		while (it.hasNext()) {
			ap = it.next();
			for (int i = 0; i<30; i++) { //Move particle for 3 seconds (30 x .1 seconds)
				correctXMove = correctYMove = false;
				particleMove = getMove(ap);
				prevXSpeed = particleMove.xSpeed;
				prevYSpeed = particleMove.ySpeed;
				ap.move(.1); // move the particle for an elapsed time of .1 seconds
				if (ap.getX() < nucleusNoMove.getX())
					correctXMove = (prevXSpeed > particleMove.xSpeed);
				else if (ap.getX() > nucleusNoMove.getX())
					correctXMove = (prevXSpeed < particleMove.xSpeed);
				else
					correctXMove = (prevXSpeed == particleMove.xSpeed);

				if (ap.getY() < nucleusNoMove.getY())
					correctYMove = (prevYSpeed > particleMove.ySpeed);
				else if (ap.getY() > nucleusNoMove.getY())
					correctYMove = (prevYSpeed < particleMove.ySpeed);
				else
					correctYMove = (prevYSpeed == particleMove.ySpeed);
				correctMove = (correctXMove && correctYMove);
				assertTrue(correctMove);
			}
		}

		ParticleComponent nucleusPath = new Nucleus(nucleusX, nucleusY, true);
		assertTrue("Path".equalsIgnoreCase(getMove(nucleusPath).getMoveName()));

		//One second of elapsed time.
		nucleusPath.move(1);

		//A nucleus that uses the Path class, should move left and right (a change in x) but not up and down (no change in y).
		assertTrue(nucleusX != nucleusPath.getX());
		assertTrue(nucleusY == nucleusPath.getY());	

		
	}

	private Move getMove(ParticleComponent particle) {
		return ((Particle)particle).getMove();
	}

}
