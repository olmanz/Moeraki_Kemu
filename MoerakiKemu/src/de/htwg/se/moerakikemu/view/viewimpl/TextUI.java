package de.htwg.se.moerakikemu.view.viewimpl;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.htwg.se.moerakikemu.controller.*;
import de.htwg.se.moerakikemu.view.UserInterface;

public class TextUI implements UserInterface {

	IController myController;
	IControllerPlayer playerController;
	static Scanner scanner;
	String wrongInputMsg = "Falsche Eingabe!";

	public TextUI(IController controller, IControllerPlayer sampler) {
		this.myController = controller;
		this.playerController = sampler;
		scanner = new Scanner(System.in);
		queryPlayerName();
	}

	/**
	 * Parses the input from the user and call controller things.
	 * 
	 * @param line Input from the user.
	 * @return
	 */
	public void processInputLine() {		
		System.out.println(playerController.getCurrentPlayerName() + ", was tun Sie?");
		printOptions();
		boolean endOfGame = false;
		while(!endOfGame){
			String line = scanner.next();
			if("1".equals(line)){
				setSpot();
				endOfGame = true;
			} else if("2".equals(line)){
				System.out.println("Spiel beendet!");
				printPoints();
				myController.setEnd(true);
				endOfGame = true;
			} else {
				System.out.println(wrongInputMsg);
				printOptions();
			}
		}
	}
	
	/**
	 * Prints the options that the player can do with the game.
	 */
	private void printOptions() {
		System.out.println("1) Setzen");
		System.out.println("2) Beenden");
		System.out.print("\t>> ");
	}
	
	/**
	 * Queries the X-/Y-Coordinate from CommandLine to set a Spot.
	 * If the spot is occupied it keeps asking for new coordinates.
	 */
	private void setSpot(){
		int err = -1;
		boolean errorInput;
		int x = 0, y = 0;
		do{
			errorInput = false;
			System.out.print("Bitte gib eine X - Koordinate ein: ");	
			try{
				x = scanner.nextInt();
			}catch(InputMismatchException e){
				System.out.println(wrongInputMsg);
				errorInput = true;
				scanner.nextLine();
			}
			System.out.print("Bitte gib eine Y - Koordinate ein: ");
			try{
				y = scanner.nextInt();
			}catch(InputMismatchException e){
				System.out.println(wrongInputMsg);
				errorInput = true;
				scanner.nextLine();
			}
			
			if(!errorInput){
				err = myController.occupy(x, y);
			}
		}while(err == -1);
	}
	
	public  void queryPlayerName() {
		String name1;
		String name2;
		System.out.print("Bitte Namen des ersten Spielers eingeben: ");
		name1 = scanner.nextLine();
		System.out.print("Bitte Namen des zweiten Spielers eingeben: ");
		name2 = scanner.nextLine();
		playerController.setName(name1, name2);

	}
	
	/**
	 * Draws all Spots as squares with an Player-identifying character in it.
	 * ------
	 * |1||2|...
	 * ------
	 *    .
	 *    .
	 *    .
	 * Player 1: x points
	 * Player 2: x points
	 */
	public void drawCurrentState() {
		int edgeLength = myController.getEdgeLength();
		
		printColumnIdentifiers(edgeLength);
		for(int i = 0; i < edgeLength; i++) {
			printLine(edgeLength);
			printLeadingNumber(i + 1, edgeLength);
			for(int j = 0; j < edgeLength; j++) {
				String playerString = myController.getIsOccupiedByPlayer(i, j);
				char id = playerString.isEmpty() ? ' ' : playerString.charAt(0);
				drawSpot(id);
			}
			System.out.println();
		}
		printLine(edgeLength);
		printPoints();
		
	}

	private void printLeadingNumber(final int currentNumber, final int edgeLength) {
		int offset = offset(edgeLength).length();
		
		StringBuilder builder = new StringBuilder(Integer.toString(currentNumber));
		
		while(builder.length() < offset) {
			builder.append(" ");
		}
		
		System.out.print(builder.toString());
	}
	
	/**
	 * Prints the points for both players.
	 */
	private void printPoints(){
		System.out.println(playerController.getPlayer1Name() + ": " + playerController.getPlayer1Points() + " Punkte");
		System.out.println(playerController.getPlayer2Name() + ": " + playerController.getPlayer2Points() + " Punkte");
	}
	
	/**
	 * Draws a Spot with the Player identifier (i.e. number).
	 *
	 * @param playerChar Identifier for the player or ' ' if not occupied by player.
	 */
	private void drawSpot(final char playerChar) {
		System.out.print("|" + playerChar + "|");
	}
	
	/**
	 * Calculates the offset for the game field according to the length of the
	 * number representing the edge length.
	 *
	 * @param edgeLength Length of the edge of the game field.
	 * @return The empty spaces for offset as String, not null.
	 */
	private String offset(int edgeLength) {
		int numDigits = String.valueOf(edgeLength).length();

		StringBuilder offsetBuilder = new StringBuilder();
		for (int l = 0; l < numDigits; l++) {
			offsetBuilder.append(" ");
		}
		return offsetBuilder.toString();
	}
	
	/**
	 * Prints a horizontal line for separating lines of Spots.
	 * 
	 * @param edgeLength Number of Spots per edge.
	 */
	private void printLine(int edgeLength) {
		System.out.print(offset(edgeLength));
		for(int i = 0; i < edgeLength; i++) {
			System.out.print("---");
		}
		System.out.println();
	}
	
	/**
	 * Prints a line with numbers above the game field that identifies the collumns.
	 * @param edgeLength Length of the game field.
	 */
	private void printColumnIdentifiers(final int edgeLength) {
		StringBuilder headlineBuilder = new StringBuilder(offset(edgeLength));
		for (int i = 1; i <= edgeLength; i++) {
			if(i < 10){
				headlineBuilder.append(" ").append(i).append(" ");
			} else {
				headlineBuilder.append(i).append(" ");
			}
		}
		System.out.println(headlineBuilder.toString());
	} 
	
	public void printMessage(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * prints the winner and end the game
	 * @return the boolean  - value for the MoerakiKemu - class to finish the game.
	 */
	public void Quit(){
		String winner = myController.getWinner();
		if(winner != null){
			System.out.println("Der Gewinner ist " + winner + "!!!");
		} else {
			System.out.println("Unentschieden");
		}
	}
}
