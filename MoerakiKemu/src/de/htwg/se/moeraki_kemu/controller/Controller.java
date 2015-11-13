package de.htwg.se.moeraki_kemu.controller;

import de.htwg.se.moeraki_kemu.modellayer.Field;
import de.htwg.se.moeraki_kemu.modellayer.Player;

public class Controller {

	private Player player1;
	private Player player2;
	private Player currentPlayer;
	
	private int pointsPlayer1;
	private int pointsPlayer2;
	
	private Field gameField;
	private int fieldLength;
	
	public Controller() {
		this("","");
	}
	
	public Controller(final String player1Name, final String player2Name) {
		gameField = new Field();
		this.fieldLength = gameField.getEdgeLength();
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);
		currentPlayer = player1;
	}
	
	public void selectNextPlayer() {
		if(currentPlayer == null) {
			currentPlayer = player1;
		} else if(currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}
	
	public String getCurrentPlayerName() {
		if(currentPlayer != null) {
			return currentPlayer.getName();
		} else {
			return "";
		}
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
		return pointsPlayer1;
	}

	/**
	 * Returns the points of the second player.
	 *
	 * @return Amount of points, 0 or more.
	 */
	public int getPlayer2Points() {
		return pointsPlayer2;
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
	 * @param xCoordinate X coordinate of the spot.
	 * @param yCoordinate Y coordinate of the spot.
	 * @param playerName Name of the Player.
	 * @return The number of points (0 - 4) or -1 if the Spot is occupied.
	 */
	public int occupy(int xCoordinate, int yCoordinate) {
		if(gameField.getIsOccupiedFrom(xCoordinate, yCoordinate) != "leer"){
			return -1;
		}		
		testPositionOfPoint(xCoordinate, yCoordinate);	
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
	
	/*
	 * helper  - Method to the "testPositionOfPoint" - Method. Test if the point is on a edge
	 */
	private boolean testIsEdge(int xCoordinate, int yCoordinate){
		if((xCoordinate == 0 && yCoordinate == 0)||(xCoordinate == 0 && yCoordinate == fieldLength)||(xCoordinate == fieldLength && yCoordinate == 0)||(xCoordinate == fieldLength && yCoordinate == fieldLength)){
			return true;
		}
		return false;
	}
	
	/*
	 * helper  - Method to the "testPositionOfPoint" - Method. Test if the point is on a border
	 */
	private boolean testIsBorder(int xCoordinate, int yCoordinate){
		if(xCoordinate == 0 || yCoordinate == 0 || xCoordinate == fieldLength || yCoordinate == fieldLength){
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
		if(xCoordinate == 0 && yCoordinate == 0){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
		}
		if(xCoordinate == 0 && yCoordinate == fieldLength){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
		}
		if(xCoordinate == fieldLength && yCoordinate == 0){
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate + 1);
		}
		if(xCoordinate == fieldLength && yCoordinate == fieldLength){
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
		if(yCoordinate == fieldLength){
			testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
			testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate - 1);
		}
		if(xCoordinate == fieldLength){
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
		if(counter1 == 3){
			player1.addPoints(1);
		}
		if(counter1 == 4){
			player1.addPoints(1);
			//*The End?
		}
		if(counter2 == 3){
			player2.addPoints(1);
		} 
		if(counter2 == 4){
			player2.addPoints(1);
			//*The End?
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
			return 0;
		}
	}
}
