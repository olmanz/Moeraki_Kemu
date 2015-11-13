package de.htwg.se.moeraki_kemu.view;

import de.htwg.se.moeraki_kemu.controller.Controller;

public class TextUI {

	Controller myController;

	public TextUI(Controller controller) {
		myController = controller;
	}

	public boolean processInputLine(final String line) {
		
		return false;
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
		
		for(int i = 0; i < edgeLength; i++) {
			printLine(edgeLength);
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
	 * Prints a horizontal line for separating lines of Spots.
	 * 
	 * @param edgeLength Number of Spots per edge.
	 */
	private void printLine(int edgeLength) {
		for(int i = 0; i < edgeLength; i++) {
			System.out.print("---");
		}
		System.out.println();
	}
}
