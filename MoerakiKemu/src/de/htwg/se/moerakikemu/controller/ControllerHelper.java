package de.htwg.se.moerakikemu.controller;


public class ControllerHelper {
	private int x,y, maxLength;
	
	public ControllerHelper (int xCoordinates, int yCoordinates, int maxLength){
		this.x = xCoordinates;
		this.y = yCoordinates;
		this.maxLength = maxLength;
	}

	
	class Square {
		private SquareState squareState;
		
		public Square(){
			squareState = new EdgeSquare();
		}
		
		public void setSquare(SquareState s){
			squareState = s;
		}
		
		public void test(){
			squareState.test(this);
		}
	}
	
	interface SquareState{
		public void test(Square square);
	}
	
	class InnerSquare implements SquareState{
		public void test(Square square){
			
		}
	}
	
	class EdgeSquare implements SquareState{
		public void test(Square square){
			if((x == 0 && y == 0) || (x == 0 && y == maxLength) || (x == maxLength && y == 0) || (x == maxLength && y == maxLength)){
				//BERECHNUG STARTEN
			}
			else {
				//STATUS ZU BORDERSQUARE WECHSELN
			}
		}
	}
	
	class BorderSquare implements SquareState{
		public void test(Square square){
			
		}
	}
/*
	private void testPositionOfPoint(int xCoordinate, int yCoordinate){
		if(testIsEdge(xCoordinate, yCoordinate)){
			testEdgeSquare(xCoordinate, yCoordinate);
		} else if(testIsBorder(xCoordinate, yCoordinate)){
			testBorderSquare(xCoordinate, yCoordinate);
		} else {
			testInnerSquare(xCoordinate, yCoordinate);
		}
	}
	

	public boolean testIsEdge(int xCoordinate, int yCoordinate){
		int maxLength = fieldLength - 1;

		boolean leftUpperCorner = xCoordinate == 0 && yCoordinate == 0;
		boolean leftLowerCorner = xCoordinate == 0 && yCoordinate == maxLength;
		boolean rightUpperCorner = xCoordinate == maxLength && yCoordinate == 0;
		boolean rightLowerCorner = xCoordinate == maxLength && yCoordinate == maxLength;

		if (leftUpperCorner || leftLowerCorner || rightUpperCorner || rightLowerCorner){
			return true;
		}
		return false;
	}
	

	public boolean testIsBorder(int xCoordinate, int yCoordinate){
		if(xCoordinate == 0 || yCoordinate == 0 || xCoordinate == fieldLength - 1 || yCoordinate == fieldLength - 1){
			return true;
		}
		return false;
	}
	

	public void testEdgeSquare(int xCoordinate, int yCoordinate){
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

	public void testBorderSquare(int xCoordinate, int yCoordinate){
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

	public void testInnerSquare(int xCoordinate, int yCoordinate){
		testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate - 1);
		testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate - 1);
		testSquare(xCoordinate, yCoordinate, xCoordinate + 1, yCoordinate + 1);
		testSquare(xCoordinate, yCoordinate, xCoordinate - 1, yCoordinate + 1);
	}
	 */

}
