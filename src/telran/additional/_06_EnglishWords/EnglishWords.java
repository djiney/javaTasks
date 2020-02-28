package telran.additional._06_EnglishWords;

import telran.additional._06_EnglishWords.components.TextNode;

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
 *
 * Complexity of getting element by index from List is O[N],
 * but even if you use regular array your solution doesn't provide most effective search.
 * (1) method startWith takes a time for long prefixes
 * (2) There may be a lot of the words for short prefix so the loop of the first word searching may take a lot time
 *
 */
public class EnglishWords
{
	private static final String WORDS_PATH = "./files/dictionary.csv";

	private TextNode mainNode;

	public EnglishWords()
	{
		this.loadVocabulary();
	}

	public List getWords(String prefix)
	{
		return this.mainNode.search(prefix);
	}

	public int getWordsCount()
	{
		return this.mainNode.getWordsCount();
	}

	private void loadVocabulary()
	{
		this.mainNode = new TextNode();

		Path pathToFile = Paths.get(EnglishWords.WORDS_PATH);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII))
		{
			String line = br.readLine();

			while (line != null)
			{
				line = br.readLine();
				this.mainNode.addWord(line);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
