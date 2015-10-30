package de.htwg.se.MoerakiKemu.Modellayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.MoerakiKemu.Modellayer.Field;
import de.htwg.se.MoerakiKemu.Modellayer.Player;

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
		assertFalse(field.getIsOccupied(1, 2));
	}

	@Test
	public void test_occupy_occupyEmptySpotReturnsNoPoints() {
		assertEquals(0, field.occupy(2, 2, player1.getName()));
	}

	@Test
	public void test_occupy_occupyNotEmptySpotReturnsNegativeOne() {
		assertEquals(0, field.occupy(2, 2, player1.getName()));
		assertEquals(-1, field.occupy(2, 2, player1.getName()));
	}

	@Test
	public void test_occupy_occupyFourFieldsReturnsOnePoint() {
		assertEquals(0, field.occupy(2, 3, player1.getName()));
		assertEquals(0, field.occupy(3, 2, player1.getName()));
		assertEquals(0, field.occupy(3, 3, player1.getName()));
		assertEquals(1, field.occupy(2, 2, player1.getName()));
	}

	@Test
	public void test_occupy_occupyThreeFieldsReturnsOnePoint() {
		assertEquals(0, field.occupy(2, 3, player2.getName()));
		assertEquals(0, field.occupy(3, 2, player1.getName()));
		assertEquals(0, field.occupy(3, 3, player1.getName()));
		assertEquals(1, field.occupy(2, 2, player1.getName()));
	}
	

	@Test
	public void test_occupy_occupyTwoFieldsReturnsNoPoints() {
		assertEquals(0, field.occupy(2, 3, player2.getName()));
		assertEquals(0, field.occupy(3, 2, player2.getName()));
		assertEquals(0, field.occupy(3, 3, player1.getName()));
		assertEquals(1, field.occupy(2, 2, player1.getName()));
	}
}
