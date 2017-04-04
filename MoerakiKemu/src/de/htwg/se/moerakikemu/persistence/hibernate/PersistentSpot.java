package de.htwg.se.moerakikemu.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "spot")
public class PersistentSpot implements Serializable {

	private static final long serialVersionUID = 3320952025325625843L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "rowspot")
	private int row = 0;

	@Column(name = "columnspot")
	private int column = 0;

	private boolean isOccupied = false;

	private String occupiedByPlayer;

	@ManyToOne
	@JoinColumn(name = "fieldid")
	private PersistentField field;

	public PersistentSpot(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public PersistentSpot() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
