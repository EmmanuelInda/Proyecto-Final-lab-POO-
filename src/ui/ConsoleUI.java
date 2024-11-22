package ui;

import game.Game;
import game.Table;

import java.util.Scanner;

public class ConsoleUI implements GameUI {
	private Scanner sc;

	public ConsoleUI() {
		sc = new Scanner(System.in);
	}

	@Override
	public void displayTable(Table table) {
		System.out.println(table.toString());
	}

	@Override
	public void displayMessage(String msg) {
	}

	@Override
	public String getUserInput() {
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

