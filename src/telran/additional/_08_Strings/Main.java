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
		SubstringSearch search = new SubstringSearch(string, substring);
		return search.find();
	}

	private static class SubstringSearch
	{
		String string;
		String substring;

		int stringLength;
		int substringLength;

		public SubstringSearch(String string, String substring)
		{
			this.string = string;
			this.substring = substring;

			stringLength = string.length();
			substringLength = substring.length();
		}

		public boolean find()
		{
			if (!isDataValid()) {
				return false;
			}

			return find(0);
		}

		private boolean isDataValid()
		{
			return substringLength > 0 && stringLength > 0 && stringLength >= substringLength;
		}

		private boolean find(int index)
		{
			if (index >= stringLength) {
				return false;
			}

			if (checkSubstring(index, 0)) {
				return true;
			}

			return find(index + 1);
		}

		private boolean checkSubstring(int stringIndex, int substringIndex)
		{
			if (substringIndex >= substringLength) {
				return true;
			}

			if (string.charAt(stringIndex) != substring.charAt(substringIndex)) {
				return false;
			}

			return checkSubstring(stringIndex + 1, substringIndex + 1);
		}
	}
}
