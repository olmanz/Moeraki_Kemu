package de.htwg.se.moerakikemu.b_controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moerakikemu.b_controller.*;
import de.htwg.se.moerakikemu.controller.*;
import de.htwg.se.moerakikemu.controller.controller_impl.Controller;
import de.htwg.se.moerakikemu.controller.controller_impl.ControllerPlayer;

public class Controller_Test {
	IController controller;
	IControllerPlayer playerController;
	
	@Before
	public void setUp(){
		playerController = new ControllerPlayer();
		controller = new Controller(6, playerController);
		playerController.setName("Player1", "Player2");
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
		controller = new Controller(5, playerController);
		
		controller.occupy(1, 1);
		controller.occupy(3, 3);
		controller.occupy(1, 2);
		controller.occupy(3, 4);
		controller.occupy(2, 1);
		controller.occupy(2, 2);
		
		assertEquals("Player1", playerController.getPlayer1Name());
		assertEquals("Player2", playerController.getPlayer2Name());
	}
	
	@Test
	public void test_selectNextPlayer_wasPlayerOneIsPlayerTwoNextPlayerOne() {
		assertEquals(playerController.getCurrentPlayerName(), "Player1");
		playerController.selectNextPlayer();
		assertEquals(playerController.getCurrentPlayerName(), "Player2");
		playerController.selectNextPlayer();
		assertEquals(playerController.getCurrentPlayerName(), "Player1");
	}
	
	@Test
	public void test_setName_isPlayer1Player2() {
		playerController.setName("Player1", "Player2");
		assertEquals(playerController.getPlayer1Name(), "Player1");
		assertEquals(playerController.getPlayer2Name(), "Player2");
	}
	
	@Test
	public void test_getEdgeLength_sixAsInitialized() {
		assertEquals(controller.getEdgeLength(), 6);
	}
	
	@Test
	public void test_getQuit_gamefinished(){
		controller = new Controller(5, playerController);
		assertFalse(controller.testIfWinnerExists());
		
		controller.occupy(1, 1);
		controller.occupy(2, 2);
		controller.occupy(1, 2);
		controller.occupy(2, 3);
		controller.occupy(2, 1);
		
		assertEquals("Player1", controller.getWinner());
		
		playerController = new ControllerPlayer();
		playerController.setName("Player1", "Player2");
		controller = new Controller(5, playerController);
		assertEquals("", controller.getWinner());

		controller.occupy(5, 5);
		controller.occupy(1, 1);
		controller.occupy(4, 4);
		controller.occupy(2, 1);
		controller.occupy(3, 3);
		controller.occupy(1, 2);
		controller.occupy(2, 2);
		
		assertEquals("Player2", controller.getWinner());
		assertEquals("Player2", controller.getWinner());
	}
	
	@Test
	public void test_getIsOccupiedByPlayer_ifOccupiedFromAPlayer(){
		controller = new Controller(5, playerController);
		
		controller.occupy(1, 1);
		
		String a = controller.getIsOccupiedByPlayer(0, 0); //0,0 because occupy get the direkt input from a player, so there is a -1, -1 needed
		assertEquals("Player1", a);
	}
}
