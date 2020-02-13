package telran.additional._01_hasSumTwo;

public class Main
{
    public static void main(String[] args)
    {
        short[] array = {14, 3912, 32767, 2, 3, 4, 5, 9, 1, 2, 3, 4, 5, 9};
        short sum = 10;

        Main.hasSumTwo(array, sum);
    }

	public static boolean hasSumTwo(short[] array, short sum)
	{
		if (array.length == 0) {
			return false;
		}

		int maxSize = Math.min(sum + 1, Short.MAX_VALUE);
		boolean[] potentialComponents = new boolean[maxSize];

		for (short value: array) {
			if (value > sum) {
				continue;
			}

			if (potentialComponents[sum - value]) {
				System.out.print(
					  String.format("Found elements: %d & %d", value, sum - value)
				);
				return true;
			}

			potentialComponents[value] = true;
		}

		System.out.print("Nothing have been found");
		return false;
	}
}
