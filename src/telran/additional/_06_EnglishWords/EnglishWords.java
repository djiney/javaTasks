package telran.additional._06_EnglishWords;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Develop class EnglishWords with method:
 *
 * getWords(String prefix)
 * returning List of words beginning with the given prefix.
 *
 * The data structure and the method should provide the least complexity
 */
public class EnglishWords
{
	private static final String WORDS_PATH = "./files/dictionary.csv";

	private List vocabulary;

	public EnglishWords()
	{
		this.loadVocabulary();
	}

	private void loadVocabulary()
	{
		this.vocabulary = new List();

		Path pathToFile = Paths.get(EnglishWords.WORDS_PATH);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII))
		{
			String line = br.readLine();

			while (line != null)
			{
				this.vocabulary.add(line);
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private int getFirstIndex(String prefix)
	{
		if (this.vocabulary.getItemCount() < 1) {
			return -1;
		}

		int left = 0;
		int right = this.vocabulary.getItemCount() - 1;
		int middle = (left + right) / 2;

		while (left <= right && !this.vocabulary.getItem(middle).startsWith(prefix))
		{
			if (prefix.compareTo(this.vocabulary.getItem(middle)) < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}

			middle = (left + right) / 2;
		}

		if (left > right) {
			return -left - 1;
		}

		while (middle >= 0 && this.vocabulary.getItem(middle).startsWith(prefix)) {
			middle--;
		};

		return ++middle;
	}

	public List getWords(String prefix)
	{
		if (prefix.length() == 0) {
			throw new UnsupportedOperationException();
		}

		List list = new List();
		int index = this.getFirstIndex(prefix);

		if (index < 0) {
			return list;
		}

		while (this.vocabulary.getItem(index).startsWith(prefix)) {
			list.add(this.vocabulary.getItem(index++));
		}

		return list;
	}
}
