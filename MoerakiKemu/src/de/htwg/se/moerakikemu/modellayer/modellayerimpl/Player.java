package de.htwg.se.moerakikemu.modellayer.modellayerimpl;

import de.htwg.se.moerakikemu.modellayer.IPlayer;

public class Player implements IPlayer {

	int points;
	
	private String name;
	
	public Player() {
		name = "";
		points = 0;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String getName(){
		return name;
	}
	
	 public void addPoints(int amount){
		 if (amount >= 0) {
			 points += amount;
		 }
	 }
	 
	 public void setName (String name){
		 this.name = name;
	 }
	 
	 public void refreshPoints(){
		 points = 0;
	 }
}
