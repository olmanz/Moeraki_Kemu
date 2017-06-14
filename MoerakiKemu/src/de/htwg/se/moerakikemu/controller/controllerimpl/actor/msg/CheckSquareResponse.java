package de.htwg.se.moerakikemu.controller.controllerimpl.actor.msg;

public class CheckSquareResponse {

	private int pointsPlayer1;
	private int pointsPlayer2;
	private int winner;
	
	public CheckSquareResponse(int pointsPlayer1, int pointsPlayer2, int winner) {
		this.pointsPlayer1 = pointsPlayer1;
		this.pointsPlayer2 = pointsPlayer2;
		this.winner = winner;
	}

	public int[] getResult() {
		return (new int[] { pointsPlayer1, pointsPlayer2, winner });
	}
	
}
