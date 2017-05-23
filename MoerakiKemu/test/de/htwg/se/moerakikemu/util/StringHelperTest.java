package de.htwg.se.moerakikemu.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import de.htwg.se.util.StringHelper;

public class StringHelperTest {
	private static final String STRING_1 = "Hallo";
	private static final String EMPTY_STRING = "";
	private static final String DEFAULT = "DEFAULT";

	@Test
	public void testGetDefaultEmptyString() {
		assertEquals(StringHelper.getDefaultEmptyString(STRING_1),STRING_1);
		assertEquals(StringHelper.getDefaultEmptyString(EMPTY_STRING),EMPTY_STRING);
		assertEquals(StringHelper.getDefaultEmptyString(null),EMPTY_STRING);
	}

	@Test
	public void testGetDefaultString() {
		assertEquals(StringHelper.getDefaultString(EMPTY_STRING, DEFAULT), DEFAULT);
		assertEquals(StringHelper.getDefaultString(STRING_1, DEFAULT), STRING_1);
	}

	@Test
	public void testIsNotEmpty() {
		assertFalse(StringHelper.isNotEmpty(null));
		assertFalse(StringHelper.isNotEmpty(EMPTY_STRING));
		assertTrue(StringHelper.isNotEmpty(STRING_1));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(StringHelper.isEmpty(null));
		assertTrue(StringHelper.isEmpty(EMPTY_STRING));
		assertFalse(StringHelper.isEmpty(STRING_1));
	}

}
