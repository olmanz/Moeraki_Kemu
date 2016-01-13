package de.htwg.se.moerakikemu.controller;

import com.google.inject.AbstractModule;

import de.htwg.se.moerakikemu.controller.controllerimpl.Controller;
import de.htwg.se.moerakikemu.controller.controllerimpl.ControllerPlayer;

public class ControllerModuleWithController extends AbstractModule {

	@Override
	protected void configure() {
		bind(IController.class).to(Controller.class);
		bind(IControllerPlayer.class).to(ControllerPlayer.class);
	}

}
