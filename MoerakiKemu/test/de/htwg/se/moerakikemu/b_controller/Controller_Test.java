package de.htwg.se.moerakikemu.b_controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;
import de.htwg.se.moerakikemu.controller.State;
import de.htwg.se.moerakikemu.controller.controllerimpl.*;
import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;
import de.htwg.se.moerakikemu.persistence.db4o.FieldDB4O;

public class Controller_Test {

	private static final String PLAYER1NAME = "Player1";
	private static final String PLAYER2NAME = "Player2";

	private IController controller;
	private IControllerPlayer playerController;
	private IFieldDAO fieldDAO;

	@Before
	public void setUp() {
		playerController = new ControllerPlayer();
		fieldDAO = new FieldDB4O();
		controller = new Controller(6, playerController, fieldDAO);
		playerController.setName(PLAYER1NAME, PLAYER2NAME);
	}

	@After
	public void after() {
		for (IField field : fieldDAO.getAllFields()) {
			fieldDAO.deleteFieldByID(field.getId());
		}
		if (fieldDAO instanceof FieldDB4O)
			((FieldDB4O) fieldDAO).closeDb();
	}

	@Test
	public void test_occupy_returnNullIfOccupied() {

		controller.occupy(3, 3);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= 5; j++) {
				if (i == 3 && j == 3) {
					assertEquals(-1, controller.occupy(i, j));
				} else {
					assertEquals(0, controller.occupy(i, j));
				}
			}
		}

		assertEquals(0, controller.occupy(4, 0));
		assertEquals(0, controller.occupy(4, 2));
		assertEquals(0, controller.occupy(4, 1));
		assertEquals(0, controller.occupy(4, 3));
		assertEquals(0, controller.occupy(5, 0));
		assertEquals(0, controller.occupy(5, 2));
		assertEquals(0, controller.occupy(5, 1));
		assertEquals(0, controller.occupy(5, 3));
		assertEquals(0, controller.occupy(4, 4));
		assertEquals(0, controller.occupy(4, 5));
		assertEquals(0, controller.occupy(5, 4));
		assertEquals(0, controller.occupy(5, 5));

		for (int i = 0; i <= 5; i++) {
			for (int j = 0; j <= 5; j++) {
				assertEquals(-1, controller.occupy(i, j));
			}
		}
		controller.occupy(0, 0);
	}

	@Test
	public void test_getPointsOfPlayer_returnPointsofCurrentPlayer() {
		controller = new Controller(5, playerController, fieldDAO);

		controller.occupy(1, 1);
		controller.occupy(3, 3);
		controller.occupy(1, 2);
		controller.occupy(3, 4);
		controller.occupy(2, 1);
		controller.occupy(2, 2);

		assertEquals(PLAYER1NAME, playerController.getPlayer1Name());
		assertEquals(PLAYER2NAME, playerController.getPlayer2Name());
	}

	@Test
	public void test_selectNextPlayer_wasPlayerOneIsPlayerTwoNextPlayerOne() {
		assertEquals(playerController.getCurrentPlayerName(), "StartDot");
		playerController.selectNextPlayer();
		assertEquals(playerController.getCurrentPlayerName(), PLAYER1NAME);
		playerController.selectNextPlayer();
		assertEquals(playerController.getCurrentPlayerName(), PLAYER2NAME);
		playerController.selectNextPlayer();
		assertEquals(playerController.getCurrentPlayerName(), PLAYER1NAME);
	}

	@Test
	public void test_setName_isPlayer1Player2() {
		playerController.setName(PLAYER1NAME, PLAYER2NAME);
		assertEquals(playerController.getPlayer1Name(), PLAYER1NAME);
		assertEquals(playerController.getPlayer2Name(), PLAYER2NAME);
	}

	@Test
	public void test_getEdgeLength_sixAsInitialized() {
		assertEquals(controller.getEdgeLength(), 6);
	}

	@Test
	public void test_getQuit_gamefinished() {
		controller = new Controller(5, playerController, fieldDAO);
		assertFalse(controller.testIfWinnerExists());

		controller.occupy(3, 3); // Set start Spot

		controller.occupy(0, 0);
		controller.occupy(1, 1);
		controller.occupy(0, 1);
		controller.occupy(1, 2);
		controller.occupy(1, 0);

		assertEquals(PLAYER1NAME, controller.getWinner());

		playerController = new ControllerPlayer();
		playerController.setName(PLAYER1NAME, PLAYER2NAME);
		controller = new Controller(5, playerController, fieldDAO);
		assertEquals("", controller.getWinner());

		controller.occupy(2, 2); // Set start Spot

		controller.occupy(4, 4);
		controller.occupy(0, 0);
		controller.occupy(3, 3);
		controller.occupy(1, 0);
		controller.occupy(2, 3);
		controller.occupy(0, 1);
		controller.occupy(1, 1);

		assertEquals(PLAYER2NAME, controller.getWinner());
	}

	@Test
	public void test_getIsOccupiedByPlayer_ifOccupiedFromAPlayer() {
		controller = new Controller(5, playerController, fieldDAO);

		controller.occupy(2, 2); // Set start Spot
		controller.occupy(0, 0);

		String a = controller.getIsOccupiedByPlayer(0, 0); // 0,0 because occupy
															// get the direkt
															// input from a
															// player, so there
															// is a -1, -1
															// needed
		assertEquals(PLAYER1NAME, a);
	}

	@Test
	public void test_newGame_resetController() {
		controller.newGame();
		assertEquals(false, controller.testIfEnd());
		assertEquals(false, controller.testIfWinnerExists());
		assertTrue("".equals(controller.getWinner()));
	}

	@Test
	public void test_getState_returnsStates() {
		playerController = new ControllerPlayer();
		controller = new Controller(6, playerController, fieldDAO);

		// Test player names
		playerController.setName("", "");
		assertEquals(State.QUERY_PLAYER_NAME, controller.getState());
		playerController.setName("A", "");
		assertEquals(State.QUERY_PLAYER_NAME, controller.getState());

		// Test normal occupation state
		playerController.setName("A", "B");
		assertEquals(State.PLAYER_OCCUPIED, controller.getState());

		// Player occupies a square.
		controller.occupy(3, 3); // Set start Spot
		controller.occupy(1, 1);
		controller.occupy(2, 3);
		controller.occupy(1, 2);
		controller.occupy(3, 2);
		controller.occupy(2, 1);
		controller.occupy(3, 4);
		controller.occupy(2, 2);
		controller.occupy(4, 3);
		assertEquals(State.PLAYER_WON, controller.getState());

		// SetEnd
		controller.setEnd(true);
		assertEquals(State.GAME_FINISHED, controller.getState());
	}

	@Test
	public void testGetPlayersName() {
		assertEquals(controller.getPlayer1Name(), "Player1");
		assertEquals(controller.getPlayer2Name(), "Player2");
	}

	@Test
	public void testDB() {
		IField field = controller.getField();
		String fieldname = "TESTFIELD";
		controller.setFieldName(fieldname);
		controller.saveToDB();
		assertTrue(controller.containsActualFieldDB());
		controller.loadFromDB(field.getId());
		assertEquals(field.getId(), controller.getField().getId());
		assertEquals(fieldname, controller.getFieldName());
		controller.generateFieldToDB(10);
		assertEquals(fieldDAO.getAllFields().size(), 11);
	}

}
