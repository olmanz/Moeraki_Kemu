package de.htwg.se.moerakikemu.aview;

import java.util.Scanner;
import java.util.logging.Logger;

import de.htwg.se.moerakikemu.controller.Controller;

public class TextUI implements UserInterface {

	private Logger logger = Logger. getLogger("de.htwg.sudoku.aview.tui");
	
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
	public boolean processInputLine() {
		logger.info(myController.getCurrentPlayerName() + ", was tun Sie?\n");
		printOptions();
		logger.info("\t>> ");
		boolean end = false;
		while(!end){
			String line = scanner.next();
			if("1".equals(line)){
				setSpot();
				end = true;
			} else if("2".equals(line)){
				printPoints();
				end = true;
			} else {
				logger.info("Falsche Eingabe!\n");
				printOptions();
			}
		}
		return false;
	}
	
	/**
	 * Prints the options that the player can do with the game.
	 */
	private void printOptions() {
		logger.info("1) Setzen\n");
		logger.info("2) Beenden\n");
	}
	
	/**
	 * Queries the X-/Y-Coordinate from CommandLine to set a Spot.
	 * If the spot is occupied it keeps asking for new coordinates.
	 */
	private void setSpot(){
		int err = -1;
		do{
			logger.info("Bitte gib eine X - Koordinate ein: ");
			int x = scanner.nextInt();
			logger.info("Bitte gib eine Y - koordinate ein: ");
			int y = scanner.nextInt();
			
			err = myController.occupy(x, y);
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
		logger.info(myController.getPlayer1Name() + ": " + myController.getPlayer1Points() + " points\n");
		logger.info(myController.getPlayer2Name() + ": " + myController.getPlayer2Points() + " points\n");
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
		logger.info(headlineBuilder.toString() + "\n");
	} 
	
	public void printMessage(String msg) {
		logger.info(msg + "\n");
	}
	
	/**
	 * prints the winner and end the game
	 * @return the boolean  - value for the MoerakiKemu - class to finish the game.
	 */
	public boolean testQuit(){
		String winner = myController.getWinner();
		if(winner != null){
			logger.info("The Winner is player " + winner + "!!!\n");
			return true;
		}
		return false;
	}
}
