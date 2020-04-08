package telran.lessons._19.components;

import telran.lessons._19.tests.JoinStrings;

public class JoinStringsImplString implements JoinStrings
{
	@SuppressWarnings("StringConcatenationInLoop")
	@Override
	public String join(String[] strings, String delimiter)
	{
		String result = "";
		for (String string : strings) {
			result += delimiter + string;
		}

		return result.substring(delimiter.length());
	}
}
