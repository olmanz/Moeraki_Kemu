package de.htwg.se.Modellayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Field_Test {

	Field field;
	Player player1;
	Player player2;

	@Before
	public void setUp() {
		field = new Field();
		player1 = new Player("alpha");
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
		fail("Not yet inplemented!");
	}

	@Test
	public void test_occupy_occupyThreeFieldsReturnsOnePoint() {
		fail("Not yet inplemented!");
	}
	

	@Test
	public void test_occupy_occupyTwoFieldsReturnsNoPoints() {
		fail("Not yet inplemented!");
	}
}
