package de.htwg.se.moerakikemu.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

public class PersistentSpot extends CouchDbDocument {
	private static final long serialVersionUID = -756264832680406685L;
	private String id;
	private int row = 0;
	private int column = 0;
	private boolean isOccupied = false;

	private String occupiedByPlayer;

	private PersistentField field;

	public PersistentSpot(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public PersistentSpot() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(boolean occupied) {
		this.isOccupied = occupied;
	}

	public String getOccupiedByPlayer() {
		return occupiedByPlayer;
	}

	public void setOccupiedByPlayer(String occupiedByPlayer) {
		this.occupiedByPlayer = occupiedByPlayer;
	}

	public PersistentField getField() {
		return field;
	}

	public void setField(PersistentField field) {
		this.field = field;
	}

}
