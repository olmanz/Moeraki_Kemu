package de.htwg.se.MoerakiKemu.Modellayer;

public class Field {
	private int edgeLength;
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
		this.edgeLength = edgeLength;
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
	 * @return False if the spot is occupied, and false if the Spot is free.
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
			int xMin = Xcoordinate - 1;
			int xMax = Xcoordinate + 1;
			int yMin = Ycoordinate - 1;
			int yMax = Ycoordinate + 1;
			
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
	 * @return The amount of points (0 - 4) for the current player.
	 */
	private int calcPoints(final int XCoordinate, final int YCoordinate,
						   final int xMin, final int xMax,
						   final int yMin, final int yMax,
						   final String playerName) {
		int pointsPlayerOne = 0;
		int pointsPlayerTwo = 0;
		int counterPlayerOne = 1;		//Player who occupied this Spot
		int counterPlayerTwo = 0;		//Enemieplayer
		
//		if(pointIsOccupied(yMin, xMin)){
//			
//		}
		
		
		
		
		// TODO: Only calculate points if all 4 Spots of a squres are set. The player with 3 / 4 Spots gets the points.
		
		return pointsPlayerOne;
	}
	
	
	private String pointIsOccupied(int XCoordinate, int YCoordinate){
		if(array[XCoordinate][YCoordinate].isOccupied()){
			return array[XCoordinate][YCoordinate].getOccupiedByPlayer();
		} else {
			return " ";
		}
	}
	
	// Determine lines to edge of field
	
}
