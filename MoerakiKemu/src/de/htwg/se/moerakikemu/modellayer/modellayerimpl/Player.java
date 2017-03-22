package de.htwg.se.moerakikemu.modellayer.modellayerimpl;

import de.htwg.se.moerakikemu.modellayer.IPlayer;

public class Player implements IPlayer {

	private int points;
	
	private String name;


	public Player() {
		this("");
	}
	
	public Player(final String name) {
		this.name = name;
		this.points = 0;
	}

	public int getPoints(){
		return points;
	}

	public String getName(){
		return name;
	}

	 public void addPoints(final int amount){
		 if (amount >= 0) {
			 points += amount;
		 }
	 }

	 public void setName (final String name){
		 if (name != null) {
			 this.name = name;
		 }
	 }

	 public void refreshPoints(){
		 points = 0;
	 }
}
