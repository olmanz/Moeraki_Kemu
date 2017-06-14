package de.htwg.se.moerakikemu.controller;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.util.observer.IObserverSubject;

public interface IController extends IObserverSubject {

	/**
	 * Returns the name of the player that occupies the field with the coordinates.
	 *
	 * @param x X-Coordinate.
	 * @param y Y-Coordinate.
	 * @return The name of the player or an empty String. 
	 */
	String getIsOccupiedByPlayer(final int x, final int y);
	
	/**
	 * Returns the length of one edge of the game field.
	 *
	 * @return Number > 0.
	 */
	int getEdgeLength();
	
	/**
	 * Occupation of a Spot by a player. Returns -1 if the Spot is already occupied.
	 * @param xCoordinate X coordinate of the spot beginning from 1 to edgeLength.
	 * @param yCoordinate Y coordinate of the spot beginning from 1 to edgeLength.
	 * @return returns 0 if the current player occupied the field and got points;
	 * -1 if the spot already was occupied.
	 */
	int occupy(final int xCoordinate, final int yCoordinate);
	
	/**
	 * Returns the name of the player if a player has won, else an empty String.
	 * @return A String, not null.
	 */
	String getWinner();
	
	/**
	 * Tests if there is a winner:
	 * @return True if there is a winner, false when there isn't one;
	 */
	boolean testIfWinnerExists();
	
	/**
	 * Test if the game is over os must be terminated.
	 * @return true if there is a winner, false when there isn't one.
	 */
	boolean testIfEnd();
	
	/**
	 * set if the game ends
	 * @param end True if the game must end.
	 */
	void setEnd(final boolean end);

	/**
	 * Returns the current State for the Controller to determine the correct UI response.
	 * @return A constant from the enum State.
	 */
	State getState();
	
	/**
	 * Reset all values without the names of the players to zero.
	 */
	void newGame();

	/**
	 * Added for web-controller.
	 */
	String getPlayer1Name();
	String getPlayer2Name();
	
	/**
	 * Load a field from the database
	 * @param fieldId
	 */
	void loadFromDB(String fieldId);
	
	/**
	 * Save a field to the database
	 */
	void saveToDB();
	
	/**
	 * Check whether the actual field is already in database
	 * @return true if the entry exists, false if not
	 */
	boolean containsActualFieldDB();
	
	/**
	 * Generate a number of fields and save them to the database
	 * @param number
	 */
	void generateFieldToDB(int number);
	
	/**
	 * Generate a number of fields and save them to the database
	 * @return the name of the field
	 */
	String getFieldName();
	
	/**
	 * Generate a number of fields and save them to the database
	 * @param the name of the field
	 */
	void setFieldName(String name);
	
	IField getField();

	void deleteFromDB(String id);

	String[][] getRowDataAll();

	String fieldToHtml();
}
