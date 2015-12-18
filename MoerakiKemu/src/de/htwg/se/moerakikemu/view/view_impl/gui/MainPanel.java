package de.htwg.se.moerakikemu.view.view_impl.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;


	ImageIcon black_icon;
	ImageIcon red_icon;

	GridLayout gridForSpots;
	JButton field[][];

	// Listener
	MouseListener listener = new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
			JButton pressedButton = (JButton) me.getSource();
			
			pressedButton.setIcon(red_icon);
			
            System.out.println(pressedButton.getText());
          }
	};

	public MainPanel(final int fieldLength) {
		super();
		
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
		
		black_icon = new ImageIcon("Spot_black.png");
		Image black_icon_img = black_icon.getImage();
		black_icon.setImage(black_icon_img.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		red_icon = new ImageIcon("Spot_red.png");
		Image red_icon_img = red_icon.getImage();
		red_icon.setImage(red_icon_img.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
	}
}
