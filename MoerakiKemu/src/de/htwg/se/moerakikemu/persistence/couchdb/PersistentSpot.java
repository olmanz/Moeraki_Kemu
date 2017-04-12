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
}
