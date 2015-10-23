package de.htwg.se.Modellayer;

public class Field {

	private Spot[][] array;
	
	/**
	 * Creates a field with the default size of 6 by 6.
	 */
	public Field() {
		this(6);
	}
	
	/**
	 * Generic constructor for an arbitrary field size.
	 * @param edgeLength Length of an edge of the square field.
	 */
	private Field(int edgeLength) {
		array = new Spot[edgeLength][edgeLength];
		for (int i = 0; i < edgeLength; i++) {
			for (int j = 0; j < edgeLength; j++) {
				array[i][j] = new Spot();
			}
		}
	}
	
	/**
	 * Determines if one special spot is occupied.
	 *
	 * @param Xcoordinate X coordinate of the spot.
	 * @param Ycoordinate Y coordinate of the spot.
	 * @return True if the spot is occupied, else false.
	 */
	public boolean getIsOccupied(int Xcoordinate, int Ycoordinate) {
		return array[Xcoordinate][Ycoordinate].isOccupied();
	}
	
	/**
	 * Occupation of a Spot by a player. Returns -1 if the Spot is already occupied.
	 * @param Xcoordinate X coordinate of the spot.
	 * @param Ycoordinate Y coordinate of the spot.
	 * @param playerName Name of the Player.
	 * @return The number of points (0 - 4) or -1 if the Spot is occupied.
	 */
	public int occupy(int Xcoordinate, int Ycoordinate, String playerName) {
		boolean occupationSucceeded = array[Xcoordinate][Ycoordinate].occupy(playerName);
		
		if (occupationSucceeded) {
			int points = 0;
			// Calculate points and check if 4 spots of square selected
			return points;
		} else {
			return -1;
		}
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
	 * @return The amount of points to add to the current player.
	 */
	private int calcPoints(final int xMin, final int xMax,
						   final int yMin, final int yMax,
						   final String playerName) {
		int points = 0;
		
		if(array[xMin][yMin].getOccupiedByPlayer().equals(playerName)) {
			points++;
		}
		if(array[xMin][yMax].getOccupiedByPlayer().equals(playerName)) {
			points++;
		}
		if(array[xMax][yMin].getOccupiedByPlayer().equals(playerName)) {
			points++;
		}
		if(array[xMax][yMax].getOccupiedByPlayer().equals(playerName)) {
			points++;
		}
		
		return points;
	}
	
	// Determine lines to edge of field
	
}
