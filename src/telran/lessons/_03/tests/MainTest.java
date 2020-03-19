package telran.lessons._03.tests;

import telran.lessons._03.Main;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MainTest
{
	@Test
	public void testAdd()
	{
		int[] array = {1, 2, -5, 10, 8};
		int[] expectedArray0 = {100, 1, 2, -5, 10, 8};
		int[] expectedArray1 = {100, 1, 2, -5, 10, 8, 50};
		int[] expectedArray2 = {100, 1, 2, -5, 600, 10, 8, 50};

		assertArrayEquals(array, Main.addToArray(array, 500, 100));
		assertArrayEquals(array, Main.addToArray(array, -500, 100));

		assertArrayEquals(expectedArray0, array = Main.addToArray(array, 0, 100));
		assertArrayEquals(expectedArray1, array = Main.addToArray(array, 6, 50));
		assertArrayEquals(expectedArray2, array = Main.addToArray(array, 4, 600));
	}

	@Test
	public void testDelete()
	{
		int[] array = {1, 2, -5, 10, 8};
		int[] expectedArray0 = {2, -5, 10, 8};
		int[] expectedArray1 = {2, -5, 10};
		int[] expectedArray2 = {2, 10};

		assertArrayEquals(array, Main.deleteFromArray(array, 500));
		assertArrayEquals(array, Main.deleteFromArray(array, -500));

		assertArrayEquals(expectedArray0, array = Main.deleteFromArray(array, 0));
		assertArrayEquals(expectedArray1, array = Main.deleteFromArray(array, 3));
		assertArrayEquals(expectedArray2, array = Main.deleteFromArray(array, 1));
	}

	@Test
	public void testAddToSorted()
	{
		int[] array = {1, 2, 3, 4, 5, 6, 7, 9, 10};
		int[] expectedArray0 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		assertArrayEquals(expectedArray0, Main.addToSortedArray(array, 8));
	}

	/* tests for HW #3 */
	@Test
	void testUnion ()
	{
		Integer[] ar1 = {10, 30, -8, 20};
		Integer[] ar2 = {0, -3, 7, 11};
		Integer[] ar3 = {0, -8, 20, 10};
		Integer[] exp1 = {10, 30, -8, 20, 0, -3, 7, 11};
		Integer[] exp2 = {10, 30, -8, 20, 0};

		compareArrays(exp1, Main.union(ar1, ar2));
		compareArrays(exp2, Main.union(ar1, ar3));
	}

	@Test
	void testIntersection ()
	{
		Integer[] ar1 = {10, 30, -8, 20};
		Integer[] ar2 = {0, -3, 7, 11};
		Integer[] ar3 = {0, -8, 20, 10};
		Integer[] exp1 = {};
		Integer[] exp2 = {10, -8, 20};
		compareArrays(exp1, Main.intersection(ar1, ar2));
		compareArrays(exp2, Main.intersection(ar1, ar3));
	}

	@Test
	void testDifference ()
	{
		Integer[] ar1 = {10, 30, -8, 20};
		Integer[] ar2 = {0, -3, 7, 11};
		Integer[] ar3 = {0, -8, 20, 10};
		Integer[] exp1 = {10, 30, -8, 20};
		Integer[] exp2 = {30};
		compareArrays(exp1, Main.difference(ar1, ar2));
		compareArrays(exp2, Main.difference(ar1, ar3));
	}

	private void compareArrays(Integer[] array1, Integer[] array2)
	{
		Arrays.sort(array1);
		Arrays.sort(array2);

		assertArrayEquals(array1, array2);
	}
}