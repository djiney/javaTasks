package telran.additional._02_getMaxWithNegativePresentation;

public class Main
{
	public static void main(String[] args)
	{
		short[] array = {1, 2, -3, 4, 5, 6, -6, 7, 8, 9, 10, -9};

		short result = Main.getMaxWithNegativePresentation(array);

		System.out.println(
			  String.format("Maximum value: %d & %d", result, -result)
		);
	}

	public static short getMaxWithNegativePresentation(short[] array)
	{
		short maxValue = -1;

		if (array.length < 2) {
			return maxValue;
		}

		byte[] potentialComponents = new byte[Short.MAX_VALUE];

		short positiveValue, potentialValue;
		boolean isNegative;

		for (short value: array) {

			isNegative = value < 0;
			positiveValue = (short) (isNegative ? -value : value);

			potentialValue = potentialComponents[positiveValue];

			if (potentialValue == 0) {
				potentialComponents[positiveValue] = (byte) (isNegative ? -1 : 1);
			} else if ((isNegative && potentialValue == 1) || (!isNegative && potentialValue == -1)) {
				System.out.println(
					String.format("Found a pair: %d & %d", positiveValue, -positiveValue)
				);

				if (positiveValue > maxValue) {
					maxValue = positiveValue;
				}
			}
		}

		return  maxValue;
	}
}
