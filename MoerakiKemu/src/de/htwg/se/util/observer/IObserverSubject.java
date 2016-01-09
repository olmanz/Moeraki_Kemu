package de.htwg.se.util.observer;

public interface IObserverSubject {
	public void attatch(ObserverObserver newObserver);
	public void detatch(ObserverObserver observer);
	public void notifyObservers();
	
}
