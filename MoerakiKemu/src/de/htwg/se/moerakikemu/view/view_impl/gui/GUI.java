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


		black_icon = new ImageIcon("./Spot_black.png");
		black_icon = new ImageIcon("./Spot_red.png");
		
		int fieldLength = myController.getEdgeLength();
		gridForSpots = new GridLayout(fieldLength, fieldLength);
		this.setLayout(gridForSpots);
		
		field = new JButton[fieldLength][fieldLength];
		for (int i = 0; i < fieldLength; i++) {
			for (int j = 0; j < fieldLength; j++) {
				field[i][j] = new JButton("(" + i + "/" + j + ")");
				this.add(field[i][j]);
				field[i][j].addMouseListener(listener);
			}
		}//END OUTTER FOR

		this.setSize(1024, 768);
		this.setVisible(true);
		
		black_icon = new ImageIcon("Spot_black.png");
		Image black_icon_img = black_icon.getImage();
		black_icon.setImage(black_icon_img.getScaledInstance(field[0][0].getWidth(), field[0][0].getHeight(), Image.SCALE_SMOOTH));
		red_icon = new ImageIcon("Spot_red.png");
		Image red_icon_img = black_icon.getImage();
		red_icon.setImage(red_icon_img.getScaledInstance(field[0][0].getWidth(), field[0][0].getHeight(), Image.SCALE_SMOOTH));
		
	}

	// Listener
	MouseListener listener = new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
			JButton pressedButton = (JButton) me.getSource();
			
			pressedButton.setIcon(black_icon);
			
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
