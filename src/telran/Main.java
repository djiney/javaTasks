package telran;

import java.util.Arrays;

public class Main
{
	public static void main(String[] args)
	{
		long value = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("KB: " + value / (1024));
	}

	public static int[] addToSortedArray(int[] oldArray, int newValue)
	{
		/* Another function */
		int newIndex = Arrays.binarySearch(oldArray, newValue);
		if (newIndex < 0) {
			newIndex = -newIndex - 1;
		}

		return Main.addToArray(oldArray, newIndex, newValue);
	}

	public static int[] addToArray(int[] oldArray, int newIndex, int newValue)
	{
		if (newIndex > oldArray.length || newIndex < 0) {
			return oldArray;
		}

		int newLength = oldArray.length + 1;
		int[] newArray = new int[newLength];

		int index = 0;

		for (int i = 0; i < newLength; i++) {
			if (i == newIndex) {
				newArray[i] = newValue;
				continue;
			}

			newArray[i] = oldArray[index++];
		}

		return newArray;
	}

	public static int[] deleteFromArray(int[] oldArray, int deleteIndex)
	{
		if (deleteIndex >= oldArray.length || deleteIndex < 0) {
			return oldArray;
		}

		int oldLength = oldArray.length;
		int[] newArray = new int[oldLength - 1];

		int index = 0;

		for (int i = 0; i < oldLength; i++) {
			if (i == deleteIndex) {
				continue;
			}

			newArray[index++] = oldArray[i];
		}

		return newArray;
	}
}
