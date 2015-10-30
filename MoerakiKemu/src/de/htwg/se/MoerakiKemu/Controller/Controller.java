package de.htwg.se.MoerakiKemu.Controller;

import de.htwg.se.MoerakiKemu.Modellayer.Field;
import de.htwg.se.MoerakiKemu.Modellayer.Player;

public class Controller {

	private Player player1;
	private Player player2;
	
	private int pointsPlayer1;
	private int pointsPlayer2;
	
	private Field gameField;
	private int fieldLength;
	
	public Controller(final String player1Name, final String player2Name, final int fieldLength) {
		this.fieldLength = fieldLength;
		gameField = new Field(fieldLength);
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);
	}
	
	/**
	 * Occupation of a Spot by a player. Returns -1 if the Spot is already occupied.
	 * @param Xcoordinate X coordinate of the spot.
	 * @param Ycoordinate Y coordinate of the spot.
	 * @param playerName Name of the Player.
	 * @return The number of points (0 - 4) or -1 if the Spot is occupied.
	 */
	private int occupy(int Xcoordinate, int Ycoordinate, String playerName) {
		if(gameField.getIsOccupiedFrom(Xcoordinate, Ycoordinate) != "leer"){
			return -1;
		}
		int points = 0;
		
		// Calculate points and check if 4 spots of square selected
		int xMin = Xcoordinate - 1;
		int xMax = Xcoordinate + 1;
		int yMin = Ycoordinate - 1;
		int yMax = Ycoordinate + 1;
			
		return points;

	}
	
	/**
	 * Calculates the points after a player occupies a spot on the field
	 * determined by the spots around the currently set one in the square.
	 * 
	 * @param xMin Lower x border of the square.
	 * @param xMax Upper x border of the square.
	 * @param yMin Lower y border of the square.
	 * @param yMax Upper y border of the square.
	 * @param playerName Name of the player that occupied the field.
	 * @return The amount of points (0 - 4) for the current player.
	 */
//	private int setSpot(final int XCoordinate, final int YCoordinate,
//						   final int xMin, final int xMax,
//						   final int yMin, final int yMax,
//						   final String playerName1, final String playerName2) {
//		
//
//	
//	}
}
