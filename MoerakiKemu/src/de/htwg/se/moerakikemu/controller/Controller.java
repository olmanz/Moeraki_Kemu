package de.htwg.se.moerakikemu.controller;

import de.htwg.se.moerakikemu.modellayer.Field;
import de.htwg.se.moerakikemu.modellayer.Player;

public class Controller {

	private Player player1;
	private Player player2;
	private Player currentPlayer;	// Pointer to one of the players above
	
	private Field gameField;
	private int fieldLength;

	private ControllerHelper helper;
	
	private String playerWin;
	
	public Controller(int fieldLength) {
		gameField = new Field(fieldLength);
		this.fieldLength = fieldLength;
		player1 = new Player();
		player2 = new Player();
		currentPlayer = player1;	
		playerWin = null;
	}
	
	/**
	 * Determines the player that has the next turn.
	 * If no one is the current player then player1 begins.
	 * Else player1 and player2 are altering the next player.
	 */
	public void selectNextPlayer() {
		if(currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}
	
	/**
	 * Returns the name of the current player.
	 * @return String that may be empty, not null.
	 */
	public String getCurrentPlayerName() {
		return currentPlayer.getName();
	}
	
	/**
	 * Set the names for both player1 and player2.
	 * @param player1name Name for player1.
	 * @param player2name Name for player2.
	 */
	public void setName(String player1name, String player2name){
		player1.setName(player1name);
		player2.setName(player2name);
	}
	
	/**
	 * Returns the name of player1.
	 *
	 * @return A String, not null.
	 */
	public String getPlayer1Name() {
		return player1.getName();
	}

	/**
	 * Returns the name of player2.
	 *
	 * @return A String, not null.
	 */
	public String getPlayer2Name() {
		return player2.getName();
	}
	
	/**
	 * Returns the points of the first player.
	 *
	 * @return Amount of points, 0 or more.
	 */
	public int getPlayer1Points() {
		return player1.getPoints();
	}

	/**
	 * Returns the points of the second player.
	 *
	 * @return Amount of points, 0 or more.
	 */
	public int getPlayer2Points() {
		return player2.getPoints();
	}
	
	/**
	 * Returns the name of the player that occupies the field with the coordinates.
	 *
	 * @param x X-Coordinate.
	 * @param y Y-Coordinate.
	 * @return The name of the player or an empty String.
	 */
	public String getIsOccupiedByPlayer(int x, int y) {
		return gameField.getIsOccupiedFrom(x, y);
	}
	
	/**
	 * Returns the length of one edge of the game field.
	 *
	 * @return Number > 0.
	 */
	public int getEdgeLength() {
		return fieldLength;
	}
	
	/**
	 * Occupation of a Spot by a player. Returns -1 if the Spot is already occupied.
	 * @param xCoordinate X coordinate of the spot beginning from 1 to edgeLength.
	 * @param yCoordinate Y coordinate of the spot beginning from 1 to edgeLength.
	 * @param playerName Name of the Player.
	 * @return returns 0 if the current player occupied the field and got points;
	 * -1 if the spot already was occupied.
	 */
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
		helper = new ControllerHelper(x, y, (fieldLength - 1));
		helper.testSquare();
		testListOfSquares();
		helper.resetSquareTest();
//		testPositionOfPoint
		selectNextPlayer();
		return 0;
	}

	private void testListOfSquares(){
		int[] squareArray = new int[17];
		squareArray = helper.getSquareArray();
		for(int j = 0; j < squareArray.length; j++){
			System.out.print(squareArray[j]);
			if(j == 0 || (j+1) == 5 || (j+1) == 9 || (j+1) == 13 || (j+1) == 17){
				System.out.println();
			}
		}
		if(squareArray[0] == 1){
			testSquare(squareArray[1], squareArray[2], squareArray[3],squareArray[4]);
		} else if(squareArray[0] == 2){
			testSquare(squareArray[1], squareArray[2], squareArray[3],squareArray[4]);
			testSquare(squareArray[5], squareArray[6], squareArray[7],squareArray[8]);
		} else if(squareArray[0] == 4){
			testSquare(squareArray[1], squareArray[2], squareArray[3],squareArray[4]);
			testSquare(squareArray[5], squareArray[6], squareArray[7],squareArray[8]);
			testSquare(squareArray[9], squareArray[10], squareArray[11],squareArray[12]);
			testSquare(squareArray[13], squareArray[14], squareArray[15],squareArray[16]);
		}
	}
	
	/**
	 * test four positions and looks how much spots are occupied by players
	 * @param xMin Left lower corner of the square.
	 * @param yMin Right lower corner of the square.
	 * @param xMax Left upper corner of the square.
	 * @param yMax Right upper corner of the square.
	 */
	private void testSquare(int xMin, int yMin, int xMax, int yMax){
		int []counterForPlayers = {0, 0};
		System.out.println(xMin + " " + yMin + " " + xMax + " " + yMax);
		
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
	
	/**
	 * Returns the number of the player who occupies a Spot from 0 (for player1).
	 *
	 * @param x X-coordinates of the Spot.
	 * @param y Y-coordinates of the Spot.
	 * @return Returns the number of the player (>=0) or -1 if no player gets a point.
	 */
	int checkOccupationReturnPlayerGettingPoint(final int x, final int y) {
		if(!"".equals(gameField.getIsOccupiedFrom(x, y))){
			if(gameField.getIsOccupiedFrom(x, y).equals(player1.getName())) {
				return 0;
			} else if(gameField.getIsOccupiedFrom(x, y).equals(player2.getName())) {
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * Calculates and the points the players get by counting the number of occupied Spots in a square.
	 * @param counter1 Number of Spots occupied by player1 in the current square.
	 * @param counter2 Number of Spots occupied by player2 in the current square.
	 */

	private void setPointsOfPlayer(int counter1, int counter2){
		if(counter1 == 3  && counter2 == 1){
			player1.addPoints(1);
		}
		if(counter1 == 4){ 
			player1.addPoints(1);
			playerWin = player1.getName();
		}
		if(counter2 == 3 && counter1 == 1){
			player2.addPoints(1);
		} 
		if(counter2 == 4){
			player2.addPoints(1);
			playerWin = player2.getName();
		}
	}
	
	/**
	 * returns if a player has won
	 * @return the player who has won;
	 */
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
	
	/**
	 * returns the points of a player
	 * @param playerName
	 * @return the points of player one or player two or if there is no player with the given name, this method returns 0
	 */
	public int getPointsOfPlayer(String playerName){
		if(player1.getName().equals(playerName)){
			return player1.getPoints();
		} else if(player2.getName().equals(playerName)){
			return player2.getPoints();
		} else {
			return -1;
		}
	}

	/**
	 * test if there is a winner
	 * @return true if there is a winner, false when there isnt one;
	 */
	public boolean testIfWinnerExists(){
		if(playerWin != null){
			return true;
		}
		return false;
	}
}
