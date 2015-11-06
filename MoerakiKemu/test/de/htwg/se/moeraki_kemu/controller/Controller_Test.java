package de.htwg.se.moeraki_kemu.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.moeraki_kemu.controller.Controller;

public class Controller_Test {
	Controller controller;
	
	@Before
	public void setUp(){
		controller = new Controller("Spieler1", "Spieler2");
	}
	
	
}