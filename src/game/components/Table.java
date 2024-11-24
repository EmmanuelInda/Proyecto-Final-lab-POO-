package game.components;

import game.logic.Position;

public class Table {
	private static final int x = 5;
	private static final int y = 6;

	private char[][] cells;
	private Position.State[][] colors;
	private int row;

	public Table() {
		cells = new char[x][y];
		colors = new Position.State[x][y];

		fill();

		row = 0;
	}

	public void updateRow(String word, int[] result) {
		for (int i = 0; i < x; ++i) {
			if (result[i] == 1)
				colors[i][row] = Position.State.CORRECT;
			else if (result[i] == -1)
				colors[i][row] = Position.State.ABSENT;
			else if (result[i] == 0)
				colors[i][row] = Position.State.PRESENT;

			cells[i][row] = word.charAt(i);
		}

		row++;
	}

	private void fill() {
		for (int j = 0; j < y; ++j)
		for (int i = 0; i < x; ++i) 
		cells[i][j] = '*';
	}

	public int getRow() {
		return row;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (int j = 0; j < y; ++j) {
			for (int i = 0; i < x; ++i) {
				if (colors[i][j] == Position.State.CORRECT)
					sb.append("\033[32m");
				else if (colors[i][j] == Position.State.PRESENT)
					sb.append("\033[33m");
				else if (colors[i][j] == Position.State.ABSENT)
					sb.append("\033[31m");

				sb.append(cells[i][j]);
				sb.append("\033[0m ");
			}

			if (j != y - 1) sb.append("\n");
		}

		return sb.toString();
	}
}
