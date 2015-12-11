package de.htwg.se.moerakikemu.a_view.gui;

import javax.swing.JFrame;

import de.htwg.se.moerakikemu.a_aiview.UserInterface;

public class GUI extends JFrame implements UserInterface {
	private static final long serialVersionUID = 2078463309153663728L;

	public GUI() {
		this.setJMenuBar(new MainMenu());
	}

	@Override
	public void drawCurrentState() {
		// TODO Auto-generated method stub
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
