package telran.lessons._19.components;

import telran.lessons._19.tests.JoinStrings;

public class JoinStringsImplBuilder implements JoinStrings
{
	@Override
	public String join(String[] strings, String delimiter)
	{
		if (strings.length == 0) {
			return "";
		}

		StringBuilder result = new StringBuilder(strings[0]);
		for (int i = 1; i < strings.length; i++)
		{
			result.append(delimiter).append(strings[i]);
		}

		return result.toString();
	}
}
