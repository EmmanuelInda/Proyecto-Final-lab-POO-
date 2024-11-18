import ui.WindowUI;
import game.Game;

public class Main {
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1");
		System.setProperty("sun.java2d.dpiaware", "true");

		Game game = new Game();
		WindowUI windowUI = new WindowUI();

		game.hello();
	}
}

