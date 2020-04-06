package telran.lessons._18;

import telran.lessons._11.TreeSet;

import javax.management.BadAttributeValueExpException;
import java.util.ArrayList;

public class Encoder
{
	public static final String BASE_64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	public static final String BASE_64_URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
	public static final String BASE_64_MARK_SYMBOL = "=";

	String markSymbol = BASE_64_MARK_SYMBOL;
	String alphabet;

	int bitCount;
	int radix;

	ArrayList<String> list;

	public Encoder() throws BadAttributeValueExpException
	{
		this(BASE_64_ALPHABET);
	}

	public Encoder(String alphabet) throws BadAttributeValueExpException
	{
		validate(alphabet);

		this.alphabet = alphabet;
		radix = alphabet.length();
		bitCount = (int) (Math.log(radix) / Math.log(2));
	}

	public void setMarkSymbol(String symbol)
	{
		markSymbol = symbol;
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

			result.insert(0, alphabet.charAt(remainder));
		}

		return result.toString();
	}

	public String codeText(String text)
	{
		StringBuilder sequence = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			sequence.append(convertToBytes(text.charAt(i), 8));
		}

		int padding = breakSequence(sequence, bitCount);
		String postfix = markSymbol.repeat(padding / 2);

		StringBuilder result = new StringBuilder();
		for (String symbol : list) {
			result.append(alphabet.charAt(Integer.parseInt(symbol, 2)));
		}

		result.append(postfix);
		return result.toString();
	}

	public String decodeText(String text)
	{
		StringBuilder sequence = new StringBuilder();
		int index;
		for (int i = 0; i < text.length(); i++) {
			index = alphabet.indexOf(text.charAt(i));
			if (index < 0) {
				sequence.setLength(sequence.length() - 2);
			} else {
				sequence.append(convertToBytes(index, bitCount));
			}
		}

		breakSequence(sequence, 8);

		StringBuilder result = new StringBuilder();
		for (String symbol : list) {
			result.append((char) Integer.parseInt(symbol, 2));
		}

		return result.toString();
	}

	private String convertToBytes(int value, int requiredLength)
	{
		String result = Integer.toString(value, 2);
		return "0".repeat(requiredLength - result.length()) + result;
	}

	private int breakSequence(StringBuilder sequence, int breakAt)
	{
		int padding = 0;

		list = new ArrayList<>();

		StringBuilder word = new StringBuilder();
		for (int i = 0; i < sequence.length(); i++) {
			word.append(sequence.charAt(i));

			if (word.length() == breakAt) {
				list.add(word.toString());
				word = new StringBuilder();
			}
		}

		if (word.length() > 0) {
			while (word.length() < breakAt) {
				word.append(0);
				padding++;
			}

			list.add(word.toString());
		}

		return padding;
	}
}
