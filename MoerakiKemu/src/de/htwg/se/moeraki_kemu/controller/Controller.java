package de.htwg.se.moeraki_kemu.controller;

import de.htwg.se.moeraki_kemu.modellayer.Field;
import de.htwg.se.moeraki_kemu.modellayer.Player;

public class Controller {

	private Player player1;
	private Player player2;
	private Player currentPlayer;	// Pointer to one of the players above
	
	private Field gameField;
	private int fieldLength;
	
	public Controller(int fieldLength) {
		gameField = new Field(fieldLength);
		this.fieldLength = fieldLength;
		player1 = new Player();
		player2 = new Player();
		currentPlayer = player1;
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
	 * Ends the program (and all UIs).
	 */
	public void quitGame(){
		System.exit(0);
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
	 * -1 if the spot already was occpuied.
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
		testPositionOfPoint(x, y);
		selectNextPlayer();
		return 0;
	}
	
	/**
	 * This method test where the point is located
	 * @param xCoordinate
	 * @param yCoordinate
	 * @return 1 when the point is a edge, 2 if the point is a border - point and 3 if the point is somewhere in the middle of the field
	 */
	private void testPositionOfPoint(int xCoordinate, int yCoordinate){
		if(testIsEdge(xCoordinate, yCoordinate)){
			testEdgeSquare(xCoordinate, yCoordinate);
		} else if(testIsBorder(xCoordinate, yCoordinate)){
			testBorderSquare(xCoordinate, yCoordinate);
		} else {
			testInnerSquare(xCoordinate, yCoordinate);
		}
	}
	
	/**
	 * helper  - Method to the "testPositionOfPoint" - Method. Test if the point is on a edge
	 */
	private boolean testIsEdge(int xCoordinate, int yCoordinate){
		int maxLength = fieldLength - 1;
		if((xCoordinate == 0 && yCoordinate == 0)||(xCoordinate == 0 && yCoordinate == maxLength)||(xCoordinate == maxLength && yCoordinate == 0)||(xCoordinate == maxLength && yCoordinate == maxLength)){
			return true;
		}
		return false;
	}
	
	/**
	 * helper  - Method to the "testPositionOfPoint" - Method. Test if the point is on a border
	 */
	private boolean testIsBorder(int xCoordinate, int yCoordinate){
		if(xCoordinate == 0 || yCoordinate == 0 || xCoordinate == fieldLength - 1 || yCoordinate == fieldLength - 1){
			return true;
		}
		return false;
	}
	
	/**
	 * test the points around the main point if it is a point at a edge
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	private void testEdgeSquare(int xCoordinate, int yCoordinate){
		int maxLength = fieldLength - 1;
		if(xCoordinate == 0 && yCoordinate == 0){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
		}
		if(xCoordinate == 0 && yCoordinate == maxLength){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
		}
		if(xCoordinate == maxLength && yCoordinate == 0){
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate + 1);
		}
		if(xCoordinate == maxLength && yCoordinate == maxLength){
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate - 1);
		}
	}
	
	/**
	 * test the points around the main point if it is a point at a border
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	private void testBorderSquare(int xCoordinate, int yCoordinate){
		if(yCoordinate == 0){
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate + 1);
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
		}
		if(yCoordinate == fieldLength - 1){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate - 1);
		}
		if(xCoordinate == fieldLength - 1){
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate + 1);
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate - 1);
		}
		if(xCoordinate == 0){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
		}
	}
	
	/**
	 * test the spots around the main point if it is a normal point in the middle of the map
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	private void testInnerSquare(int xCoordinate, int yCoordinate){
		testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
		testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate - 1);
		testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
		testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate + 1);
	}
	
	/**
	 * test four positions and looks how much spots are occupied by players
	 * @param xMin
	 * @param yMin
	 * @param xMax
	 * @param yMax
	 */
	private void testSquare(int xMin, int yMin, int xMax, int yMax){
		int counterPlayer1 = 0;
		int counterPlayer2 = 0;
		
		if(gameField.getIsOccupiedFrom(xMin,yMin) != ""){
			if(gameField.getIsOccupiedFrom(xMin, yMin) == player1.getName()){
				counterPlayer1 += 1;
			} else if(gameField.getIsOccupiedFrom(xMin, yMin) == player2.getName()){
				counterPlayer2 += 1;
			}
		} 
		if(gameField.getIsOccupiedFrom(xMin, yMax) != ""){
			if(gameField.getIsOccupiedFrom(xMin, yMax) == player1.getName()){
				counterPlayer1 += 1;
			} else if(gameField.getIsOccupiedFrom(xMin, yMax) == player2.getName()){
				counterPlayer2 += 1;
			}
		} 
		if(gameField.getIsOccupiedFrom(xMax, yMin) != ""){
			if(gameField.getIsOccupiedFrom(xMax, yMax) == player1.getName()){
				counterPlayer1 += 1;
			} else if(gameField.getIsOccupiedFrom(xMax, yMax) == player2.getName()){
				counterPlayer2 += 1;
			}
		}
		if(gameField.getIsOccupiedFrom(xMax, yMax) != ""){
			if(gameField.getIsOccupiedFrom(xMax, yMax) == player1.getName()){
				counterPlayer1 += 1;
			} else if(gameField.getIsOccupiedFrom(xMax, yMax) == player2.getName()){
				counterPlayer2 += 1;
			}
		}
		
		getPointsOfPlayer(counterPlayer1, counterPlayer2);
		
	}
	
	/**
	 * add the points the players get
	 * @param counter1
	 * @param counter2
	 */
	private void getPointsOfPlayer(int counter1, int counter2){
		if(counter1 == 3  && counter2 == 1){
			player1.addPoints(1);
		}
		if(counter1 == 4){ 
			player1.addPoints(1);
			// Notify UIs:
			// Print points with message
			// Then quit
		}
		if(counter2 == 3 && counter1 == 1){
			player2.addPoints(1);
		} 
		if(counter2 == 4){
			player2.addPoints(1);
			// Notify UIs:
			// Print points with message
			// Then quit
		}
	}
	
	/**
	 * returns the points of a player
	 * @param playerName
	 * @return the points of player one or player two or if there is no player with the given name, this method returns 0
	 */
	public int getPointsOfPlayer(String playerName){
		if(player1.getName() == playerName){
			return player1.getPoints();
		} else if(player2.getName() == playerName){
			return player2.getPoints();
		} else {
			return -1;
		}
	}
}
