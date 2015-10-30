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
	public Field(int edgeLength) {
		this.edgeLength = edgeLength;
		array = new Spot[edgeLength][edgeLength];
		for (int i = 0; i < edgeLength; i++) {
			for (int j = 0; j < edgeLength; j++) {
				array[i][j] = new Spot();
			}
		}
	}

	/**
	 * Determines if one special spot is occupied from a specific player.
	 *
	 * @param Xcoordinate X coordinate of the spot.
	 * @param Ycoordinate Y coordinate of the spot.
	 * @return The Name of the player if one of the player has occupied the spot or an empty String if not occupied.
	 */	
	public String getIsOccupiedFrom(int Xcoordinate, int Ycoordinate) {
		if(array[Xcoordinate][Ycoordinate].isOccupied()){
			return array[Xcoordinate][Ycoordinate].getOccupiedByPlayer();
		}
		return "";
	}
	
	// Determine lines to edge of field
	
}
