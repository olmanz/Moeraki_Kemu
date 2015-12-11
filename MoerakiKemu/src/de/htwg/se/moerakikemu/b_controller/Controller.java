package de.htwg.se.moerakikemu.b_controller;

import de.htwg.se.moerakikemu.b_aicontroller.IController;
import de.htwg.se.moerakikemu.c_modellayer.Field;
import de.htwg.se.moerakikemu.c_modellayer.IField;
import de.htwg.se.moerakikemu.c_modellayer.IPlayer;
import de.htwg.se.moerakikemu.c_modellayer.Player;

public class Controller implements IController{

	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;	// Pointer to one of the players above
	
	private IField gameField;
	private int fieldLength;

	private ControllerHelper helper;
	
	private String playerWin;
	private boolean gameEnds;
	
	public Controller(int fieldLength) {
		gameField = new Field(fieldLength);
		this.fieldLength = fieldLength;
		player1 = new Player();
		player2 = new Player();
		currentPlayer = player1;	
		playerWin = null;
	}
	
	public void selectNextPlayer() {
		if(currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	public String getCurrentPlayerName() {
		return currentPlayer.getName();
	}

	public void setName(String player1name, String player2name) {
		player1.setName(player1name);
		player2.setName(player2name);
	}
	
	public String getPlayer1Name() {
		return player1.getName();
	}

	public String getPlayer2Name() {
		return player2.getName();
	}

	public int getPlayer1Points() {
		return player1.getPoints();
	}

	public int getPlayer2Points() {
		return player2.getPoints();
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
			// Notify UIs:
			// Print points with message
			// Then quit
		}
		
		if(gameField.getIsOccupiedFrom(x, y) != ""){
			return -1;
		}		
		gameField.occupy(x, y, getCurrentPlayerName());
		helper = new ControllerHelper(x, y, fieldLength - 1);
		helper.testSquare();
		testListOfSquares();
		helper.resetSquareTest();
		selectNextPlayer();
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
			if(gameField.getIsOccupiedFrom(x, y).equals(player1.getName())) {
				return 0;
			} else if(gameField.getIsOccupiedFrom(x, y).equals(player2.getName())) {
				return 1;
			}
		}
		return -1;
	}

	private void setPointsOfPlayer(int counter1, int counter2){
		if(counter1 == 3  && counter2 == 1){
			player1.addPoints(1);
		}
		if(counter1 == 4){ 
			player1.addPoints(1);
			playerWin = player1.getName();
			setEnd(true);
		}
		if(counter2 == 3 && counter1 == 1){
			player2.addPoints(1);
		} 
		if(counter2 == 4){
			player2.addPoints(1);
			playerWin = player2.getName();
			setEnd(true);
		}
	}
	
	public String getWinner(){
		if(playerWin == null){
			if(player1.getPoints() > player2.getPoints()){
				playerWin = player1.getName();
			} else if(player1.getPoints() < player2.getPoints()){
				playerWin = player2.getName();
			}
		}
		return playerWin;
	}
	
	public int getPointsOfPlayer(String playerName) {
		if(player1.getName().equals(playerName)){
			return player1.getPoints();
		} else if(player2.getName().equals(playerName)){
			return player2.getPoints();
		} else {
			return -1;
		}
	}

	public boolean testIfWinnerExists() {
		return gameEnds;
	}
	
	public void setEnd(boolean end) {
		gameEnds = end;
	}
}
