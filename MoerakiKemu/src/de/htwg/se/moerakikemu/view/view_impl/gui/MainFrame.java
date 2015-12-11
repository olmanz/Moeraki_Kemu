package de.htwg.se.moerakikemu.view.view_impl.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -6915328769438463935L;

	GridLayout gridForSpots;
	JButton field[][];

	public MainFrame(final int fieldLength) {
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
}
