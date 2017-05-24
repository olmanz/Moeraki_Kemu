package de.htwg.se.moerakikemu.controller.controllerimpl.actor;

import akka.actor.UntypedAbstractActor;
import de.htwg.se.moerakikemu.controller.controllerimpl.actor.msg.CheckSquareRequest;
import de.htwg.se.moerakikemu.controller.controllerimpl.actor.msg.CheckSquareResponse;
import de.htwg.se.moerakikemu.modellayer.IField;

public class SquareActor extends UntypedAbstractActor {
	private int x, y, maxLength;
	private int[] squareArray;
	private boolean finished;
	private Square square;

	public SquareActor() {
		this.finished = false;
		this.squareArray = new int[17];
		this.square = new Square();
	}

	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof CheckSquareRequest) {
			receivedCheckSquareRequest((CheckSquareRequest) msg);
		}
	}

	private void receivedCheckSquareRequest(CheckSquareRequest msg) {
		x = msg.getPoint().x;
		y = msg.getPoint().y;
		maxLength = msg.getFieldLength();
		testSquare();
		int[] points = testListOfSquares(msg.getField());
		getSender().tell(new CheckSquareResponse(points[0], points[1]), getSelf());
	}

	private void testSquare() {
		square.test();
	}

	private int[] getSquareArray() {
		int[] returnArray = new int[squareArray.length];
		System.arraycopy(squareArray, 0, returnArray, 0, squareArray.length);
		return returnArray;
	}

	public void resetSquareTest() {
		square.resetSquare();
	}

	private class Square {
		private SquareState squareState;

		Square() {
			squareState = new EdgeSquare();
		}

		void setSquare(SquareState s) {
			squareState = s;
		}

		void resetSquare() {
			setSquare(new EdgeSquare());
		}

		void test() {
			while (!finished) {
				squareState.test(this);
			}
		}
	}

	interface SquareState {
		void test(Square square);
	}

	private class EdgeSquare implements SquareState {
		public void test(Square square) {
			if (x == 0 && y == 0) {
				squareArray[0] = 1;
				setArray(1, x, y, x + 1, y + 1);
				finished = true;
			} else if (x == 0 && y == maxLength) {
				squareArray[0] = 1;
				setArray(1, x, y, x + 1, y - 1);
				finished = true;
			} else if (x == maxLength && y == 0) {
				squareArray[0] = 1;
				setArray(1, x, y, x - 1, y + 1);
				finished = true;
			} else if (x == maxLength && y == maxLength) {
				squareArray[0] = 1;
				setArray(1, x, y, x - 1, y - 1);
				finished = true;
			} else {
				square.setSquare(new BorderSquare());
			}
		}
	}

	private class BorderSquare implements SquareState {

		BorderSquare() {
			test(square);
		}

		public void test(Square square) {
			if (x == 0) {
				squareArray[0] = 2;
				setArray(1, x, y, x + 1, y + 1);
				setArray(5, x, y, x + 1, y + 1);
				finished = true;
			} else if (y == 0) {
				squareArray[0] = 2;
				setArray(1, x, y, x + 1, y + 1);
				setArray(5, x, y, x - 1, y + 1);
				finished = true;
			} else if (x == maxLength) {
				squareArray[0] = 2;
				setArray(1, x, y, x - 1, y + 1);
				setArray(5, x, y, x - 1, y - 1);
				finished = true;
			} else if (y == maxLength) {
				squareArray[0] = 2;
				setArray(1, x, y, x - 1, y - 1);
				setArray(5, x, y, x + 1, y - 1);
				finished = true;
			} else {
				square.setSquare(new InnerSquare());
			}
		}
	}

	private class InnerSquare implements SquareState {
		InnerSquare() {
			test(square);
		}

		public void test(Square square) {
			if (x < maxLength && y < maxLength) {
				squareArray[0] = 4;
				setArray(1, x, y, x + 1, y - 1);
				setArray(5, x, y, x - 1, y - 1);
				setArray(9, x, y, x + 1, y + 1);
				setArray(13, x, y, x - 1, y + 1);
				finished = true;
			}
		}
	}

	private void setArray(final int start, final int edgeOne, final int edgeTwo, final int edgeThree,
			final int edgeFour) {
		squareArray[start] = edgeOne;
		squareArray[start + 1] = edgeTwo;
		squareArray[start + 2] = edgeThree;
		squareArray[start + 3] = edgeFour;
	}

	private int[] testListOfSquares(IField field) {
		int[] squareArray = getSquareArray();
		if (squareArray[0] == 1) {
			return testSquare(squareArray[1], squareArray[2], squareArray[3], squareArray[4], field);
		} else if (squareArray[0] == 2) {
			int[] points1 = testSquare(squareArray[1], squareArray[2], squareArray[3], squareArray[4], field);
			int[] points2 = testSquare(squareArray[5], squareArray[6], squareArray[7], squareArray[8], field);
			return (new int[] { points1[0] + points2[0], points1[1] + points2[1] });
		} else if (squareArray[0] == 4) {
			int[] points = new int[2];
			for (int i = 0; i < 4; i++) {
				int[] tmp = testSquare(squareArray[i * 4 + 1], squareArray[i * 4 + 2], squareArray[i * 4 + 3],
						squareArray[i * 4 + 4], field);
				points[0] += tmp[0];
				points[1] += tmp[1];
			}
			return points;
		}
		return null;
	}

	private int[] testSquare(int xMin, int yMin, int xMax, int yMax, IField field) {
		int index;
		index = checkOccupationReturnPlayerGettingPoint(xMin, yMin, field);
		int[] counterForPlayers = new int[2];
		if (index != -1) {
			counterForPlayers[index]++;
		}
		index = checkOccupationReturnPlayerGettingPoint(xMin, yMax, field);
		if (index != -1) {
			counterForPlayers[index]++;
		}
		index = checkOccupationReturnPlayerGettingPoint(xMax, yMin, field);
		if (index != -1) {
			counterForPlayers[index]++;
		}
		index = checkOccupationReturnPlayerGettingPoint(xMax, yMax, field);
		if (index != -1) {
			counterForPlayers[index]++;
		}
		return counterForPlayers;
	}

	private int checkOccupationReturnPlayerGettingPoint(final int x, final int y, IField field) {
		if (!"".equals(field.getIsOccupiedFrom(x, y))) {
			if (field.getIsOccupiedFrom(x, y).equals("Spieler 1")) {
				return 0;
			} else if (field.getIsOccupiedFrom(x, y).equals("Spieler 2")) {
				return 1;
			}
		}
		return -1;
	}

}
