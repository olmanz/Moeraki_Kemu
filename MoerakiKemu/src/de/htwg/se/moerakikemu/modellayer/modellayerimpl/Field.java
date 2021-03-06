package de.htwg.se.moerakikemu.modellayer.modellayerimpl;

import java.util.UUID;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.ISpot;

public class Field implements IField {
	private int edgeLength;
	private int occupiedSpots;
	private ISpot[][] array;
	private String id;
	private String name;

	public Field(int edgeLength) throws IllegalArgumentException {
		if (edgeLength < 1) {
			throw new IllegalArgumentException("Edgelength too small: " + edgeLength);
		}
		this.edgeLength = edgeLength;
		array = new Spot[edgeLength][edgeLength];
		for (int i = 0; i < edgeLength; i++) {
			for (int j = 0; j < edgeLength; j++) {
				array[i][j] = new Spot();
			}
		}
		this.id = UUID.randomUUID().toString();
		occupiedSpots = 0;
		name = "default";
	}

	public int getEdgeLength() {
		return this.edgeLength;
	}

	public boolean getIsOccupied(final int x, final int y) {
		return !getIsOccupiedFrom(x, y).isEmpty();
	}

	public boolean occupy(final int x, final int y, final String playerName) {
		if (array[x][y].isOccupied()) {
			return false;
		} else {
			array[x][y].occupy(playerName);
			occupiedSpots++;
			return true;
		}
	}

	public String getIsOccupiedFrom(final int xCoordinate, final int yCoordinate) {
		if (array[xCoordinate][yCoordinate].isOccupied()) {
			return array[xCoordinate][yCoordinate].getOccupiedByPlayer();
		}
		return "";
	}

	public boolean isFilled() {
		return occupiedSpots == (edgeLength * edgeLength);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public ISpot getISpot(int x, int y) {
		return array[x][y];
	}

}
