package telran.lessons._19.components;

import telran.lessons._19.tests.JoinStrings;

public class JoinStringsImplString implements JoinStrings
{
	@SuppressWarnings("StringConcatenationInLoop")
	@Override
	public String join(String[] strings, String delimiter)
	{
		if (strings.length == 0) {
			return "";
		}

		String result = strings[0];
		for (int i = 1; i < strings.length; i++)
		{
			result += delimiter + strings[i];
		}

		return result;
	}
}
