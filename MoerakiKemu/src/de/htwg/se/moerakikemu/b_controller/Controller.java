package de.htwg.se.moerakikemu.b_controller;

import de.htwg.se.moerakikemu.b_aicontroller.IController;
import de.htwg.se.moerakikemu.b_aicontroller.IControllerPlayer;
import de.htwg.se.moerakikemu.c_aimodellayer.IField;
import de.htwg.se.moerakikemu.c_aimodellayer.IPlayer;
import de.htwg.se.moerakikemu.c_modellayer.Field;

public class Controller implements IController{

	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;	// Pointer to one of the players above
	
	private IField gameField;
	private int fieldLength;

	private ControllerHelper helper;
	private IControllerPlayer playerController;
	
	private String playerWin;
	private boolean gameEnds;
	
	public Controller(int fieldLength, IControllerPlayer playerCon) {
		gameField = new Field(fieldLength);
		this.fieldLength = fieldLength;
		this.playerController = playerCon;
		gameEnds = false;
		playerWin = null;
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
			playerController.addAPoint(player1);
		}
		if(counter1 == 4){ 
			playerController.addAPoint(player1);
			playerWin = playerController.getPlayer1Name();
			setEnd(true);
		}
		if(counter2 == 3 && counter1 == 1){
			playerController.addAPoint(player2);
		} 
		if(counter2 == 4){
			playerController.addAPoint(player2);
			playerWin = playerController.getPlayer2Name();
			setEnd(true);
		}
	}
	
	public String getWinner(){
		if(playerWin == null){
			if(playerController.getPlayer1Points() > playerController.getPlayer2Points()){
				playerWin = player1.getName();
			} else if(playerController.getPlayer1Points() < playerController.getPlayer2Points()){
				playerWin = player2.getName();
			}
		}
		return playerWin;
	}

	public boolean testIfWinnerExists() {
		return gameEnds;
	}
	
	public void setEnd(boolean end) {
		gameEnds = end;
	}
}
