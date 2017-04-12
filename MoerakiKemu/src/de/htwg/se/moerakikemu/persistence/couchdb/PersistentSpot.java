package de.htwg.se.moerakikemu.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

public class PersistentSpot extends CouchDbDocument {
	private static final long serialVersionUID = -6049548436571017626L;
	private String id;
	private Integer value = 0;
	private Integer row = 0;
	private Integer column = 0;
	private Boolean given = false;
	
	public PersistentSpot(Integer column, Integer row) {
		this.column = column;
		this.row = row;
	}
	
	public PersistentSpot() {
		
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public void setRow(Integer row) {
		this.row = row;
	}
	
	public Integer getRow() {
		return this.row;
	}
	
	public void setColumn(Integer column) {
		this.column = column;
	}
	
	public Integer getColumn() {
		return this.column;
	}
	
	public void setRowSpot(Integer rowSpot) {
		this.row = rowSpot;
	}
	
	public Integer getRowSpot() {
		return this.row;
	}
	
	public void setColumnSpot(Integer columnSpot) {
		this.column = columnSpot;
	}
	
	public Integer getColumnSpot() {
		return this.column;
	}
	
	public void setGiven(Boolean given) {
		this.given = given;
	}
	
	public Boolean getGiven() {
		return this.given;
	}
}
