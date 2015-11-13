package de.htwg.se.moeraki_kemu.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moeraki_kemu.controller.Controller;

public class Controller_Test {
	Controller controller;
	
	@Before
	public void setUp(){
		controller = new Controller(6);
		controller.setName("Player1", "Player2");
	}
	
	@Test
	public void test_occupy_returnNullIfOccupied(){
		for(int i = 1; i < 5; i++){
			for(int j = 1; j <= 6; j++){
					assertEquals(0, controller.occupy(i, j));
			}
		}	
		
		assertEquals(0, controller.occupy(5, 1));
		assertEquals(0, controller.occupy(5, 3));
		assertEquals(0, controller.occupy(5, 2));
		assertEquals(0, controller.occupy(5, 4));
		assertEquals(0, controller.occupy(6, 1));
		assertEquals(0, controller.occupy(6, 3));
		assertEquals(0, controller.occupy(6, 2));
		assertEquals(0, controller.occupy(6, 4));
		assertEquals(0, controller.occupy(5, 5));
		assertEquals(0, controller.occupy(5, 6));
		assertEquals(0, controller.occupy(6, 5));
		assertEquals(0, controller.occupy(6, 6));
		
		for(int i = 1; i <= 6; i++){
			for(int j = 1; j <= 6; j++){
					assertEquals(-1, controller.occupy(i, j));
			}
		}
	}
	
	
	@Test
	public void test_getPointsOfPlayer_returnPointsofCurrentPlayer(){
		controller = new Controller(5);
		controller.setName("Player1", "Player2");
		
		controller.occupy(1, 1);
		controller.occupy(3, 3);
		controller.occupy(1, 2);
		controller.occupy(3, 4);
		controller.occupy(2, 1);
		controller.occupy(3, 5);
		controller.occupy(2, 2);
		
		assertEquals(1, controller.getPointsOfPlayer("Player1"));
		assertEquals(0, controller.getPointsOfPlayer("Player2"));
		assertEquals(-1, controller.getPointsOfPlayer("Random"));
	}
	
}