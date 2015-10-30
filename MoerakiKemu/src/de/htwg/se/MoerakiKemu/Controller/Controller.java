package de.htwg.se.MoerakiKemu.Controller;

import de.htwg.se.MoerakiKemu.Modellayer.Field;
import de.htwg.se.MoerakiKemu.Modellayer.Player;

public class Controller {

	Player player1;
	Player player2;
	
	Field gameField;
	
	public Controller(final String player1Name, final String player2Name) {
		gameField = new Field();
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);
	}
	
}
