package telran.lessons._18;

import telran.lessons._11.TreeSet;

import javax.management.BadAttributeValueExpException;

public class Encoder
{
	String encodingString;
	int radix;

	public Encoder(String string) throws BadAttributeValueExpException
	{
		validate(string);

		encodingString = string;
		radix = string.length();
	}

	private void validate(String string) throws BadAttributeValueExpException
	{
		if (string.length() <= 1) {
			throw new BadAttributeValueExpException(string);
		}

		TreeSet<Character> set = new TreeSet<>();
		for (int i = 0; i < string.length(); i++) {
			if (!set.add(string.charAt(i))) {
				throw new BadAttributeValueExpException(string);
			}
		}
	}

	public String code(int number)
	{
		StringBuilder result = new StringBuilder();
		int remainder;

		while (number != 0)
		{
			remainder = number % radix;
			number = (number - remainder) / radix;

			result.insert(0, encodingString.charAt(remainder));
		}

		return result.toString();
	}
}
