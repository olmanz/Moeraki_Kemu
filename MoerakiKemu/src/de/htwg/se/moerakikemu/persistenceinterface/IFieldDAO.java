package de.htwg.se.moerakikemu.persistenceinterface;

import de.htwg.se.moerakikemu.modellayer.IField;

public interface IFieldDAO {
	
	/**
	 * Stores an instance of IField in a database.
	 */
	void saveField(final IField field);
	
	/**
	 * Deletes an instance of IField with a given id from the database.
	 */
	void deleteFieldByID(final String id);
	
	/**
	 * Checks if an instance of IField with the given id exists in the database.
	 * @return true if the instance exists, otherwise false.
	 */
	boolean containsFieldByID(final String id);
}
