package ui;

import game.components.Table;

public interface GameUI {
	void displayTable(Table table);
	void displayWinMessage();
	void displayLoseMessage(String word);
	String getUserWord();
}

