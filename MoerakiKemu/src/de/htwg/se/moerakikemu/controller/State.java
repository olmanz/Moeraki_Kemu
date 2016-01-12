package de.htwg.se.moerakikemu.controller;

public enum State {

	player_occupied,	// A Player is occupying a Spot
	query_player_name,	// At least the name of one Player is not set
	player_won,			// A Player won the game
	game_finished		// The game is ended by the user and the program must be aborted
	;
	
}
