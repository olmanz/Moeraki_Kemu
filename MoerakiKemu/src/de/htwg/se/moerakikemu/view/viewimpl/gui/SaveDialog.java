package de.htwg.se.moerakikemu.view.viewimpl.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.se.moerakikemu.controller.IController;

public class SaveDialog extends JDialog implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final int BUTTONS=10;

	private IController controller;
	private JTextField textField;

	private JOptionPane optionPane;

	private String btnString1 = "Save";
	private String btnString2 = "Cancel";

	public SaveDialog(JFrame frame, IController controller) {
		super(frame, true);

		this.controller = controller;

		setTitle("Save game...");

		JPanel contentPanel = new JPanel(new FlowLayout(BUTTONS));
		contentPanel.add(new JLabel("Name:"));
		textField = new JTextField(BUTTONS);
		textField.setText(controller.getFieldName());
		contentPanel.add(textField);

		Object[] options = { btnString1, btnString2 };

		// Create the JOptionPane.
		optionPane = new JOptionPane(contentPanel, JOptionPane.QUESTION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);

		setContentPane(optionPane);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
			}
		});

		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				textField.requestFocusInWindow();
			}
		});

		textField.addActionListener(this);
		optionPane.addPropertyChangeListener(this);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		optionPane.setValue(btnString1);
	}

	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();

		if (isVisible()
				&& (e.getSource() == optionPane)
				&& (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY
						.equals(prop))) {
			Object value = optionPane.getValue();

			if (value == JOptionPane.UNINITIALIZED_VALUE) {
				// ignore reset
				return;
			}

			optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

			if (btnString1.equals(value)) {
				controller.setFieldName(textField.getText());
				controller.saveToDB();
			}
			clearAndHide();
		}
	}

	public void clearAndHide() {
		textField.setText(null);
		setVisible(false);
	}
}
