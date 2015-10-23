package Modellayer;

public class Spot {

	private boolean occupied;			// True if player set token in this field
	private String occupiedByPlayer;	// Name of the player of with token on this field
	
	public Spot() {
		occupied = false;
	}
	
	public void occupy(final String playerName) {
		occupied = true;
		this.occupiedByPlayer = playerName;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public String getOccupiedByPlayer() {
		return occupiedByPlayer;
	}

}
