package de.htwg.se.moerakikemu.persistence;

import com.db4o.*;
import de.htwg.se.moerakikemu.modellayer.IField;

public class FieldDB4O {
	private ObjectContainer db;
	
	public FieldDB4O() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "field.data");
	}
	
	public void saveField(final IField field) {
		db.store(field);
	}
}
