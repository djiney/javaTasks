package telran.lessons._19.components;

import telran.lessons._19.tests.JoinStrings;

public class JoinStringsImplString implements JoinStrings
{
	@SuppressWarnings("StringConcatenationInLoop")
	@Override
	public String join(String[] strings, String delimiter)
	{
		String result = "";

		for (int i = 0; i < strings.length; i++)
		{
			if (i != 0) result += delimiter;
			result += strings[i];
		}

		return result;
	}
}
