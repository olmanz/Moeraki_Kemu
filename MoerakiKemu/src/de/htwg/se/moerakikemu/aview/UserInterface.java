package de.htwg.se.moerakikemu.aview;

public interface UserInterface {

	/**
	 * Print game field and points for each player.
	 */
	void drawCurrentState();

	/**
	 * Set the player name in the controller
	 */
	void queryPlayerName();

	/**
	 * Prints a message text on the respective GUI.
	 * @param msg Message to print.
	 */
	void printMessage(String msg);
}
