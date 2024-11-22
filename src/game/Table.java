package game;

import game.keys.Key;

public class Table {
	private static final int x = 5;
	private static final int y = 6;

	private char[][] cells;
	private Key.State[][] colors;
	private int row;

	public Table() {
		cells = new char[x][y];
		colors = new Key.State[x][y];

		fill();

		row = 0;
	}

	public void updateRow(int[] result) {
		for (int i = 0; i < x; ++i) {
			if (result[i] == 1)
				colors[i][row] = Key.State.CORRECT;
			else if (result[i] == -1)
				colors[i][row] = Key.State.ABSENT;
			else
				colors[i][row] = Key.State.PRESENT;

			row++;
		}
	}

	private void fill() {
		for (int j = 0; j < y; ++j)
		for (int i = 0; i < x; ++i) 
		cells[i][j] = '*';
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (int j = 0; j < y; ++j) {
			for (int i = 0; i < x; ++i) {
				if (colors[i][j] == Key.State.CORRECT)
					sb.append("\033[31m");
				else if (colors[i][j] == Key.State.PRESENT)
					sb.append("\033[32m");
				else if (colors[i][j] == Key.State.ABSENT)
					sb.append("\033[33m");

				sb.append(cells[i][j]);
				sb.append("\033[0m ");
			}

			if (j != y - 1) sb.append("\n");
		}

		return sb.toString();
	}
}
