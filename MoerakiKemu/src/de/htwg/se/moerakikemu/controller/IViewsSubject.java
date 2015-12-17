package de.htwg.se.moerakikemu.controller;

import de.htwg.se.moerakikemu.view.IViewsObserver;

/**
 * Object that informs the observers about changes.
 * Controller?!
 */
public interface IViewsSubject {
	public void attatch(IViewsObserver newObserver);
	public void detatch(IViewsObserver observer);
	public void notifyObservers();
}
