package de.htwg.se.moerakikemu;

import de.htwg.se.moerakikemu.controller.Controller;
import de.htwg.se.moerakikemu.view.TextUI;

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

		while (true) {
			tui.drawCurrentState();
			tui.processInputLine();
			if(tui.testQuit()){
				break;
			}
		}

	}

}
