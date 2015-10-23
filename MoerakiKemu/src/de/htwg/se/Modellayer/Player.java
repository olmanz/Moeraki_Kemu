package de.htwg.se.Modellayer;

public class Player {

	/**
	 * Amount of points of the Player; determines win or lost of the game.
	 */
	private int points;
	
	/**
	 * Name of the player for identification of occupied Spots.
	 */
	private String name;
	
	/**
	 * Creates a new Player with a name and zero points.
	 * @param name
	 */
	public Player(final String name) {
		this.name = name;
		points = 0;
	}
	
	/**
	 * Returns the points of the player.
	 * @return Points >= 0.
	 */
	public int getPoints(){
		return points;
	}
	
	/**
	 * Returns the name of the Player.
	 * @return Not Null.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Adds points to the Player's points.
	 * @param amount The amount to increase the Player's points.
	 */
	 public void addPoints(int amount){
		 if (amount >= 0) {
			 points += amount;
		 }
	 }
}
