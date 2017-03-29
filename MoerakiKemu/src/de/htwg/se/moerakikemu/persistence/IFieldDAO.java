package de.htwg.se.moerakikemu.persistence;

import java.util.List;

import de.htwg.se.moerakikemu.modellayer.IField;

public interface IFieldDAO {

	/**
	 * Stores an instance of IField in a database.
	 * @param field that is to be stored
	 * 
	 */
	void saveField(final IField field);

	/**
	 * Deletes an instance of IField with a given id from the database.
	 * @param id of the field that is deleted
	 */
	void deleteFieldByID(final String id);

	/**
	 * Checks if an instance of IField with the given id exists in the database.
	 * @param id that is searched for
	 * @return true if the instance exists, otherwise false.
	 */
	boolean containsFieldByID(final String id);
	
	/**
	 * Returns field with given ID
	 * @param id that is searched for
	 * @return field
	 */
	IField getFieldByID(final String id);
	
	/**
	 * Generates given number of fields with given edgeLength
	 * @param number of new fields
	 * @param edgeLength of each field
	 */
	void generateFields(final int number, int edgeLength);
	
	/**
	 * Returns all fields
	 * @return list of fields
	 */
	List<IField> getAllFields();

}
