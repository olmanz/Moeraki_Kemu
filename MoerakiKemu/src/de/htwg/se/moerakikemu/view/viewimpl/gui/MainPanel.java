package de.htwg.se.moerakikemu.view.viewimpl.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.IControllerPlayer;


public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	IController myController;
	IControllerPlayer myPlayerController;

	ImageIcon black_icon;
	ImageIcon red_icon;

	GridLayout gridForSpots;
	JButton field[][];

	
	private int[] getButtonCoordinates(JButton button) {
		int []xyCoordinates = new int[2];
		
		Scanner getNumbers = new Scanner(button.getText()); 
		getNumbers.useDelimiter("[()/]");
		xyCoordinates[0] = getNumbers.nextInt();
		xyCoordinates[1] = getNumbers.nextInt();
		getNumbers.close();
		System.out.println("(" + xyCoordinates[0] + "/" + xyCoordinates[1] + ")");
		
		return xyCoordinates;
	}
	
	private void setSpotColor(JButton buttonToChange) {
		System.out.println(myPlayerController.getCurrentPlayerName());
		if (myPlayerController.getCurrentPlayerName().equals(myPlayerController.getPlayer1Name())) {
			buttonToChange.setIcon(black_icon);
		} else if (myPlayerController.getCurrentPlayerName().equals(myPlayerController.getPlayer2Name())) {
			buttonToChange.setIcon(red_icon);
		}
	}

	private MouseListener listener = new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
			JButton pressedButton = (JButton) me.getSource();
			
			// Occupy Spot
			int []coordinates = getButtonCoordinates(pressedButton);
			String name = myController.getIsOccupiedByPlayer(coordinates[0], coordinates[1]);
			if ("".equals(name)) {
				myController.occupy(coordinates[0], coordinates[1]);
			}
			setSpotColor(pressedButton);
		}
	};

	public MainPanel(IController controller, IControllerPlayer playerController, final int fieldLength) {
		super();
		this.myController = controller;
		this.myPlayerController = playerController;
		
		// Read and scale images for occupied spots
		black_icon = new ImageIcon("Spot_black.png");
		Image black_icon_img = black_icon.getImage();
		black_icon.setImage(black_icon_img.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		red_icon = new ImageIcon("Spot_red.png");
		Image red_icon_img = red_icon.getImage();
		red_icon.setImage(red_icon_img.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		gridForSpots = new GridLayout(fieldLength, fieldLength);
		this.setLayout(gridForSpots);
		field = new JButton[fieldLength][fieldLength];
		for (int i = 0; i < fieldLength; i++) {
			for (int j = 0; j < fieldLength; j++) {
				field[i][j] = new JButton();
				this.add(field[i][j]);
				field[i][j].addMouseListener(listener);
				field[i][j].setText("(" + (i+1) + "/" + (j+1) + ")");
				field[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
				field[i][j].setVerticalTextPosition(SwingConstants.CENTER);
				field[i][j].setOpaque(false);
				field[i][j].setContentAreaFilled(false);
				field[i][j].setBorderPainted(false);
			}
		}
	}
}
