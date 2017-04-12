package de.htwg.se.moerakikemu.persistence.couchdb;

import java.util.Set;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class PersistentField extends CouchDbDocument {
	private static final long serialVersionUID = 1538704903825440126L;
	@TypeDiscriminator
	private String id;
	private Set<PersistentSpot> cells;
	private String name;
	private int blocksPerEdge;
	private int numberSetSpots;
	
	public PersistentField() {
		
	}
}
