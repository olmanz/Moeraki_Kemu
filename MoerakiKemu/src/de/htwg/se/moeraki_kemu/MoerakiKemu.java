package de.htwg.se.moeraki_kemu;

import de.htwg.se.moeraki_kemu.controller.Controller;
import de.htwg.se.moeraki_kemu.view.TextUI;

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
		}

	}

}
