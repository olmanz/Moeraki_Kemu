package de.htwg.se.moerakikemu.controller;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import de.htwg.se.moerakikemu.controller.controllerimpl.Controller;
import de.htwg.se.moerakikemu.controller.controllerimpl.ControllerPlayer;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;

public class ControllerModuleWithController extends AbstractModule {

	@Override
	protected void configure() {
		bind(Integer.class).annotatedWith(Names.named("fieldLength")).toInstance(12);
		bind(IFieldDAO.class).to(de.htwg.se.moerakikemu.persistence.hibernate.FieldHibernateDAO.class);
		bind(IController.class).to(Controller.class);
		bind(IControllerPlayer.class).to(ControllerPlayer.class);
	}

}
