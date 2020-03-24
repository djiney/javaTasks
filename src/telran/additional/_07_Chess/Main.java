package telran.additional._07_Chess;

public class Main
{
	public static void main(String[] args)
	{
		int rows = 4;
		int columns = 5;

		System.out.println(calculateCombinations(rows, columns));
	}

	public static int calculateCombinations(int rows, int columns)
	{
		int total = rows * columns;
		int sixesCount = 0;

		if (rows >= 2 && columns >= 2 && total > 4) {
			if (columns > 2) {
				sixesCount += (columns - 2) * (rows - 1);
			}
			if (rows > 2) {
				sixesCount += (columns - 1) * (rows - 2);
			}
		}

		return total * (total - 1) - sixesCount * 4;
	}
}
