package de.htwg.se.moerakikemu.persistenceinterface;

import de.htwg.se.moerakikemu.modellayer.IField;

public interface IFieldDAO {
	void saveField(final IField field);
	void deleteFieldByID(final String id);
	boolean containsFieldByID(final String id);
}
