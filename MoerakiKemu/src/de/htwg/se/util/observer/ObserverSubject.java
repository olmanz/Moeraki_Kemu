package de.htwg.se.util.observer;

import java.util.ArrayList;
import java.util.List;


/**
 * Object that informs the observers about changes.
 * Controller?!
 */
public class ObserverSubject implements IObserverSubject {
	protected List<ObserverObserver> observers;
	
	public ObserverSubject() {
		observers = new ArrayList<ObserverObserver>();
	}

	@Override
	public void attatch(ObserverObserver newObserver) {
		observers.add(newObserver);
	}

	@Override
	public void detatch(ObserverObserver observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers() {
		for (ObserverObserver currentObserver : observers) {
			currentObserver.notify();
		}
	}
	
}
