package de.htwg.se.MoerakiKemu;

import java.util.Scanner;

import de.htwg.se.MoerakiKemu.Controller.Controller;
import de.htwg.se.MoerakiKemu.View.TextUI;

public class MoerakiKemu {

	static Scanner scanner;

	/**
	 * Starts the game with TUI, GUI.
	 *
	 * @param args Unused parameters.
	 */
	public static void main(String[] args) {
		// TODO Replace Comments with code

		Controller controller = new Controller();

		TextUI tui = new TextUI(controller);
		tui.drawCurrentState();
		// continue until the user decides to quit
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu) {
		    continu = tui.processInputLine(scanner.next());
		}

	}

}
