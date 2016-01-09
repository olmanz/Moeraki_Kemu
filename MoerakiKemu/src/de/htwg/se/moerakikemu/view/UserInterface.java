package de.htwg.se.moerakikemu.view;

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
	
	/**
	 * This prints a a message or popup to display
	 * that the game will get sterminated.
	 */
	public void Quit();

	/**
	 * This updates the GUI and the TUI
	 */
	void update();
}
