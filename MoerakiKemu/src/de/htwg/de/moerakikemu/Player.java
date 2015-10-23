package de.htwg.de.moerakikemu;

public class Player {

	private int points;
	private String name;
	
	public Player(final String name) {
		this.name = name;
		points = 0;
	}
	
	
	public int getPoints(){
		return points;
	}
	
	public String getName(){
		return name;
	}
	 public void addPoints(int amount){
		 points += amount;
	 }
}
