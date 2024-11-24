package game.logic;

public class Feedback {
	public static enum GameStatus {
		UNFINISHED,
		WIN,
		LOSE
	}

	public static int[] getFeedback(String w1, String w2) {
		int[] comparison = new int[5];

		for (int i = 0; i < 5; ++i) {
			if (w1.charAt(i) == w2.charAt(i))
				comparison[i] = 1;
			else if (w1.indexOf(w2.charAt(i)) >= 0)
				comparison[i] = 0;
			else
				comparison[i] = -1;
		}

		return comparison;
	}

	public static GameStatus checkResult(int[] result) {
		GameStatus gameStatus = GameStatus.WIN;

		for (int i = 0; i < 5; ++i) {
			if (result[i] != 1) {
				gameStatus = GameStatus.UNFINISHED;
			}
		}

		return gameStatus;
	}
}

