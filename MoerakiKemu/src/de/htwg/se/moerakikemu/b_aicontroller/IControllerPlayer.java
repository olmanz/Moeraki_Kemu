package de.htwg.se.moerakikemu.b_aicontroller;

import de.htwg.se.moerakikemu.c_aimodellayer.IPlayer;

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
	
	void addAPoint(IPlayer player);
	
	String getCurrentPlayerName();
	
	/**
	 * Determines the player that has the next turn.
	 * If no one is the current player then player1 begins.
	 * Else player1 and player2 are altering the next player.
	 */
	void selectNextPlayer();
	
	
}
