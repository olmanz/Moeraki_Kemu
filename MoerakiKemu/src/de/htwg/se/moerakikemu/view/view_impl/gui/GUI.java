package de.htwg.se.moerakikemu.view.view_impl.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.view.IViewsObserver;
import de.htwg.se.moerakikemu.view.UserInterface;

public class GUI extends JFrame implements UserInterface, IViewsObserver {
	private static final long serialVersionUID = 2078463309153663728L;

	IController myController;

	ImageIcon black_icon;
	ImageIcon red_icon;
	
	GridLayout gridForSpots;
	JButton field[][];
	
	
	public GUI(IController newController) {
		super("Moeraki Kemu");
		this.myController = newController;

		this.setJMenuBar(new MainMenu());

		this.add(new MainPanel(myController.getEdgeLength()));

		this.setSize(1024, 768);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	// Listener
	MouseListener listener = new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
			JButton pressedButton = (JButton) me.getSource();
			
			pressedButton.setIcon(red_icon);
			
            System.out.println(pressedButton.getText());
          }
	};
	/*
	void setSpotColor(final int playerNum, final int x, final  int y) {
		field[x][y].setIcon(playerNum == 0 ? black_icon : red_icon);
	}*/

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCurrentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryPlayerName() {
		// TODO Auto-generated method stub
		String player1Name = "";
		String player2Name = "";

		while ("".equals(player1Name)) {
			// Input Dialog
		}

		while ("".equals(player2Name)) {
			// Input Dialog
		}
		
		// Set Names
	}

	@Override
	public void printMessage(String msg) {
		// TODO Auto-generated method stub
		
	}


}
