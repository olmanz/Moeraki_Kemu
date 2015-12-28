package de.htwg.se.moerakikemu.view.viewimpl.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.htwg.se.moerakikemu.controller.IController;

public class MainMenu extends JMenuBar {
	private static final long serialVersionUID = -6197107387907014280L;

	
	IController myController;
	
	JMenu game;
	JMenuItem quit;
	//JMenuItem newGame;
	//JMenuItem pause;
	//JMenuItem resume;

	public MainMenu(IController controller) {
		this.myController = controller;

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


	MouseListener listener = new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
			JMenuItem pressedButton = (JMenuItem) me.getSource();
			
			if (pressedButton == quit) {
				// Quit Game
				myController.setEnd(true);
			} else {
				// Do nothing
			}
			// Do nothing
          }
	};
}
