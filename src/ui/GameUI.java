package ui;

import game.Table;

public interface GameUI {
	void displayTable(Table table);
	void displayMessage(String msg);
	String getUserInput();
}

