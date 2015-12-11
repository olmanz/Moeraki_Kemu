package de.htwg.se.moerakikemu.view.view_impl.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.view.UserInterface;

public class GUI extends JFrame implements UserInterface {
	private static final long serialVersionUID = 2078463309153663728L;

	IController myController;

	Icon black_icon;
	Icon red_icon;
	
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
				field[i][j].addMouseListener(
						new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								// Do some crazy stuff
							}
						});
			}
		}//END OUTTER FOR
		
		this.setSize(1024, 768);
		this.setVisible(true);
		
	}

	void setSpotColor(final int playerNum, final int x, final  int y) {
		field[x][y].setIcon(playerNum == 0 ? black_icon : red_icon);
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
