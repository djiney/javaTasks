package telran.additional._06_EnglishWords.test;

import org.junit.jupiter.api.Test;
import telran.additional._06_EnglishWords.EnglishWords;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EnglishWordsTest
{
	@Test
	void testExisting()
	{
		EnglishWords dictionary = new EnglishWords();
		List result = dictionary.getWords("som");

		List expectedResult = new List();
		String[] words = {"some", "somebody", "someone", "something", "sometimes"};
		for (String value : words) {
			expectedResult.add(value);
		}

		assertEquals(expectedResult.getItemCount(), result.getItemCount());

		for (int i = 0; i < expectedResult.getItemCount(); i++) {
			assertEquals(result.getItem(i), expectedResult.getItem(i));
		}
	}

	@Test
	void testAllWords()
	{
		EnglishWords dictionary = new EnglishWords();
		List result = dictionary.getWords("");

		assertEquals(result.getItemCount(), dictionary.getWordsCount());
	}

	@Test
	void testNonExisting()
	{
		EnglishWords dictionary = new EnglishWords();
		List result = dictionary.getWords("pferdamm");

		List expectedResult = new List();
		assertEquals(expectedResult.getItemCount(), result.getItemCount());
	}
}