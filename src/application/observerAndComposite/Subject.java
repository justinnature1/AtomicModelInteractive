package application.observerAndComposite;

/**
 * The interface implemented by subjects that need to track and notify observers.
 * @author Justin Keller
 */
public interface Subject {
	public void registerObserver (Observer o);
	public void removeObserver (Observer o);
	public void notifyObservers();
}
