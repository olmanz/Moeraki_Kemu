package de.htwg.se.moerakikemu.modellayer;

import com.google.inject.AbstractModule;

import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Player;

public class ModelControllerWithFieldPlayer extends AbstractModule {

	@Override
	protected void configure() {
		bind(IField.class).to(Field.class);
		bind(IPlayer.class).to(Player.class);
	}

}
