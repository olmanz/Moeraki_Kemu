package de.htwg.se.moerakikemu.controller;

public interface IController extends IViewsSubject {

	/**
	 * Returns the name of the player that occupies the field with the coordinates.
	 *
	 * @param x X-Coordinate.
	 * @param y Y-Coordinate.
	 * @return The name of the player or an empty String. 
	 */
	String getIsOccupiedByPlayer(int x, int y);
	
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
	 * @param playerName Name of the Player.
	 * @return returns 0 if the current player occupied the field and got points;
	 * -1 if the spot already was occupied.
	 */
	int occupy(int xCoordinate, int yCoordinate);
	
	/**
	 * returns if a player has won
	 * @return A String, not null
	 */
	String getWinner();
	
	/**
	 * test if there is a winner
	 * @return true if there is a winner, false when there isnt one;
	 */
	boolean testIfWinnerExists();
	
	/**
	 * return the boolean value "end";
	 * @param a boolean Value
	 */
	void setEnd(boolean end);	
}
