package de.htwg.se.moerakikemu.view.view_impl.gui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar {
	private static final long serialVersionUID = -6197107387907014280L;

	JMenu game;
	JMenuItem quit;

	public MainMenu() {
		this.setToolTipText("Easter Egg");
		
		// Create MenuItems
		quit = new JMenuItem("Beenden", KeyEvent.VK_B);
		
		// Create and Assemble game-menu
		game = new JMenu("Spiel");
		game.setMnemonic(KeyEvent.VK_S);
		game.add(quit);
		
		
		// Assemle the whole MenuBar
		this.add(game);
	}
}
