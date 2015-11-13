package de.htwg.se.moeraki_kemu.modellayer;

public class Field {
	private int edgeLength;
	private int occupiedSpots;
	private Spot[][] array;
	
	/**
	 * Generic constructor for an arbitrary field size.
	 * @param edgeLength Length of an edge of the square field.
	 */
	public Field(int edgeLength) throws IllegalArgumentException {
		if(edgeLength < 1) {
			throw new IllegalArgumentException("Edgelength too small: " + edgeLength);
		}
		this.edgeLength = edgeLength;
		array = new Spot[edgeLength][edgeLength];
		for (int i = 0; i < edgeLength; i++) {
			for (int j = 0; j < edgeLength; j++) {
				array[i][j] = new Spot();
			}
		}
		occupiedSpots = 0;
	}
	
	/**
	 * Returns the length of an edge of the square game field.
	 *
	 * @return Number > 0
	 */
	public int getEdgeLength() {
		return this.edgeLength;
	}

	/**
	 * Determines whether the Spot is occupied.
	 *
	 * @param x X-coordinate of the spot.
	 * @param y Y-coordinate of the spot.
	 * @return True if the Spot is occupied, else false.
	 */
	public boolean getIsOccupied(int x, int y) {
		return !getIsOccupiedFrom(x, y).isEmpty();
	}
	
	/**
	 * Occupies a Spot if not already occupied.
	 *
	 * @param x X-coordinate of the spot.
	 * @param y Y-coordinate of the spot.
	 * @param playerName Name of the player to occupy the spot.
	 * @return True if the spot is newly occupied, false if the Spot was already occupied.
	 */
	public boolean occupy(int x, int y, final String playerName) {
		if (array[x][y].isOccupied()) {
			return false;
		} else {
			array[x][y].occupy(playerName);
			occupiedSpots++;
			return true;
		}
	}
	
	/**
	 * Determines if one special spot is occupied from a specific player.
	 *
	 * @param xCoordinate X coordinate of the spot.
	 * @param yCoordinate Y coordinate of the spot.
	 * @return The Name of the player if one of the player has occupied the spot or an empty String if not occupied.
	 */	
	public String getIsOccupiedFrom(int xCoordinate, int yCoordinate) {
		if(array[xCoordinate][yCoordinate].isOccupied()){
			return array[xCoordinate][yCoordinate].getOccupiedByPlayer();
		}
		return "";
	}
	
	public boolean isFilled(){
		if(occupiedSpots == (edgeLength * edgeLength)){
			return true;
		} else {
			return false;
		}
	}
	
	// Determine lines to edge of field
	
}
