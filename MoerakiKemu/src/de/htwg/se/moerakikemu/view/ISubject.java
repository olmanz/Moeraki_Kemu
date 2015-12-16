package de.htwg.se.moerakikemu.view;

/**
 * Object that informs the observers about changes.
 */
public interface ISubject {
	public void attatch(IObserver newObserver);
	public void detatch(IObserver observer);
	public void notifyObservers();
}
