package de.htwg.se.moerakikemu.controller;

import de.htwg.se.moerakikemu.modellayer.IPlayer;

public interface IControllerPlayer {

	public void setName(String player1name, String player2name);
	
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
	 * adds a Point to Player 1
	 * @param A String, not null
	 */
	void addAPointPlayer1();
	
	/**
	 * adds a Point to Player 2
	 * @param A String, not null
	 */
	void addAPointPlayer2();
	
	/**
	 * Return the current Player
	 * @return A String, not null
	 */
	String getCurrentPlayerName();
	
	/**
	 * Determines the player that has the next turn.
	 * If no one is the current player then player1 begins.
	 * Else player1 and player2 are altering the next player.
	 */
	void selectNextPlayer();
	
	
}
