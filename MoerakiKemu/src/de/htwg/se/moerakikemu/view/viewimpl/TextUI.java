package de.htwg.se.moerakikemu.view.viewimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;
import de.htwg.se.moerakikemu.view.UserInterface;


public class TextUI implements UserInterface {

	static Logger logger = (Logger) LogManager.getLogger(TextUI.class);
	
	IController myController;
	IControllerPlayer myPlayerController;

	public TextUI(IController controller, IControllerPlayer playerController) {
		myController = controller;
		myPlayerController = playerController;
		queryPlayerName();
	}

	/**
	 * Parses the input from the user and call controller things.
	 * 
	 * @param line Input from the user.
	 * @return
	 */
	public void processInputLine() {
	}
	
	public  void queryPlayerName() {
		logger.info("Bitte Namen des ersten Spielers eingeben: ");
		logger.info("Bitte Namen des zweiten Spielers eingeben: ");
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
		logger.info(myPlayerController.getPlayer1Name() + ": " + myPlayerController.getPlayer1Points() + " Punkte\n");
		logger.info(myPlayerController.getPlayer2Name() + ": " + myPlayerController.getPlayer2Points() + " Punkte\n");
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
		if(!"".equals(winner)){
			logger.info("Der Gewinner ist " + winner + "!!!\n");
		} else {
			logger.info("Unentschieden");
		}
	}

	@Override
	public void update() {
		drawCurrentState();
	}
}