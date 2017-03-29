package de.htwg.se.moerakikemu.persistence.db4o;

import java.util.List;

import com.db4o.*;
import com.db4o.query.Predicate;
import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;

public class FieldDB4O implements IFieldDAO {
	private ObjectContainer db;

	public FieldDB4O() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "field.data");
	}
	
	public void closeDb() {
		db.close();
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

	public void generateFields(int number, int edgeLength) {
		for (int i = 0; i < number; i++) {
			IField field = new Field(edgeLength);
			if (containsField(field)) {
				number--;
			} else {
				db.store(field);
			}
		}
	}

	private boolean containsField(IField field) {
		final String fieldString = field.toString();
		List<IField> fields = db.query(new Predicate<IField>() {

			private static final long serialVersionUID = 1L;

			public boolean match(IField field) {
				return (field.toString().equals(fieldString));
			}
		});

		if (fields.size() > 0) {
			return true;
		}
		return false;
	}

	public List<IField> getAllFields() {
		return db.query(IField.class);
	}
}
