package de.htwg.se.moerakikemu.view.viewimpl.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import de.htwg.se.moerakikemu.controller.IController;

public class MainMenu extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = -6197107387907014280L;

	private IController myController;
	private JFrame frame;
	private JMenu game;
	private JMenuItem quit;
	private JMenuItem newGame;
	private JMenu moreInfos;
	private JMenuItem help;
	private JMenuItem info;

	private JMenuItem saveToDB;
	private JMenuItem loadFromDB;

	public MainMenu(JFrame gui, final IController controller) {
		this.myController = controller;
		this.frame = gui;

		this.setToolTipText("Easter Egg");

		// Create MenuItems
		quit = new JMenuItem("Beenden");
		quit.setMnemonic(KeyEvent.VK_Q);
		quit.addActionListener(this);

		// Create MenuItems
		newGame = new JMenuItem("Neues Spiel");
		newGame.setMnemonic(KeyEvent.VK_N);
		newGame.addActionListener(this);

		// Create MenuItems
		help = new JMenuItem("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.addActionListener(this);

		// Create MenuItems
		info = new JMenuItem("Info");
		info.setMnemonic(KeyEvent.VK_I);
		info.addActionListener(this);

		// Create and Assemble game-menu
		game = new JMenu("Spiel");
		game.setMnemonic(KeyEvent.VK_S);
		game.add(newGame);

		saveToDB = new JMenuItem("Save to DB");
		saveToDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SaveDialog(frame, controller);
			}
		});
		game.add(saveToDB);
		loadFromDB = new JMenuItem("Load from DB");
		loadFromDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoadDialog(controller);
			}
		});
		game.add(loadFromDB);

		game.add(quit);
		
		// Create and Assemble game-menu
		moreInfos = new JMenu("?");
		moreInfos.setMnemonic(KeyEvent.VK_X);
		moreInfos.add(help);
		moreInfos.add(info);

		// Assemle the whole MenuBar
		this.add(game);
		this.add(moreInfos);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == quit) {
			myController.setEnd(true);
		} else if (source == newGame) {
			myController.newGame();
		} else if (source == help) {
			printHelper();
		} else if (source == info) {
			printInfo();
		}
	}

	private void printInfo() {
		JOptionPane.showMessageDialog(null, "This Game was developed from Etienne Gramlich and Daniel Hipp");
	}

	private void printHelper() {
		final String rules = "Spielregeln: \n " + "Ziel: \n"
				+ "    Das Ziel dieses Spiels ist es so viele Punkte bei Beenden des Spiels zu haben wie moeglich.\n"
				+ "Punkte:\n"
				+ "    Einen Punkt bekommt man in dem man bei einem Quadrat mehr Eckpunkte eingenommen hat als der Gegenspieler\n"
				+ "    Der Startpunkt wird als neutral betrachtet und gibt nimanden Punkte\n" + "Spielende:\n"
				+ "    Sobald ein Spieler vier Eckpunkte eines Quadrates eingenommen hat oder das komplette Spielfeld belegt ist, endet das Spiel\n"
				+ "    Alternativ ist es auch moeglich sich vom Startstein in gerader Richtung zu einem beliebigen Rand zu \"bewegen\" um das SPiel zu beenden";
		JOptionPane.showMessageDialog(null, rules);
	}
}
