package de.htwg.se.moeraki_kemu;

import java.util.Scanner;

import de.htwg.se.moeraki_kemu.controller.Controller;
import de.htwg.se.moeraki_kemu.view.TextUI;

public class MoerakiKemu {

	/**
	 * Starts the game with TUI, GUI.
	 *
	 * @param args Unused parameters.
	 */
	public static void main(String[] args) {
		// TODO Replace Comments with code

		Controller controller = new Controller(12);

		TextUI tui = new TextUI(controller);
		// continue until the user decides to quit
		
		while (true) {
			tui.drawCurrentState();
			tui.processInputLine();
		}

	}

}
