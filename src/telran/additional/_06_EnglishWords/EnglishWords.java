package telran.additional._06_EnglishWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

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

	private TreeSet<String> treeSet;

	public EnglishWords()
	{
		this.loadVocabulary();
	}

	public ArrayList<String> getWords(String prefix)
	{
		SortedSet<String> set = treeSet.subSet(prefix, prefix + Character.MAX_VALUE);
		return new ArrayList<>(set);
	}

	public int getWordsCount()
	{
		return this.treeSet.size();
	}

	private void loadVocabulary()
	{
		this.treeSet = new TreeSet<>();

		Path pathToFile = Paths.get(EnglishWords.WORDS_PATH);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII))
		{
			String line = br.readLine();

			while (line != null)
			{
				this.treeSet.add(line);
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
