package de.htwg.se.moeraki_kemu.modellayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moeraki_kemu.modellayer.Spot;

public class Spot_test {
	Spot spot;

	@Before
	public void setUp(){
		spot = new Spot();
	}
	
	@Test
	public void test_isOccupied_firstOccupation_returnsNotEmptyString(){
		assertTrue(spot.occupy("alpha"));
		assertTrue(spot.isOccupied());
		assertEquals("alpha", spot.getOccupiedByPlayer());
	}
	
	@Test
	public void test_isOccupied_alreadyOccupied_returnsFalse() {
		assertTrue(spot.occupy("alpha"));
		assertTrue(spot.isOccupied());
		
		assertFalse(spot.occupy("beta"));
		assertEquals("alpha", spot.getOccupiedByPlayer());
	}
}