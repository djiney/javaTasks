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
		if (!isDataValid(string, substring)) {
			return false;
		}

		return find(string, substring, 0);
	}

	private static boolean isDataValid(String string, String substring)
	{
		return string.length() > 0 && substring.length() > 0 && string.length() >= substring.length();
	}

	private static boolean find(String string, String substring, int index)
	{
		if (index >= string.length()) {
			return false;
		}

		if (checkSubstring(string, substring, index, 0)) {
			return true;
		}

		return find(string, substring, index + 1);
	}

	private static boolean checkSubstring(String string, String substring, int stringIndex, int substringIndex)
	{
		if (substringIndex >= substring.length()) {
			return true;
		}

		if (string.charAt(stringIndex) != substring.charAt(substringIndex)) {
			return false;
		}

		return checkSubstring(string, substring, stringIndex + 1, substringIndex + 1);
	}
}
