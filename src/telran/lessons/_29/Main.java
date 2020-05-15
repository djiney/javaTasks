package telran.lessons._29;

import java.util.HashMap;
import java.util.Map;

public class Main
{
	public static boolean isAnagram(String string, String anagram)
	{
		if (anagram == null || string == null) {
			return false;
		}

		if (string.equals(anagram)) {
			return true;
		}

		if (anagram.isBlank() || string.isBlank()) {
			return false;
		}

		if (anagram.length() != string.length()) {
			return false;
		}

		Map<Character, Integer> stringMap = getCharactersMap(string);

		for (Character character : anagram.toCharArray()) {
			if (stringMap.compute(character, (k, v) -> v == null ? -1 : --v) < 0) {
				return false;
			}
		}

		return true;
	}

	private static Map<Character, Integer> getCharactersMap(String string)
	{
		Map<Character, Integer> result = new HashMap<>();
		for (Character character : string.toCharArray()) {
			result.put(character, result.getOrDefault(character, 0) + 1);
		}

		return result;
	}
}
