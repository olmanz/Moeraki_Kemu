package de.htwg.se.MoerakiKemu.View;

import de.htwg.se.MoerakiKemu.Controller.Controller;

public class TextUI {

	Controller myController;

	public TextUI(Controller controller) {
		myController = controller;
	}

	/**
	 * Parses the input from the user and call controller things.
	 * 
	 * @param line Input from the user.
	 * @return
	 */
	public boolean processInputLine(final String line) {
		System.out.println("Spieler x, was tun Sie?");
		printOptions();
		System.out.print("\t>> ");
		// TODO: Implement
		
		// Setzen
		
		// Was noch?
		
		return false;
	}
	
	private void printOptions() {
		System.out.println("1) Setzen");
		System.out.println("2) Aufgeben");
	}
	
	public String queryPlayerName() {
		// Read name from console
		return "";
	}
	
	/**
	 * Draws all Spots as squares with an Player-identifying character in it.
	 * ------
	 * |1||2|...
	 * ------
	 *    .
	 *    .
	 *    .
	 * Player 1: x points
	 * Player 2: x points
	 */
	public void drawCurrentState() {
		int edgeLength = myController.getEdgeLength();
		
		printColumnIdentifiers(edgeLength);
		for(int i = 0; i < edgeLength; i++) {
			printLine(edgeLength);
			System.out.print(i + 1);
			for(int j = 0; j < edgeLength; j++) {
				String playerString = myController.getIsOccupiedByPlayer(i, j);
				char id = playerString.isEmpty() ? ' ' : playerString.charAt(playerString.length() - 1);
				drawSpot(id);
			}
			System.out.println();
		}
		printLine(edgeLength);
		System.out.println("Player 1: " + myController.getPlayer1Points() + "points");
		System.out.println("Player 2: " + myController.getPlayer2Points() + "points");
	}
	
	/**
	 * Draws a Spot with the Player identifier (i.e. number).
	 *
	 * @param playerChar Identifier for the player or ' ' if not occupied by player.
	 */
	private void drawSpot(final char playerChar) {
		System.out.print("|" + playerChar + "|");
	}
	
	/**
	 * Calculates the offset for the game field according to the length of the
	 * number representing the edge length.
	 *
	 * @param edgeLength Length of the edge of the game field.
	 * @return The empty spaces for offset as String, not null.
	 */
	private String offset(int edgeLength) {
		int numDigits = String.valueOf(edgeLength).length();

		StringBuilder offsetBuilder = new StringBuilder();
		for (int l = 0; l < numDigits; l++) {
			offsetBuilder.append(" ");
		}
		return offsetBuilder.toString();
	}
	
	/**
	 * Prints a horizontal line for separating lines of Spots.
	 * 
	 * @param edgeLength Number of Spots per edge.
	 */
	private void printLine(int edgeLength) {
		System.out.print(offset(edgeLength));
		for(int i = 0; i < edgeLength; i++) {
			System.out.print("---");
		}
		System.out.println();
	}
	
	/**
	 * Prints a line with numbers above the game field that identifies the collumns.
	 * @param edgeLength Length of the game field.
	 */
	private void printColumnIdentifiers(final int edgeLength) {
		StringBuilder headlineBuilder = new StringBuilder(offset(edgeLength));
		for (int i = 1; i <= edgeLength; i++) {
			headlineBuilder.append(" ").append(i).append(" ");
		}
		
		System.out.println(headlineBuilder.toString());
	}
}
