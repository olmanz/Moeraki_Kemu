package de.htwg.se.moerakikemu.persistence.couchdb;

import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class PersistentField extends CouchDbDocument {
	private static final long serialVersionUID = -1315406203294763310L;
	@TypeDiscriminator
	private String id;
	private List<PersistentSpot> spots;
	private String name;
	private int blocksPerEdge;
	private int numberSetSpots;
	private int edgeLength;
	
	public PersistentField() {
		
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setSpots(List<PersistentSpot> spots) {
		this.spots = spots;
	}
	
	public List<PersistentSpot> getSpots() {
		return this.spots;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setBlocksPerEdge(int blocksPerEdge) {
		this.blocksPerEdge = blocksPerEdge;
	}
	
	public int getBlocksPerEdge() {
		return this.blocksPerEdge;
	}
	
	public void setNumberSetSpots(int numberSetSpots) {
		this.numberSetSpots = numberSetSpots;
	}
	
	public int getNumberSetSpots() {
		return this.numberSetSpots;
	}

	public int getEdgeLength() {
		return edgeLength;
	}

	public void setEdgeLength(int edgeLength) {
		this.edgeLength = edgeLength;
	}
}
