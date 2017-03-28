package de.htwg.se.moerakikemu.persistence;

import java.util.List;

import com.db4o.*;
import com.db4o.query.Predicate;

import de.htwg.se.moerakikemu.modellayer.IField;

public class FieldDB4O {
	private ObjectContainer db;
	
	public FieldDB4O() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "field.data");
	}
	
	public void saveField(final IField field) {
		db.store(field);
	}
	
	public void deleteFieldByID(final String id) {
		if (containsFieldByID(id)) {
			db.delete(getFieldByID(id));
		}
	}
	
	public IField getFieldByID(final String id) {
		List<IField> fields = db.query(new Predicate<IField>() {
			private static final long serialVersionUID = 1L;
			
			public boolean match(IField field) {
				return (id.equals(field.getID()));
			}
		});
		
		if (fields.size() > 0) {
			return fields.get(0);
		}
		
		return null;
	}
	
	public boolean containsFieldByID(final String id) {
		List<IField> fields = db.query(new Predicate<IField>() {
			private static final long serialVersionUID = 1L;
			
			public boolean match(IField field) {
				return (field.getID().equals(id));
			}
		});
		
		if (fields.size() > 0) {
			return true;
		}
		
		return false;
	}
}
