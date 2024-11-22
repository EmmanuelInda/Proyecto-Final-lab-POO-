import ui.GameUI;
import ui.WindowUI;
import ui.ConsoleUI;

import game.Game;

public class Main {
	public static void main(String[] args) {
		String mode = (args.length == 0) ? "window" : args[0].toLowerCase();

		GameUI ui = switch (mode) {
			case "console" -> new ConsoleUI();
			case "window" -> new WindowUI();
			default -> {
				System.err.println("Invalid option.");
				System.exit(1);
				yield null;
			}
		};

		new Game(ui);
	}
}
