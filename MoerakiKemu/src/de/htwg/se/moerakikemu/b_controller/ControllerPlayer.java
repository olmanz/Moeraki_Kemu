package de.htwg.se.moerakikemu.b_controller;

import de.htwg.se.moerakikemu.b_aicontroller.IControllerPlayer;
import de.htwg.se.moerakikemu.c_aimodellayer.IPlayer;
import de.htwg.se.moerakikemu.c_modellayer.Player;

public class ControllerPlayer implements IControllerPlayer {
	private IPlayer player1;
	private IPlayer player2;
	
	private IPlayer currentPlayer;
	
	public ControllerPlayer(){
		player1 = new Player();
		player2 = new Player();
		currentPlayer = player1;
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
	
	public void addAPoint(IPlayer player){
		if(player.getName() == player1.getName()){
			player1.addPoints(1);
		} else if(player2.getName() == player2.getName()){
			player2.addPoints(1);
		}
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

}
