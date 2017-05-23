package de.htwg.se.moerakikemu.view.viewimpl.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.htwg.se.moerakikemu.controller.IController;

public class LoadDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private IController controller;
	private JTable table;
	private String[][] rowData;
	private static final int BUTTONS = 10;

	public LoadDialog(final IController controller) {

		this.controller = controller;
		JPanel mainPanel = new JPanel();
		this.rowData = controller.getRowDataAll();
		JPanel tablePanel = new JPanel();
		table = new JTable();
		table.getColumnModel().setSelectionModel(new DefaultListSelectionModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public int getLeadSelectionIndex() {
				return -1;
			}
		});
		tablePanel.setBorder(BorderFactory.createTitledBorder("Games"));
		tablePanel.add(new JScrollPane(table));
		mainPanel.add(tablePanel, BorderLayout.PAGE_END);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS, BUTTONS));
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowIdx = LoadDialog.this.table.getSelectedRow();
				if (rowIdx == -1) {
					JOptionPane.showMessageDialog(LoadDialog.this, "Please choose an entry first.");
					return;
				}
				String id = (String) LoadDialog.this.rowData[rowIdx][1];
				controller.deleteFromDB(id);
				LoadDialog.this.updateTable();
			}
		});
		buttonPanel.add(deleteButton);

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowIdx = LoadDialog.this.table.getSelectedRow();
				if (rowIdx == -1) {
					JOptionPane.showMessageDialog(LoadDialog.this, "Please choose an entry first.");
					return;
				}
				String id = (String) LoadDialog.this.rowData[rowIdx][1];
				controller.loadFromDB(id);
				LoadDialog.this.setVisible(false);
			}
		});
		buttonPanel.add(loadButton);

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoadDialog.this.setVisible(false);
			}
		});
		buttonPanel.add(exitButton);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

		updateTable();

		this.setContentPane(mainPanel);
		this.setResizable(false);
		this.setTitle("Load a Field from Database");
		this.pack();
		this.setVisible(true);
	}

	protected void updateTable() {
		this.rowData = controller.getRowDataAll();
		String[] columnNames = { "Name" };
		table.setModel(new DefaultTableModel(rowData, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

}
