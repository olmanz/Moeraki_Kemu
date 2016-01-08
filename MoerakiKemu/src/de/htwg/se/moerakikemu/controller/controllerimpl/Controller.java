package de.htwg.se.moerakikemu.controller.controllerimpl;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;
import de.htwg.se.moerakikemu.controller.State;
import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.view.UserInterface;
import de.htwg.se.util.observer.IObserverSubject;
import de.htwg.se.util.observer.ObserverObserver;
import de.htwg.se.util.observer.ObserverSubject;

public class Controller extends ObserverSubject implements IController, IObserverSubject {
	
	private IField gameField;
	private int fieldLength;

	private ControllerHelper helper;
	private IControllerPlayer playerController;
	
	private String playerWin;
	private boolean gameEnds;
	private boolean winner;
	
	public Controller(int fieldLength, IControllerPlayer playerCon) {
		gameField = new Field(fieldLength);
		this.fieldLength = fieldLength;
		this.playerController = playerCon;
		gameEnds = false;
		playerWin = "";
	}
	
	public String getIsOccupiedByPlayer(int x, int y) {
		return gameField.getIsOccupiedFrom(x, y);
	}
	
	public int getEdgeLength() {
		return fieldLength;
	}
	
	public int occupy(int xCoordinate, int yCoordinate) {
		int x = xCoordinate - 1;
		int y = yCoordinate - 1;
		if(gameField.isFilled()){
			setEnd(true);
		}
		
		if(gameField.getIsOccupiedFrom(x, y) != ""){
			return -1;
		}		
		gameField.occupy(x, y, playerController.getCurrentPlayerName());
		helper = new ControllerHelper(x, y, fieldLength - 1);
		helper.testSquare();
		testListOfSquares();
		helper.resetSquareTest();
		playerController.selectNextPlayer();
		return 0;
	}

	private void testListOfSquares(){
		int[] squareArray = new int[17];
		squareArray = helper.getSquareArray();
		if(squareArray[0] == 1){
			testSquare(squareArray[1], squareArray[2], squareArray[3],squareArray[4]);
		} else if(squareArray[0] == 2){
			testSquare(squareArray[1], squareArray[2], squareArray[3],squareArray[4]);
			testSquare(squareArray[5], squareArray[6], squareArray[7],squareArray[8]);
		} else if(squareArray[0] == 4){
			for(int i = 1; i < 17; i+=4){
				testSquare(squareArray[i], squareArray[i+1], squareArray[i+2], squareArray[i+3]);
			}
		}
	}
	
	private void testSquare(int xMin, int yMin, int xMax, int yMax){
		int []counterForPlayers = {0, 0};
		
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
		if(!"".equals(gameField.getIsOccupiedFrom(x, y))){
			if(gameField.getIsOccupiedFrom(x, y).equals(playerController.getPlayer1Name())) {
				return 0;
			} else if(gameField.getIsOccupiedFrom(x, y).equals(playerController.getPlayer2Name())) {
				return 1;
			}
		}
		return -1;
	}

	private void setPointsOfPlayer(int counter1, int counter2){
		if(counter1 == 3  && counter2 == 1){
			playerController.addAPointPlayer1();
		}
		if(counter1 == 4){ 
			playerController.addAPointPlayer1();
			playerWin = playerController.getPlayer1Name();
			setWinner(true);
		}
		if(counter2 == 3 && counter1 == 1){
			playerController.addAPointPlayer2();
		} 
		if(counter2 == 4){
			playerController.addAPointPlayer2();
			playerWin = playerController.getPlayer2Name();
			setWinner(true);
		}
		printPointsAllUIs();
	}
	
	public String getWinner(){
		if("".equals(playerWin)){
			if(playerController.getPlayer1Points() > playerController.getPlayer2Points()){
				playerWin = playerController.getPlayer1Name();
			} else if(playerController.getPlayer1Points() < playerController.getPlayer2Points()){
				playerWin = playerController.getPlayer2Name();
			}
		}
		return playerWin;
	}

	public boolean testIfWinnerExists() {
		return winner;
	}
	
	private void setWinner(boolean win){
		winner = win;
	}
	
	public void setEnd(boolean end) {
		gameEnds = end;
	}
	
	public boolean testIfEnd(){
		return gameEnds;
	}
	
	public void newGame(){
		gameField = new Field(fieldLength);
		playerController.newGame();
	}

	private void printPointsAllUIs() {
		String pointString = "";
		for (ObserverObserver ui : observers) {
			((UserInterface) ui).printMessage(pointString);
		}
	}

	@Override
	public State returnState() {
		if ("".equals(playerController.getPlayer1Name()) || "".equals(playerController.getPlayer2Name())) {
			return State.query_player_name;
		} else if (gameEnds) {
			return State.game_finished;
		} else {
			return State.player_occupied;
		}
	}
}
