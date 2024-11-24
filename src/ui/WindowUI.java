package ui;

import game.Game;
import game.components.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowUI extends JFrame implements GameUI {
	private JPanel pnl_main;
	private JPanel pnl_table;
	private JPanel pnl_keyboard;

	private String input;
	private boolean send;
	private static int row = 0;

	private Game game;

	public WindowUI(Game game) {
		this.game = game;

		this.setTitle("Wordle");
		this.setPreferredSize(new Dimension(1366, 768));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setComponents();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void setComponents() {
		pnl_main = new JPanel(new GridBagLayout());
		pnl_main.setBackground(Color.WHITE);
		add(pnl_main);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		pnl_table = new JPanel();
		pnl_table.setPreferredSize(new Dimension(330, 400));
		pnl_table.setBackground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 0;
		pnl_main.add(pnl_table, gbc);

		pnl_keyboard = new JPanel();
		pnl_keyboard.setPreferredSize(new Dimension(600, 200));
		pnl_keyboard.setBackground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pnl_main.add(pnl_keyboard, gbc);

		input = "";

		setTable();
		setKeyboard();
	}

	@Override
	public void displayTable(Table table) {
		if (table != null)
			row = table.getRow();

		System.out.println("Input: " + input);

		for (int i = 0; i < 5; ++i) {
			JPanel cell = (JPanel) pnl_table.getComponent(row * 5 + i);
			cell.removeAll();
			if (i < input.length()) {
				cell.add(new JLabel(String.valueOf(input.charAt(i))));
			}
			cell.revalidate();
			cell.repaint();
		}
	}

	@Override
	public void displayWinMessage() {
	}

	@Override
	public void displayLoseMessage(String word) {
	}

	@Override
	public String getUserWord() {
		return input;
	}

	private void setTable() {
		pnl_table.setLayout(new GridLayout(6, 5, 5, 5));

		for (int i = 0; i < 30; ++i) {
			JPanel cell = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;

					g2d.setColor(Color.LIGHT_GRAY);
					g2d.setStroke(new BasicStroke(0.8f));
					g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

					g2d.setColor(Color.WHITE);
					g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
				}
			};

			cell.setPreferredSize(new Dimension(59, 59));
			pnl_table.add(cell);
		}
	}

	private void setKeyboard() {
		pnl_keyboard.setLayout(new BoxLayout(pnl_keyboard, BoxLayout.Y_AXIS));

		Dimension keySize = new Dimension(50, 50);
		Font keyFont = new Font("Arial", Font.BOLD, 16);
		Color keyBackground = new Color(0xD3D6DA);
		Color keyTextColor = new Color(0x1A1A1B);

		String[] keys1 = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
		String[] keys2 = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
		String[] keys3 = {"Z", "X", "C", "V", "B", "N", "M"};

		JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		row1.setOpaque(false);
		for (String key : keys1) {
			JButton btn = createKeyButton(key, keyBackground, keyTextColor, keyFont, keySize);
			row1.add(btn);
		}
		pnl_keyboard.add(row1);

		JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		row2.setOpaque(false);

		JPanel spacerHalf = new JPanel();
		spacerHalf.setPreferredSize(new Dimension(25, 50));
		spacerHalf.setOpaque(false);
		row2.add(spacerHalf);

		for (String key : keys2) {
			JButton btn = createKeyButton(key, keyBackground, keyTextColor, keyFont, keySize);
			row2.add(btn);
		}

		row2.add(spacerHalf);
		pnl_keyboard.add(row2);

		JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		row3.setOpaque(false);

		JButton btnEnter = createKeyButton("Send", keyBackground, keyTextColor, keyFont, new Dimension(75, 50));
		row3.add(btnEnter);

		for (String key : keys3) {
			JButton btn = createKeyButton(key, keyBackground, keyTextColor, keyFont, keySize);
			row3.add(btn);
		}

		JButton btnBackspace = createKeyButton("←", keyBackground, keyTextColor, keyFont, new Dimension(75, 50));
		row3.add(btnBackspace);

		pnl_keyboard.add(row3);
	}

	private JButton createKeyButton(String text, Color bgColor, Color textColor, Font font, Dimension size) {
		JButton button = new JButton(text);
		button.setPreferredSize(size);
		button.setFont(font);
		button.setForeground(textColor);
		button.setBackground(bgColor);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		return button;
	}

	private class KeyButtonListener implements ActionListener {
		private final String key;

		public KeyButtonListener(String key) {
			this.key = key;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (input.length() < 5) {
				input += key;
				System.out.print("Key: " + key);
				displayTable(null);
			}
		}
	}

/*	private class BackspaceButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentWord.length() > 0) {
				currentWord = currentWord.substring(0, currentWord.length() - 1);
				updateTable();
			}
		}
	}*/

	private class EnterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (input.length() == 5) {
				send = true; 
			}
		}
	}
}
