package de.htwg.se.moerakikemu.b_aicontroller;

public interface IController {

	/**
	 * Determines the player that has the next turn.
	 * If no one is the current player then player1 begins.
	 * Else player1 and player2 are altering the next player.
	 */
	public void selectNextPlayer();
	
	/**
	 * Returns the name of the current player.
	 * @return String that may be empty, not null.
	 */
	String getCurrentPlayerName();
	
	/**
	 * Set the names for both player1 and player2.
	 * @param player1name Name for player1.
	 * @param player2name Name for player2.
	 */
	void setName(String player1name, String player2name);
	
	/**
	 * Returns the name of player1.
	 *
	 * @return A String, not null.
	 */
	String getPlayer1Name();
	
	/**
	 * Returns the name of player2.
	 *
	 * @return A String, not null.
	 */
	String getPlayer2Name();
	
	/**
	 * Returns the points of the first player.
	 *
	 * @return Amount of points, 0 or more.
	 */
	int getPlayer1Points();
	
	/**
	 * Returns the points of the second player.
	 *
	 * @return Amount of points, 0 or more.
	 */
	int getPlayer2Points();
	
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
	 * @return the player who has won;
	 */
	String getWinner();
	
	/**
	 * returns the points of a player
	 * @param playerName
	 * @return the points of player one or player two or if there is no player with the given name, this method returns 0
	 */
	int getPointsOfPlayer(String playerName);
	
	/**
	 * test if there is a winner
	 * @return true if there is a winner, false when there isnt one;
	 */
	boolean testIfWinnerExists();
	
	/**
	 * return the boolean value "end";
	 * @param end
	 */
	void setEnd(boolean end);	
}
