package telran.lessons._32;

import java.util.*;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		int[] data = new Random()
			.ints(1, Integer.MAX_VALUE)
			.distinct()
			.limit(1000000)
			.toArray();

		displayDigitStatistics(data);
	}

	private static void displayDigitStatistics(int[] data)
	{
		Arrays.stream(data)
			.boxed()
			.map(n -> Integer.toString(n)
				.chars()
				.boxed()
				.collect(Collectors.groupingBy(Character::toString, Collectors.counting())))
			.flatMap(n -> n.entrySet().stream())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum))
			.entrySet().stream()
			.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
			.forEach(System.out::println);
	}
}
