package game;

import game.components.Table;
import game.data.WordProvider;

import game.logic.Feedback;
import game.logic.Feedback.GameStatus;

import static game.logic.Feedback.GameStatus;

public class Game {
	private String targetWord;
	private int attempts;
	private final int maxAttempts = 6;
	private GameStatus status;
	private Table table;

	public Game() {
		table = new Table();
		targetWord = WordProvider.getRandomWord();
		// this.targetWord = "hello"; /* DEBUG */
		attempts = 0;
		status = GameStatus.UNFINISHED;
	}

	public Table getTable() {
		return table;
	}

	public GameStatus getStatus() {
		return status;
	}

	public int getAttempts() {
		return attempts;
	}

	public String getTargetWord() {
		return targetWord;
	}

	public void makeAttempt(String userWord) {
		this.attempts++;

		int[] result = Feedback.getFeedback(targetWord, userWord);
		this.status = Feedback.checkResult(result);

		table.updateRow(userWord, result);

		if (attempts == maxAttempts && this.status != GameStatus.WIN) {
			this.status = GameStatus.LOSE;
		}
	}

	public static boolean isValidWord(String word) {
		return WordProvider.isValidWord(word);
	}
}
