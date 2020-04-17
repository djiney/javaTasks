package telran.lessons._19_0.components;

import telran.lessons._19_0.tests.JoinStrings;

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
