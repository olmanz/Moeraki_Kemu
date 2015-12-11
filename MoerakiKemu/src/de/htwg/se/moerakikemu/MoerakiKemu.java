package de.htwg.se.moerakikemu;

import de.htwg.se.moerakikemu.a_view.TextUI;
import de.htwg.se.moerakikemu.b_aicontroller.*;

import de.htwg.se.moerakikemu.b_controller.*;

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
		IControllerPlayer playerController = new ControllerPlayer();
		IController controller = new Controller(12, playerController);

		TextUI tui = new TextUI(controller, playerController);

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
