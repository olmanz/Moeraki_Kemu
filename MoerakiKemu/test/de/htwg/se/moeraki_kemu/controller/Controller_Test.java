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
	public void test_selectNextPlayer_wasPlayerOneIsPlayerTwoNextPlayerOne() {
		assertEquals(controller.getCurrentPlayerName(), "Player1");
		controller.selectNextPlayer();
		assertEquals(controller.getCurrentPlayerName(), "Player2");
		controller.selectNextPlayer();
		assertEquals(controller.getCurrentPlayerName(), "Player1");
	}
	
	@Test
	public void test_setName_isSpieler1Spieler2() {
		controller.setName("Spieler1", "Spieler2");
		assertEquals(controller.getPlayer1Name(), "Spieler1");
		assertEquals(controller.getPlayer2Name(), "Spieler2");
	}
	
	@Test
	public void test_getEdgeLength_sixAsInitialized() {
		assertEquals(controller.getEdgeLength(), 6);
	}
}