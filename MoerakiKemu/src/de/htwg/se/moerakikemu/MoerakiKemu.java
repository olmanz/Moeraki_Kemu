package de.htwg.se.moerakikemu;

import de.htwg.se.moerakikemu.aview.TextUI;
import de.htwg.se.moerakikemu.controller.Controller;

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
		Controller controller = new Controller(12);

		TextUI tui = new TextUI(controller);

		boolean finished = false;
		while (!finished) { 
			tui.drawCurrentState();
			tui.processInputLine();
			finished = controller.testIfWinnerExists();
			if(finished)
				tui.Quit();
		}

	}

}
