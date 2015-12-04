package de.htwg.se.moerakikemu.controller;

public class ControllerHelper {
	private int x,y, maxLength;
	private int[] squareArray;
	
	public ControllerHelper (int xCoordinates, int yCoordinates, int maxLength){
		this.x = xCoordinates;
		this.y = yCoordinates;
		this.maxLength = maxLength;
		this.squareArray = new int[16];
	}

	public void testSquare(){
		Square square = new Square();
		square.test();
	}
	
	public int[] getSquareArray(){
		return squareArray;
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
	
	class EdgeSquare implements SquareState{
		public void test(Square square){
			if(x == 0 && y == 0){
				setArray(0, x, y, x + 1, y + 1);
			} else if(x == 0 && y == maxLength){
				setArray(0, x, y, x + 1, y - 1);
			} else if(x == maxLength && y == 0){
				setArray(0, x, y, x - 1, y + 1);
			} else if(x == maxLength && y == maxLength){
				setArray(0, x, y, x - 1, y - 1);
			}else {
				square.setSquare(new BorderSquare());
			}
		}
	}
	
	class BorderSquare implements SquareState{
		public void test(Square square){
			if(x == 0){
				setArray(0, x, y, x - 1, y + 1);
				setArray(4, x, y, x + 1, y + 1);
			} else if(y == 0){
				setArray(0, x, y, x + 1, y - 1);
				setArray(4, x, y, x - 1, y - 1);
			} else if(x == maxLength){
				setArray(0, x, y, x - 1, y + 1);
				setArray(4, x, y, x - 1, y - 1);
			} else if(y == maxLength){
				setArray(0, x, y, x + 1, y + 1);
				setArray(4, x, y, x + 1, y - 1);
			} else {
				square.setSquare(new InnerSquare());
			}
		}
	}
	
	class InnerSquare implements SquareState{
		public void test(Square square){
			if(x < maxLength && y < maxLength){
				setArray(0, x, y, x + 1, y - 1);
				setArray(4, x, y, x - 1, y - 1);
				setArray(8, x, y, x + 1, y + 1);
				setArray(12, x, y, x - 1, y + 1);
			} else {
				square.setSquare(new FalseSquare());
			}
		}
	}
	
	class FalseSquare implements SquareState{
		public void test(Square square){
			setArray(0, maxLength+1,0 ,0 ,0);
		}
	}
	
	private void setArray(int start, int edgeOne, int edgeTwo, int edgeThree, int edgeFour){
		squareArray[start] = edgeOne;
		squareArray[start + 1] = edgeTwo;
		squareArray[start + 2] = edgeThree;
		squareArray[start + 3] = edgeFour;
	}
}
