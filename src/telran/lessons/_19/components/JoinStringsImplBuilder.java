package telran.lessons._19.components;

import telran.lessons._19.tests.JoinStrings;

public class JoinStringsImplBuilder implements JoinStrings
{
	@Override
	public String join(String[] strings, String delimiter)
	{
		StringBuilder result = new StringBuilder();
		for (String string : strings) {
			result.append(delimiter).append(string);
		}

		return result.substring(delimiter.length());
	}
}
