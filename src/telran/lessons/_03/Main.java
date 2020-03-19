package telran.lessons._03;

import telran.lessons._10.HashSet;

import java.util.Arrays;

public class Main
{
	public static int[] addToSortedArray(int[] oldArray, int newValue)
	{
		int newIndex = telran.lessons._02.Main.binarySearch(oldArray, newValue);
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

	/* methods of the HW #3 */
	/**
	 * Assumption: no repeated numbers in each array, but
	 * numbers in first array may be repeated in the second
	 * @param ar1 - first array
	 * @param ar2 - second array
	 * @return array containing numbers of first and second arrays
	 * with no repetitions
	 */
	public static Integer[] union (Integer[] ar1, Integer[] ar2)
	{
		HashSet<Integer> set = new HashSet<>(ar1);
		for (Integer value : ar2) {
			set.add(value);
		}

		return set.toArray();
	}

	/**
	 * Assumption: no repeated numbers in each array, but
	 * numbers in first array may be repeated in the second
	 * @param ar1 - first array
	 * @param ar2 - second array
	 * @return array containing common numbers between first and second arrays
	 * with no repetitions
	 */
	public static Integer[] intersection (Integer[] ar1, Integer[] ar2)
	{
		HashSet<Integer> set = new HashSet<>(ar1);
		HashSet<Integer> resultSet = new HashSet<>();

		for (Integer value : ar2) {
			if (!set.add(value)) {
				resultSet.add(value);

			}
		}

		return resultSet.toArray();
	}

	/**
	 * Assumption: no repeated numbers in each array, but
	 * numbers in first array may be repeated in the second
	 * @param ar1 - first array
	 * @param ar2 - second array
	 * @return array containing numbers of first array that are not repeated
	 * 	in the second
	 */
	public static Integer[] difference (Integer[] ar1, Integer[] ar2)
	{
		HashSet<Integer> set = new HashSet<>(ar1);
		for (Integer value : ar2) {
		    if (set.contains(value)) {
		    	set.remove(value);
		    }
		}

		return set.toArray();
	}

	public static boolean contains(Integer[] array, Integer key)
	{
		return Arrays.stream(array).anyMatch(i -> i == key);
	}
}
