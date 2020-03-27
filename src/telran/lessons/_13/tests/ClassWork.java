package telran.lessons._13.tests;

import java.util.Arrays;

public class ClassWork
{
	public static int pow(int value, int power)
	{
		if (power == 0) {
			return 1;
		}

		return append(value, pow(value, power - 1));
	}

	private static int append(int value, int times)
	{
		if (times == 0) {
			return 0;
		}

		return value + append(value, times - 1);
	}

	public static int square(int value)
	{
		if (value <= 1) {
			return 1;
		}

		return value + value - 1 + square(value - 1);
	}
}
