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

		Map<Character, Integer> stringMap = getCharactersMap(string);
		int count;

		for (Character character : anagram.toCharArray()) {
			if (!stringMap.containsKey(character)) {
				return false;
			}

			count = stringMap.get(character) - 1;
			if (count > 0) {
				stringMap.put(character, count);
			} else {
				stringMap.remove(character);
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
