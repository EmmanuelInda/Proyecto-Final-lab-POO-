package ui;

import game.Game;
import game.components.Table;

import game.logic.Feedback;
import game.logic.Feedback.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.logic.Position;

public class WindowUI extends JFrame implements GameUI {
	private JPanel pnl_main;
	private JPanel pnl_table;
	private JPanel pnl_keyboard;

	private String input;
	private boolean send;

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

		this.addKeyListener(new KeyboardInputListener());
		this.setFocusable(true);
		this.requestFocusInWindow();
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
	public void displayTable() {
		Table table = game.getTable();
		int row = table.getRow();

		for (int i = 0; i < 5; ++i) {
			JPanel cell = (JPanel) pnl_table.getComponent(row * 5 + i);
			cell.removeAll();

			if (i < input.length()) {
				JLabel lbl_letter = new JLabel(String.valueOf(input.charAt(i)));

				lbl_letter.setFont(new Font("Arial", Font.BOLD, 24));

				lbl_letter.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_letter.setVerticalAlignment(SwingConstants.CENTER);

				cell.setLayout(new BorderLayout());
				cell.add(lbl_letter, BorderLayout.CENTER);

				cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
			} else {
				cell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
			}

			cell.revalidate();
			cell.repaint();
		}
	}

	public void updateRow() {
		String userWord = input.toLowerCase();

		game.makeAttempt(userWord);

		Table table = game.getTable();
		int row = table.getRow() - 1;
		Position.State[][] colors = table.getColors();

		for (int col = 0; col < 5; col++) {
			JPanel cell = (JPanel) pnl_table.getComponent(row * 5 + col);
			cell.removeAll();

			JLabel lbl_letter = new JLabel(String.valueOf(userWord.charAt(col)).toUpperCase());
			lbl_letter.setFont(new Font("Arial", Font.BOLD, 24));
			lbl_letter.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_letter.setVerticalAlignment(SwingConstants.CENTER);
			lbl_letter.setOpaque(false);
			cell.setLayout(new BorderLayout());
			cell.add(lbl_letter, BorderLayout.CENTER);

			cell.setOpaque(true);

			Position.State state = colors[col][row];


			switch (state) {
				case CORRECT:
					cell.setBackground(Color.GREEN);
					break;
				case PRESENT:
					cell.setBackground(Color.YELLOW);
					break;
				case ABSENT:
					cell.setBackground(Color.GRAY);
					break;
				default:
					cell.setBackground(Color.WHITE);
					break;
			}

			cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
			cell.revalidate();
			cell.repaint();
		}

		pnl_table.revalidate();
		pnl_table.repaint();

		if (game.getStatus() == GameStatus.UNFINISHED) {
			input = "";
		} else {
			if (game.getStatus() == GameStatus.WIN) {
				displayWinMessage();
			} else {
				displayLoseMessage(game.getTargetWord());
			}
		}
	}

	@Override
	public void displayWinMessage() {
		JOptionPane.showMessageDialog(this, "You win!", "Result", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void displayLoseMessage(String word) {
		JOptionPane.showMessageDialog(this, "You lose! Target word was: " + word);
	}

	@Override
	public String getUserWord() {
		return input;
	}


	private void setTable() {
		pnl_table.setLayout(new GridLayout(6, 5, 5, 5));
	
		for (int i = 0; i < 30; ++i) {
			JPanel cell = new JPanel();
			cell.setPreferredSize(new Dimension(59, 59));
			cell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
			cell.setBackground(Color.WHITE);
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
			btn.addActionListener(new KeyButtonListener(key));
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
			btn.addActionListener(new KeyButtonListener(key));
			row2.add(btn);
		}

		row2.add(spacerHalf);
		pnl_keyboard.add(row2);

		JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		row3.setOpaque(false);

		JButton btnEnter = createKeyButton("Send", keyBackground, keyTextColor, keyFont, new Dimension(75, 50));
		btnEnter.addActionListener(new EnterButtonListener());
		row3.add(btnEnter);

		for (String key : keys3) {
			JButton btn = createKeyButton(key, keyBackground, keyTextColor, keyFont, keySize);
			btn.addActionListener(new KeyButtonListener(key));
			row3.add(btn);
		}

		JButton btnBackspace = createKeyButton("←", keyBackground, keyTextColor, keyFont, new Dimension(75, 50));
		btnBackspace.addActionListener(new BackspaceButtonListener());
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
				displayTable();
			}
		}
	}

	private class BackspaceButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (input.length() > 0 && game.getStatus() == GameStatus.UNFINISHED) {
				input = input.substring(0, input.length() - 1);
				displayTable();
			}
		}
	}

	private class EnterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (input.length() == 5 && game.getStatus() == GameStatus.UNFINISHED) {
				System.out.println("Enter");
				updateRow();
			}
		}
	}

	private class KeyboardInputListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();

			if (game.getStatus() == GameStatus.UNFINISHED) {
				if (Character.isLetter(c) && input.length() < 5) {
					input += Character.toUpperCase(c);
					displayTable();
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && input.length() > 0) {
					input = input.substring(0, input.length() - 1);
					displayTable();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && input.length() == 5) {
					updateRow();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
