package telran.lessons._31;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main
{
	public static void main(String[] args)
	{
		displayShufflingArray(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		displaySportLottoNumbers(10, 100, 10);
		displaySportLottoNumbers(99, 100, 10);
	}

	public static void displaySportLottoNumbers(int min, int max, int nNumbers)
	{
		IntStream stream = new Random()
			.ints(nNumbers, min, max)
			.distinct()
			.onClose(() -> {
				if (max - min + 1 < nNumbers) throw new RuntimeException("Unable to fit the conditions");
			});

		stream.forEach(System.out::println);
		stream.close();
	}

	public static void displayShufflingArray(int[] array)
	{
		new Random()
			.ints(0, array.length)
			.distinct()
			.limit(10)
			.forEach(i -> System.out.println(array[i]));
	}

	public static void displayShufflingArrayParallel(int[] array)
	{
		Arrays.stream(array)
			.parallel()
			.forEach(System.out::println);
	}
}
