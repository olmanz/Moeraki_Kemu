package de.htwg.se.moeraki_kemu.view;

public interface UserInterface {

	/**
	 * Print game field and points for each player.
	 */
	void drawCurrentState();

	/**
	 * Get Player names from UI.
	 * Set the playername in the controllerclass
	 */
	void queryPlayerName();

	/**
	 * Prints a message text on the respective GUI.
	 * @param msg Message to print.
	 */
	void printMessage(String msg);
}
