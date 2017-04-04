package de.htwg.se.moerakikemu.modellayer.modellayerimpl;

import de.htwg.se.moerakikemu.modellayer.ISpot;

public class Spot implements ISpot {

	/**
	 * Status of the Spot.
	 * True if player set token in this field.
	 */
	private boolean occupied;

	/**
	 * Name of the player of with token on this field
	 */
	private String occupiedByPlayer = "leer";
	
	/**
	 * Creates a new unoccupied Spot.
	 */
	public Spot() {
		occupied = false;
	}
	
	/**
	 * Occupation of a Spot from a Player with a token.
	 *
	 * @param playerName Name of the Player.
	 * @return True if the player successfully occupied by the player; false if
	 * another player occupied the Spot.
	 */
	public boolean occupy(final String playerName) {
		if (this.isOccupied()) {
			return false;
		} else {
			this.occupiedByPlayer = playerName;
			occupied = true;
			return true;
		}
	}

	/**
	 * Determines if the Spot is occupied.
	 *
	 * @return True of the Spot is occupied by a Player; false if not occupied.
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Returns the name of the occupying Player as String.
	 *
	 * @return Name of the player.
	 */
	public String getOccupiedByPlayer() {
		return occupiedByPlayer;
	}

}
