package ui;

import game.Feedback;

public interface GameUI {
	void displayTable();
	void displayMessage(String msg);
	void displayFeedback(Feedback feedback);
	String getUserInput();
}

