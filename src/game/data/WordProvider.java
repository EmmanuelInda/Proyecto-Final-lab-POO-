package game.data;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Random;

public class WordProvider {
	public static String getRandomWord() {
		Random rand = new Random();

		int lines = countLines();
		int line = rand.nextInt(lines);

		return selectWord(lines, line);
	}

	private static String selectWord(int lines, int line) {
		if (line < 0 || line >= lines) {
			throw new IndexOutOfBoundsException("Invalid line.");
		}

		String filePath = "res/words/words.txt";
		String word = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			int currentLine = 0;

			while ((word = reader.readLine()) != null) {
				if (currentLine == line) break;
				currentLine++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return word;
	}

	private static int countLines() {
		String filePath = "res/words/words.txt";
		int count = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			while (reader.readLine() != null) {
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}
}
