package de.htwg.se.moerakikemu.c_modellayer;

public interface IPlayer {

	int getPoints();
	String getName();
	void addPoints(int amount);
	void setName (String name);

}
