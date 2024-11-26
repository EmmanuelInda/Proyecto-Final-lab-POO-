package ui;

import game.components.Table;

public interface GameUI {
	void displayTable();
	void displayWinMessage();
	void displayLoseMessage(String word);
	String getUserWord();
}

