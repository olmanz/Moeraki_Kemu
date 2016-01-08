package de.htwg.se.moerakikemu.modellayer;

public interface IPlayer {

	int getPoints();
	String getName();
	void addPoints(int amount);
	void setName (String name);
	void refreshPoints();

}
