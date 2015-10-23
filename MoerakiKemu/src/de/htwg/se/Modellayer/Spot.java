package de.htwg.se.Modellayer;

public class Spot {

	private boolean occupied;			// True if player set token in this field
	private String occupiedByPlayer;	// Name of the player of with token on this field
	
	public Spot() {
		occupied = false;
	}
	
	public boolean occupy(final String playerName) {
		if (this.isOccupied()) {
			return false;
		} else {
			this.occupiedByPlayer = playerName;
			return occupied = true;
		}
	}

	public boolean isOccupied() {
		return occupied;
	}

	public String getOccupiedByPlayer() {
		return occupiedByPlayer;
	}

}
