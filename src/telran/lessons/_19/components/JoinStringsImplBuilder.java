package telran.lessons._19.components;

import telran.lessons._19.tests.JoinStrings;

public class JoinStringsImplBuilder implements JoinStrings
{
	@Override
	public String join(String[] strings, String delimiter)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < strings.length; i++)
		{
			if (i != 0) result.append(delimiter);
			result.append(strings[i]);
		}

		return result.toString();
	}
}
