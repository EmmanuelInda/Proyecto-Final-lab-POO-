package ui;

import javax.swing.*;
import java.awt.*;

public class WindowUI extends JFrame {
	JPanel pnl_main;
	JPanel pnl_table;

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

		pnl_table = new JPanel();
		pnl_table.setPreferredSize(new Dimension(250, 320));
		pnl_table.setBackground(Color.CYAN);
		pnl_main.add(pnl_table);
	}
}

