package telran.lessons._21.tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	@Test
	public void testReplaceAll()
	{
		String string = " abcd%&77777777777777777777778t12g*";
		assertEquals("abcdtg", removeAllExceptLetters(string));
	}

	private String removeAllExceptLetters(String string)
	{
		return string.replaceAll("[^\\p{Alpha}]", "");
	}

	@Test
	public void testSplit()
	{
		String string = "abcd%&77777777777777777777778t12g*";

		String[] tokensExpected = {"abcd", "t", "g"};
		String[] tokensActual = string.split("[^\\p{Alpha}]+");

		System.out.println(Arrays.toString(tokensActual));

		assertArrayEquals(tokensExpected, tokensActual);
	}
}