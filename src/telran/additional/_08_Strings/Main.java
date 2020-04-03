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
		if (substring.length() == 0) {
			return true;
		}

		if (string.length() < substring.length()) {
			return false;
		}

		if (string.charAt(0) == substring.charAt(0) && isSubstring(string.substring(1), substring.substring(1))) {
			return true;
		}

		return isSubstring(string.substring(1), substring);
	}
}
