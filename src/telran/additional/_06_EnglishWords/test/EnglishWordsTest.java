package telran.additional._06_EnglishWords.test;

import org.junit.jupiter.api.Test;
import telran.additional._06_EnglishWords.EnglishWords;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EnglishWordsTest
{
	@Test
	void testExisting()
	{
		EnglishWords dictionary = new EnglishWords();
		ArrayList<String> result = dictionary.getWords("som");

		String[] words = {"some", "somebody", "someone", "something", "sometimes"};
		ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList(words));

		assertEquals(expectedResult.size(), result.size());

		for (int i = 0; i < expectedResult.size(); i++) {
			assertEquals(result.get(i), expectedResult.get(i));
		}
	}

	@Test
	void testAllWords()
	{
		EnglishWords dictionary = new EnglishWords();
		ArrayList<String> result = dictionary.getWords("");

		assertEquals(result.size(), dictionary.getWordsCount());
	}

	@Test
	void testNonExisting()
	{
		EnglishWords dictionary = new EnglishWords();
		ArrayList<String> result = dictionary.getWords("pferdamm");

		assertEquals(0, result.size());
	}
}