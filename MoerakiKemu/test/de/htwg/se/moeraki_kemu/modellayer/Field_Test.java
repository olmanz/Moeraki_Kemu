package de.htwg.se.moeraki_kemu.modellayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moeraki_kemu.modellayer.Field;
import de.htwg.se.moeraki_kemu.modellayer.Player;

public class Field_Test {
	Field field;
	Player player1;
	Player player2;
	

	@Before
	public void setUp() {
		field = new Field();
		player1 = new Player("alpha");
		player2 = new Player("beta");
	}

	@Test
	public void test_getIsOccupied_unoccupiedSpotReturnsFalse() {
		assertEquals("", field.getIsOccupiedFrom(1, 2));
	}
	@Test
	public void test_occupy_occupyEmptySpotReturnsNoPoints() {
		assertTrue(field.occupy(2, 2, player1.getName()));
	}

	@Test
	public void test_occupy_occupyNotEmptySpotReturnsFalse() {
		assertTrue(field.occupy(2, 2, player1.getName()));
		assertFalse(field.occupy(2, 2, player1.getName()));
	}
}
