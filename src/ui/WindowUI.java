package ui;

import game.Table;

import javax.swing.*;
import java.awt.*;

public class WindowUI extends JFrame implements GameUI {
	JPanel pnl_main;
	JPanel pnl_table;
	JPanel pnl_keyboard;

	public WindowUI() {
		setTitle("Wordle");
		setPreferredSize(new Dimension(1366, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setComponents();

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void setComponents() {
		pnl_main = new JPanel(new GridBagLayout());
		pnl_main.setBackground(Color.WHITE);
		add(pnl_main);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		pnl_table = new JPanel();
		pnl_table.setPreferredSize(new Dimension(270, 330));
		pnl_table.setBackground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 0;
		pnl_main.add(pnl_table, gbc);

		pnl_keyboard = new JPanel();
		pnl_keyboard.setPreferredSize(new Dimension(500, 200));
		pnl_keyboard.setBackground(Color.LIGHT_GRAY);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pnl_main.add(pnl_keyboard, gbc);

		displayTable(null);
	}

	@Override
	public void displayTable(Table table) {
		pnl_table.setLayout(new GridLayout(6, 5, 5, 5));

		for (int i = 0; i < 30; ++i) {
			JPanel cell = new JPanel();
			cell.setPreferredSize(new Dimension(62, 62));
			cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			cell.setBackground(Color.WHITE);
			pnl_table.add(cell);
		}
	}

	@Override
	public void displayMessage(String msg) {
	}

	@Override
	public String getUserInput() {
		return null;
	}
}

