package game;

import game.keys.Key;

import game.words.WordProvider;

import ui.GameUI;

import java.util.List;
import java.util.ArrayList;

public class Game {
	private String targetWord;
	private int maxAttempts;
//	private List<UserWord> attempts;
	private GameUI ui;
	private boolean isGameOver;

	private Table table;

	public Game(GameUI ui) {
		table = new Table();

		targetWord = WordProvider.getRandomWord();

		this.ui = ui;
		this.isGameOver = false;

		loop();
	}

	public void loop() {
		setup();
		display();

		while (!isGameOver) {
			input();
			update();
			display();
		}
	}

	public void setup() {
		targetWord = "hello";
	}

	public void input() {
		ui.getUserInput();
	}

	public void update() {
	}

	public void display() {
		ui.displayTable(table);
	}
}

