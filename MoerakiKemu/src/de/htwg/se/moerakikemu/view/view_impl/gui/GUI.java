package de.htwg.se.moerakikemu.view.view_impl.gui;

import javax.swing.JFrame;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.view.UserInterface;

public class GUI extends JFrame implements UserInterface {
	private static final long serialVersionUID = 2078463309153663728L;

	IController myController;

	public GUI(IController newController) {
		this.myController = newController;

		this.setJMenuBar(new MainMenu());
		this.add(new MainFrame(myController.getEdgeLength()));
	}

	@Override
	public void drawCurrentState() {
		// TODO Determine which Spots are occupied by which Player
		this.repaint();
	}

	@Override
	public void queryPlayerName() {
		// TODO Auto-generated method stub
	}

	@Override
	public void printMessage(String msg) {
		// TODO Auto-generated method stub
		
	}

}
