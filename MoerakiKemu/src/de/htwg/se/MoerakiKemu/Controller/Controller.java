package de.htwg.se.MoerakiKemu.Controller;

import de.htwg.se.MoerakiKemu.Modellayer.Field;
import de.htwg.se.MoerakiKemu.Modellayer.Player;

public class Controller {

	private Player player1;
	private Player player2;
	
	private int pointsPlayer1;
	private int pointsPlayer2;
	
	private Field gameField;
	private int fieldLength;
	
	public Controller(final String player1Name, final String player2Name, final int fieldLength) {
		this.fieldLength = fieldLength;
		gameField = new Field(fieldLength);
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);
	}
	
	/**
	 * Occupation of a Spot by a player. Returns -1 if the Spot is already occupied.
	 * @param Xcoordinate X coordinate of the spot.
	 * @param Ycoordinate Y coordinate of the spot.
	 * @param playerName Name of the Player.
	 * @return The number of points (0 - 4) or -1 if the Spot is occupied.
	 */
	private int occupy(int Xcoordinate, int Ycoordinate, String playerName) {
		if(gameField.getIsOccupiedFrom(Xcoordinate, Ycoordinate) != "leer"){
			return -1;
		}
		int points = 0;
		
		testPositionOfPoint(Xcoordinate, Ycoordinate);

		
		// Calculate points and check if 4 spots of square selected
		int xMin = Xcoordinate - 1;
		int xMax = Xcoordinate + 1;
		int yMin = Ycoordinate - 1;
		int yMax = Ycoordinate + 1;
			
		return points;

	}
	
	/**
	 * This method test where the point is located
	 * @param XCoordinate
	 * @param YCoordinate
	 * @return 1 when the point is a edge, 2 if the point is a border - point and 3 if the point is somewhere in the middle of the field
	 */
	private void testPositionOfPoint(int XCoordinate, int YCoordinate){
		if((XCoordinate == 0 && YCoordinate == 0) || (XCoordinate == 0 && YCoordinate == fieldLength) ||
		   (XCoordinate == fieldLength && YCoordinate == 0) || (XCoordinate == fieldLength && YCoordinate == fieldLength)){
			testEdgePoint(XCoordinate, YCoordinate);
		} else if(XCoordinate == 0 || YCoordinate == 0 || XCoordinate == fieldLength || YCoordinate == fieldLength){
			//testBorderPoint(XCoordinate, YCoordinate);
		} else {
			//testNormalPoint(XCoordinate, YCoordinate);
		}
	}
	
	private void testEdgePoint(int xCoordinate, int yCoordinate){
		if(xCoordinate == 0 && yCoordinate == 0){
			testPoint(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
		}
		//WEITERE METHODEN
	}
	
	private void testPoint(int xMin, int yMin, int xMax, int yMax){
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
		
		//Ergebnis berechnen!
		
	}
}
