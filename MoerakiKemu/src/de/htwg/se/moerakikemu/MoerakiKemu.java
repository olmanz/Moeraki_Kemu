package de.htwg.se.moerakikemu;


import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;
import de.htwg.se.moerakikemu.controller.controllerimpl.Controller;
import de.htwg.se.moerakikemu.controller.controllerimpl.ControllerPlayer;
import de.htwg.se.moerakikemu.view.UserInterface;
import de.htwg.se.moerakikemu.view.viewimpl.TextUI;
import de.htwg.se.moerakikemu.view.viewimpl.gui.GUI;
import de.htwg.se.util.observer.ObserverObserver;
import de.htwg.se.util.observer.IObserverSubject;


public class MoerakiKemu {

	private MoerakiKemu() {
		// Private Constructor because it must not be used elsewhere
	}

	// Module for Dependency Injection with GoogleGuice
	
	
	/**
	 * Starts the game with TUI, GUI.
	 *
	 * @param args Unused parameters.
	 */
	public static void main(String[] args) {
		IControllerPlayer playerController = new ControllerPlayer();
		IController controller = new Controller(12, playerController);
	
		UserInterface interfaces[];
		interfaces = new UserInterface[2];
		interfaces[0] = new TextUI(controller, playerController);
		interfaces[1] = new GUI(controller, playerController);
		for (int i = 0; i < interfaces.length; i++) {
			((IObserverSubject) controller).attatch((ObserverObserver) interfaces[i]);
			interfaces[i].drawCurrentState();
		}
		
		boolean finished = false;
		while (!finished) {
			/*
			for (int i = 0; i < interfaces.length; i++) {
				interfaces[i].update();
			}*/
			finished = controller.testIfWinnerExists();
			if(finished){
				((TextUI)interfaces[0]).Quit();
				((GUI)interfaces[1]).Quit();
			}
		}
	}

}
