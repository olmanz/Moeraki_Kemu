package de.htwg.se.moerakikemu.view.view_impl.gui;

import javax.swing.JFrame;

import de.htwg.se.moerakikemu.view.UserInterface;

public class GUI extends JFrame implements UserInterface {
	private static final long serialVersionUID = 2078463309153663728L;

	public GUI() {
		this.setJMenuBar(new MainMenu());
	}

	@Override
	public void drawCurrentState() {
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
