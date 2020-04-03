package telran.additional._08_Strings.tests;

import org.junit.jupiter.api.Test;
import telran.additional._08_Strings.Main;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	@Test
	void mainTest()
	{
		String source = "Дезоксирибонуклеиновая кислота";

		assertFalse(Main.isSubstring(source, "котлетка"));

		assertTrue(Main.isSubstring(source, "кислота"));
		assertTrue(Main.isSubstring(source, "Дезоксирибонуклеиновая"));
		assertTrue(Main.isSubstring(source, "Дезокси"));
		assertTrue(Main.isSubstring(source, "ирибонукле"));
		assertTrue(Main.isSubstring(source, "леиновая"));
		assertTrue(Main.isSubstring(source, " "));

		assertFalse(Main.isSubstring(source, "дезоксирибонуклеиновая кислота"));
		assertFalse(Main.isSubstring(source, ""));

		assertFalse(Main.isSubstring("", "котлетка"));
	}
}