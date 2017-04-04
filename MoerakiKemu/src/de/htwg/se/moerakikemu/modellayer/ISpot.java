package de.htwg.se.moerakikemu.modellayer;

public interface ISpot {

	boolean isOccupied();

	boolean occupy(String playerName);

	String getOccupiedByPlayer();

}
