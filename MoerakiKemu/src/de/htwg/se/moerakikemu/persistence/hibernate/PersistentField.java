package de.htwg.se.moerakikemu.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grid")
public class PersistentField implements Serializable {

	private static final long serialVersionUID = -5473842298539359097L;

	@Id
	@Column(name = "id")
	private String fieldId;

	@OneToMany(mappedBy = "field")
	@Column(name = "spot")
	private List<PersistentSpot> spots;

	private String name;
	private int edgeLength;

	public PersistentField() {
	}

	public String getId() {
		return fieldId;
	}

	public void setId(String id) {
		this.fieldId = id;
	}

	public List<PersistentSpot> getSpots() {
		return spots;
	}

	public void setSpots(List<PersistentSpot> spots) {
		this.spots = spots;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public int getEdgeLength() {
		return edgeLength;
	}

	public void setEdgeLength(int edgeLength) {
		this.edgeLength = edgeLength;
	}

}
