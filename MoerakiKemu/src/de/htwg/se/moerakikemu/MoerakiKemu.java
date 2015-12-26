package de.htwg.se.moerakikemu;


import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;
import de.htwg.se.moerakikemu.controller.controllerimpl.Controller;
import de.htwg.se.moerakikemu.controller.controllerimpl.ControllerPlayer;
import de.htwg.se.moerakikemu.view.viewimpl.TextUI;
import de.htwg.se.moerakikemu.view.viewimpl.gui.GUI;


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

		TextUI tui = new TextUI(controller, playerController);
		GUI myGui = new GUI(controller);

		boolean finished = false;
		while (!finished) {
			myGui.drawCurrentState();
			tui.drawCurrentState();
			tui.processInputLine();
			finished = controller.testIfWinnerExists();
			if(finished)
				tui.Quit();
		}

	}

}
