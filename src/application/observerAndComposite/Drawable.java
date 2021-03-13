package application.observerAndComposite;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface used by items that can be drawn on the Graphics Context
 * @author Justin Keller
 */
public interface Drawable {
	public void draw(GraphicsContext gc);
}
