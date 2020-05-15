package telran.lessons._29.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._29.Main;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	String word = "yellow";

	@Test
	void testFunctionality()
	{
		assertTrue(Main.isAnagram("", ""));
		assertTrue(Main.isAnagram(word, "welloy"));
		assertTrue(Main.isAnagram(word, "lowley"));

		assertFalse(Main.isAnagram(word, "a"));
		assertFalse(Main.isAnagram(word, null));
		assertFalse(Main.isAnagram(null, word));
		assertFalse(Main.isAnagram(null, null));
		assertFalse(Main.isAnagram(word, "dwelloy"));
		assertFalse(Main.isAnagram(word, "wellol"));
	}
}