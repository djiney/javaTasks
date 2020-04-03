package telran.additional._08_Strings;

public class Main
{
	/**
	 * function boolean isSubstring (String string, String substring)
	 * that returns true if a given 'substring' is indeed the substring of a given 'string'.
	 *
	 * Challenges:
	 * 1. To apply only following methods of the class String: charAt(int ind); String substring(int ind); int length();
	 * 2. No cycles;
	 * 3. No static fields
	 */
	public static boolean isSubstring(String string, String substring)
	{
		return find(string, substring, 0);
	}

	private static boolean find(String string, String substring, int index)
	{
		if (!isDataValid(string, substring)) {
			return false;
		}

		if (index >= substring.length()) {
			return true;
		}

		if (string.charAt(index) == substring.charAt(index)) {
			return find(string, substring, index + 1);
		}

		return find(string.substring(1), substring, 0);
	}

	private static boolean isDataValid(String string, String substring)
	{
		return string.length() > 0 && substring.length() > 0 && string.length() >= substring.length();
	}
}
