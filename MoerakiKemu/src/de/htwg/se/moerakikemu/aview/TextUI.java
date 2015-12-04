package de.htwg.se.moerakikemu.aview;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import de.htwg.se.moerakikemu.controller.Controller;


public class TextUI implements UserInterface {

	static Logger logger = LogManager.getLogger(TextUI.class);
	
	Controller myController;
	static Scanner scanner;

	public TextUI(Controller controller) {
		myController = controller;
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
		logger.info(myController.getCurrentPlayerName() + ", was tun Sie?");
		printOptions();
		while(true){
			String line = scanner.next();
			if("1".equals(line)){
				setSpot();
				if(myController.testIfWinnerExists()){
					Quit();
				}
				break;
			} else if("2".equals(line)){
				logger.warn("Spiel beendet!");
				printPoints();
				Quit();
			} else {
				logger.warn("Falsche Eingabe!\n");
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
			logger.info("Bitte gib eine X - Koordinate ein: ");	
			try{
				x = scanner.nextInt();
			}catch(InputMismatchException e){
				logger.warn("Falsche Eingabe!");
				errorInput = true;
				scanner.nextLine();
			}
			logger.info("Bitte gib eine Y - Koordinate ein: ");
			try{
				y = scanner.nextInt();
			}catch(InputMismatchException e){
				logger.warn("Falsche Eingabe!");
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
		logger.info("Bitte Namen des ersten Spielers eingeben: ");
		name1 = scanner.nextLine();
		logger.info("Bitte Namen des zweiten Spielers eingeben: ");
		name2 = scanner.nextLine();
		myController.setName(name1, name2);

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
			logger.info("\n");
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
		
		logger.info(builder.toString());
	}
	
	/**
	 * Prints the points for both players.
	 */
	private void printPoints(){
		logger.info(myController.getPlayer1Name() + ": " + myController.getPlayer1Points() + " Punkte\n");
		logger.info(myController.getPlayer2Name() + ": " + myController.getPlayer2Points() + " Punkte\n");
	}
	
	/**
	 * Draws a Spot with the Player identifier (i.e. number).
	 *
	 * @param playerChar Identifier for the player or ' ' if not occupied by player.
	 */
	private void drawSpot(final char playerChar) {
		logger.info("|" + playerChar + "|");
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
		logger.info(offset(edgeLength));
		for(int i = 0; i < edgeLength; i++) {
			logger.info("---");
		}
		logger.info("\n");
	}
	
	/**
	 * Prints a line with numbers above the game field that identifies the columns.
	 *
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
		logger.info(headlineBuilder.toString() + "\n");
	} 
	
	public void printMessage(String msg) {
		logger.info(msg + "\n");
	}
	
	/**
	 * Prints the winner and ends the game.
	 *
	 * @return the boolean  - value for the MoerakiKemu - class to finish the game.
	 */
	public void Quit(){
		String winner = myController.getWinner();
		if(winner != null){
			logger.info("Der Gewinner ist " + winner + "!!!\n");
		} else {
			logger.info("Unentschieden");
		}
	}
}
