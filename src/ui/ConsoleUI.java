package ui;

import game.Game;
import game.components.Table;

import game.logic.Feedback;
import game.logic.Feedback.GameStatus;

import static game.logic.Feedback.GameStatus;

import java.util.Scanner;

public class ConsoleUI implements GameUI {
	private Scanner sc;
	private Game game;

	public ConsoleUI(Game game) {
		this.sc = new Scanner(System.in);
		this.game = game;
		this.loop();
	}

	public void loop() {
		displayTable();

		while (game.getStatus() == GameStatus.UNFINISHED) {
			/* Input */
			String userWord = getUserWord();

			/* Update */
			game.makeAttempt(userWord);

			/* Display */
			displayTable();
		}

		if (game.getStatus() == GameStatus.WIN) {
			displayWinMessage();
		} else {
			displayLoseMessage(game.getTargetWord());
		}
	}

	@Override
	public void displayTable() {
		Table table = game.getTable();

		System.out.println(table.toString());
	}

	@Override
	public void displayWinMessage() {
		System.out.println("You win!");
	}

	@Override
	public void displayLoseMessage(String word) {
		System.out.println("You lose!");
		System.out.println("Target word was: " + word);
	}

	@Override
	public String getUserWord() {
		String input;

		System.out.print("Word: ");
		do {
			input = sc.next();

			if (input.length() != 5) {
				System.out.println("\nInput error: Word must be 5 characters long.\n");
				System.out.print("Word: ");
			}
		} while (input.length() != 5);

		System.out.println("");
		return input;
	}
}
