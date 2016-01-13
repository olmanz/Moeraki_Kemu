package de.htwg.se.util.observer;

public interface IObserverSubject {
	
	/**
	 * Adds an Observer to be notified on the Subject's change.
	 * @param newObserver The observer to be added.
	 */
	public void attatch(ObserverObserver newObserver);
	
	/**
	 * Removes an Observer that used to observe the Subject.
	 * @param observer The Observer to be removed.
	 */
	public void detatch(ObserverObserver observer);
	
	/**
	 * Notifies the Observers that there happened a change.
	 */
	public void notifyObservers();
	
}
