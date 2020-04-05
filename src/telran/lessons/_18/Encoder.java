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
		String convertedNumber = Integer.toString(number, radix);

		StringBuilder result = new StringBuilder();
		int index;

		for (int i = 0; i < convertedNumber.length(); i++)
		{
			index = Character.getNumericValue(convertedNumber.charAt(i));
			result.append(encodingString.charAt(index));
		}

		return result.toString();
	}
}
