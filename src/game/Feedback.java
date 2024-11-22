package game;

public class Feedback {
	public static int[] getFeedback(String w1, String w2) {
		int[] comparison = new int[5];

		for (int i = 0; i < 5; ++i) {
			if (w1.charAt(i) == w2.charAt(i))
				comparison[i] = 1;
			else if (w2.charAt(i) != w2.charAt(i))
				comparison[i] = -1;
			else
				comparison[i] = 0;
		}

		return comparison;
	}
}

