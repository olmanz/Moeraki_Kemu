package de.htwg.se.moerakikemu.controller.controllerimpl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;
import de.htwg.se.moerakikemu.controller.State;
import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;
import de.htwg.se.moerakikemu.view.UserInterface;
import de.htwg.se.util.StringHelper;
import de.htwg.se.util.observer.IObserverSubject;
import de.htwg.se.util.observer.ObserverObserver;
import de.htwg.se.util.observer.ObserverSubject;

@Singleton
public class Controller extends ObserverSubject implements IController, IObserverSubject {

	/**
	 * Replece the for-loop in the main program with update-calls from the
	 * Controller to keep UIs updated. When to update UIs: - newGame() -
	 * setEnd() - occupy()
	 */

	private IField gameField;
	private int fieldLength;

	private ControllerHelper helper;
	private IControllerPlayer playerController;

	private int xCoordinateStartDot, yCoordinateStartDot;

	private String playerWin;
	private boolean quitGame;
	private boolean winner;
	private IFieldDAO fieldDAO;

	@Inject
	public Controller(@Named("fieldLength") int fieldLength, IControllerPlayer playerCon, IFieldDAO fieldDAO) {
		super();
		gameField = new Field(fieldLength);
		this.fieldLength = fieldLength;
		this.playerController = playerCon;
		this.fieldDAO = fieldDAO;
		quitGame = false;
		playerWin = "";
		xCoordinateStartDot = 0;
		yCoordinateStartDot = 0;
		notifyObservers();
	}

	public String getIsOccupiedByPlayer(final int x, final int y) {
		return gameField.getIsOccupiedFrom(x, y);
	}

	public int getEdgeLength() {
		return fieldLength;
	}

	private boolean noProperStartDot(final int x, final int y) {
		return !playerController.startDotSet() && !setStartDot(x, y);
	}

	public int occupy(final int x, final int y) {
		printInfoAllUIs(x, y);

		if (!"".equals(gameField.getIsOccupiedFrom(x, y)) || noProperStartDot(x, y)) {
			return -1;
		}

		gameField.occupy(x, y, playerController.getCurrentPlayerName());
		helper = new ControllerHelper(x, y, fieldLength - 1);
		helper.testSquare();
		testListOfSquares();
		if (!"StartDot".equals(playerController.getCurrentPlayerName())) {
			testAllInLine(x, y);
		}
		helper.resetSquareTest();
		playerController.selectNextPlayer();

		if (gameField.isFilled()) {
			setEnd(true);
		}

		notifyObservers();

		return 0;
	}

	private boolean setStartDot(final int xCoordinate, final int yCoordinate) {
		final int halfLength = (fieldLength - 1) / 2;
		final int radiusLow = halfLength - 1;
		final int radiusUp = halfLength + (fieldLength % 2 != 0 ? 1 : 2);

		final boolean isInMidX = xCoordinate >= radiusLow && xCoordinate <= radiusUp;
		final boolean isInMidY = yCoordinate >= radiusLow && yCoordinate <= radiusUp;
		if (isInMidX && isInMidY) {
			xCoordinateStartDot = xCoordinate;
			yCoordinateStartDot = yCoordinate;
			return true;
		} else {
			return false;
		}

	}

	private void testListOfSquares() {
		int[] squareArray = helper.getSquareArray();
		if (squareArray[0] == 1) {
			testSquare(squareArray[1], squareArray[2], squareArray[3], squareArray[4]);
		} else if (squareArray[0] == 2) {
			testSquare(squareArray[1], squareArray[2], squareArray[3], squareArray[4]);
			testSquare(squareArray[5], squareArray[6], squareArray[7], squareArray[8]);
		} else if (squareArray[0] == 4) {
			for (int i = 0; i < 4; i++) {
				testSquare(squareArray[i * 4 + 1], squareArray[i * 4 + 2], squareArray[i * 4 + 3],
						squareArray[i * 4 + 4]);
			}
		}
	}

	private void testSquare(int xMin, int yMin, int xMax, int yMax) {
		int[] counterForPlayers = { 0, 0 };

		int index;
		index = checkOccupationReturnPlayerGettingPoint(xMin, yMin);
		if (index != -1) {
			counterForPlayers[index]++;
		}
		index = checkOccupationReturnPlayerGettingPoint(xMin, yMax);
		if (index != -1) {
			counterForPlayers[index]++;
		}
		index = checkOccupationReturnPlayerGettingPoint(xMax, yMin);
		if (index != -1) {
			counterForPlayers[index]++;
		}
		index = checkOccupationReturnPlayerGettingPoint(xMax, yMax);
		if (index != -1) {
			counterForPlayers[index]++;
		}

		setPointsOfPlayer(counterForPlayers[0], counterForPlayers[1]);
	}

	private int checkOccupationReturnPlayerGettingPoint(final int x, final int y) {
		if (!"".equals(gameField.getIsOccupiedFrom(x, y))) {
			if (gameField.getIsOccupiedFrom(x, y).equals(playerController.getPlayer1Name())) {
				return 0;
			} else if (gameField.getIsOccupiedFrom(x, y).equals(playerController.getPlayer2Name())) {
				return 1;
			}
		}
		return -1;
	}

	private void setPointsOfPlayer(int counter1, int counter2) {
		if (counter1 == 3 && counter2 == 1) {
			playerController.addAPointPlayer1();
			printInfoALLUIs(playerController.getPlayer1Name());
		}
		if (counter1 == 4) {
			playerController.addAPointPlayer1();
			playerWin = playerController.getPlayer1Name();
			printInfoALLUIs(playerController.getPlayer1Name());
			setWinner(true);
		}
		if (counter2 == 3 && counter1 == 1) {
			playerController.addAPointPlayer2();
			printInfoALLUIs(playerController.getPlayer2Name());
		}
		if (counter2 == 4) {
			playerController.addAPointPlayer2();
			playerWin = playerController.getPlayer2Name();
			printInfoALLUIs(playerController.getPlayer2Name());
			setWinner(true);
		}
	}

	private void testAllInLine(int x, int y) {
		if (!testIfNearStartDot(x, y)) {
			return;
		}
		final int distanceTop = xCoordinateStartDot;
		final int distanceBot = fieldLength;
		final int distanceRight = fieldLength;
		final int distanceLeft = yCoordinateStartDot;

		if (y == yCoordinateStartDot) {
			if (x > xCoordinateStartDot) {
				testInLine("x", xCoordinateStartDot, distanceBot, y, fieldLength - xCoordinateStartDot - 1);
			} else {
				testInLine("x", 0, distanceTop, y, distanceTop);
			}
		} else if (x == xCoordinateStartDot) {
			if (y < yCoordinateStartDot) {
				testInLine("y", 0, distanceLeft, x, distanceLeft);
			} else {
				testInLine("y", yCoordinateStartDot, distanceRight, x, fieldLength - yCoordinateStartDot - 1);
			}
		}
	}

	private boolean isOccupiedByCurrentPlayer(final int x, final int y) {
		return gameField.getIsOccupiedFrom(x, y).equals(playerController.getCurrentPlayerName());
	}

	private void testInLine(final String xy, final int start, final int end, final int secondValue,
			final int counterEnd) {
		int counter = 0;
		for (int i = start; i < end; i++) {
			if ("x".equals(xy) && isOccupiedByCurrentPlayer(i, secondValue)) {
				counter++;
			} else if ("y".equals(xy) && isOccupiedByCurrentPlayer(secondValue, i)) {
				counter++;
			}
		}
		if (counter == counterEnd) {
			setWinner(true);
		}
	}

	private boolean testIfNearStartDot(final int x, final int y) {
		return xCoordinateStartDot == x || yCoordinateStartDot == y;
	}

	public String getWinner() {
		if ("".equals(playerWin)) {
			if (playerController.getPlayer1Points() > playerController.getPlayer2Points()) {
				playerWin = playerController.getPlayer1Name();
			} else if (playerController.getPlayer1Points() < playerController.getPlayer2Points()) {
				playerWin = playerController.getPlayer2Name();
			}
		}
		return playerWin;
	}

	public boolean testIfWinnerExists() {
		return winner;
	}

	private void setWinner(boolean win) {
		winner = win;
	}

	public void setEnd(boolean end) {
		quitGame = end;
		printInfoALLUIs();
	}

	public boolean testIfEnd() {
		return quitGame;
	}

	public void newGame() {
		gameField = new Field(fieldLength);
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).printMessage("");
		}
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).addPoints(0, 0);
		}
		playerController.newGame();
		playerWin = "";
		quitGame = false;
		winner = false;

		notifyObservers();
	}

	private void printInfoALLUIs() {
		final String pointString = "Das Spiel endet";
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).printMessage(pointString);
		}
	}

	private void printInfoALLUIs(String player) {
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).addPoints(playerController.getPlayer1Points(), playerController.getPlayer2Points());
		}
		final String pointString = "Ein Punkt fuer " + player;
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).printMessage(pointString);
		}
	}

	private void printInfoAllUIs(int x, int y) {
		final String xValue = String.valueOf(x + 1);
		final String yValue = String.valueOf(y + 1);
		final String pointString = "Gewaehlter Punkt: " + xValue + "/" + yValue;
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).printMessage(pointString);
		}
	}

	public State getState() {
		if ("".equals(playerController.getPlayer1Name()) || "".equals(playerController.getPlayer2Name())) {
			return State.QUERY_PLAYER_NAME;
		} else if (quitGame) {
			return State.GAME_FINISHED;
		} else if (winner) {
			return State.PLAYER_WON;
		} else {
			return State.PLAYER_OCCUPIED;
		}
	}

	public String getPlayer1Name() {
		return StringHelper.getDefaultEmptyString(playerController.getPlayer1Name());
	}

	public String getPlayer2Name() {
		return StringHelper.getDefaultEmptyString(playerController.getPlayer2Name());
	}

	public void loadFromDB(String fieldId) {
		this.gameField = fieldDAO.getFieldByID(fieldId);
		notifyObservers();
	}

	public void saveToDB() {
		fieldDAO.saveField(gameField);
	}

	public boolean containsActualFieldDB() {
		return fieldDAO.containsFieldByID(gameField.getId());
	}

	public void generateFieldToDB(int number) {
		fieldDAO.generateFields(number, fieldLength);
	}

	@Override
	public void deleteFromDB(String id) {
		fieldDAO.deleteFieldByID(id);
	}

	public String getFieldName() {
		return gameField.getName();
	}

	public void setFieldName(String name) {
		gameField.setName(name);
	}

	public IField getField() {
		return gameField;
	}

	@Override
	public String[][] getRowDataAll() {
		List<IField> fields = fieldDAO.getAllFields();
		String[][] data = new String[fields.size()][2];
		for (int i = 0; i < fields.size(); i++) {
			IField f = fields.get(i);
			data[i][0] = f.getName();
			data[i][1] = f.getId().toString();
		}
		return data;
	}
}
