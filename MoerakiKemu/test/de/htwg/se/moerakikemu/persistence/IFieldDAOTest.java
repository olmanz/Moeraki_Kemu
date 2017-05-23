package de.htwg.se.moerakikemu.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.persistence.db4o.FieldDB4O;
import de.htwg.se.moerakikemu.persistence.hibernate.FieldHibernateDAO;

public class IFieldDAOTest {
	IFieldDAO fieldDAO;

	@Before
	public void setUp() {
		fieldDAO = new FieldHibernateDAO();
	}

	@After
	public void after() {
		for (IField field : fieldDAO.getAllFields()) {
			System.out.println(field.getId());
			fieldDAO.deleteFieldByID(field.getId());
		}
		if (fieldDAO instanceof FieldDB4O)
			((FieldDB4O) fieldDAO).closeDb();
	}

	@Test
	public void testSaveField() {
		fieldDAO.saveField(new Field(12));
		fieldDAO.saveField(new Field(12));
		IField field = new Field(14);
		fieldDAO.saveField(field);
		fieldDAO.saveField(field);
		fieldDAO.saveField(null);
		assertEquals(fieldDAO.getAllFields().size(), 3);
	}

	@Test
	public void testGenerateFields() {
		fieldDAO.generateFields(14, 12);
		assertEquals(fieldDAO.getAllFields().size(), 14);
	}

	@Test
	public void testGetFieldByID() {
		IField field = new Field(12);
		String testId = "TEST-ID01";
		String testName = "TEST-FILED01";
		field.setId(testId);
		field.setName(testName);
		field.setName(null);
		fieldDAO.saveField(field);
		IField foundField = fieldDAO.getFieldByID(testId);
		assertEquals(foundField.getName(), testName);
		assertTrue(fieldDAO.getFieldByID("TEST-ID02") == null);
	}

	@Test
	public void testDeleteAndContainsFieldById() {
		IField field = new Field(12);
		String testId = "TEST-ID01";
		String testName = "TEST-FILED01";
		field.setId(testId);
		field.setName(testName);
		fieldDAO.saveField(field);
		assertTrue(fieldDAO.containsFieldByID(testId));
		fieldDAO.deleteFieldByID(testId);
		assertFalse(fieldDAO.containsFieldByID(testId));
		fieldDAO.deleteFieldByID(testId);
	}

}
