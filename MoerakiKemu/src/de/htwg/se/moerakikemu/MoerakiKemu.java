package de.htwg.se.moerakikemu;


import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.moerakikemu.controller.ControllerModuleWithController;
import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.controllerimpl.Controller;
import de.htwg.se.moerakikemu.view.UserInterface;
import de.htwg.se.moerakikemu.view.viewimpl.TextUI;
import de.htwg.se.moerakikemu.view.viewimpl.gui.GUI;
import de.htwg.se.util.observer.ObserverObserver;
import de.htwg.se.util.observer.IObserverSubject;


public class MoerakiKemu {

	private MoerakiKemu() {
		// Private Constructor because it must not be used elsewhere
	}

	/**
	 * Starts the game with TUI, GUI.
	 *
	 * @param args Unused parameters.
	 */
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ControllerModuleWithController());
		
		IController controller = injector.getInstance(Controller.class);
	
		UserInterface[] interfaces;
		interfaces = new UserInterface[2];
		interfaces[0] = injector.getInstance(TextUI.class);
		interfaces[1] = injector.getInstance(GUI.class);

		for (int i = 0; i < interfaces.length; i++) {
			((IObserverSubject) controller).attatch((ObserverObserver) interfaces[i]);
			interfaces[i].drawCurrentState();
		}

		// Used to query Player names
		((ObserverObserver) interfaces[1]).update();
		
		boolean finished = false;
		while (!finished) {
			finished = controller.testIfEnd();
			interfaces[0].update();
			interfaces[1].update();
		}
		
		for (UserInterface ui : interfaces) {
			ui.quit();
		}
	}

}
