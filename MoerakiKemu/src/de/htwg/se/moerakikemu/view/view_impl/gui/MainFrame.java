package de.htwg.se.moerakikemu.view.view_impl.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -6915328769438463935L;

	Icon black_icon;
	Icon red_icon;
	
	GridLayout gridForSpots;
	JButton field[][];

	public MainFrame(final int fieldLength) {
		black_icon = new ImageIcon("./Spot_black.png");
		black_icon = new ImageIcon("./Spot_red.png");
		
		
		gridForSpots = new GridLayout(fieldLength, fieldLength);
		this.setLayout(gridForSpots);

		field = new JButton[fieldLength][fieldLength];
		for (int i = 0; i < fieldLength; i++) {
			for (int j = 0; j < fieldLength; j++) {
				field[i][j] = new JButton("(" + i + "/" + j + ")");
				field[i][j].addMouseListener(
						new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								// Do some crazy stuff
							}
						});
			}
		}//END OUTTER FOR
		
		// Create Grid-Layout for Buttons
	}//END CONSTRUCTOR
	
	void setSpotColor(final int playerNum, final int x, final  int y) {
		field[x][y].setIcon(playerNum == 0 ? black_icon : red_icon);
	}
}
