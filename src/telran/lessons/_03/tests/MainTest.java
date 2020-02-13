package telran.lessons._03.tests;

import telran.lessons._03.Main;
import org.junit.jupiter.api.Test;

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
		int[] ar1 = {10, 30, -8, 20};
		int[] ar2 = {0, -3, 7, 11};
		int[] ar3 = {0, -8, 20, 10};
		int[] exp1 = {10, 30, -8, 20, 0, -3, 7, 11};
		int[] exp2 = {10, 30, -8, 20, 0};
		assertArrayEquals(exp1, Main.union(ar1, ar2));
		assertArrayEquals(exp2, Main.union(ar1, ar3));
	}

	@Test
	void testIntersection ()
	{
		int[] ar1 = {10, 30, -8, 20};
		int[] ar2 = {0, -3, 7, 11};
		int[] ar3 = {0, -8, 20, 10};
		int[] exp1 = {};
		int[] exp2 = {10, -8, 20};
		assertArrayEquals(exp1, Main.intersection(ar1, ar2));
		assertArrayEquals(exp2, Main.intersection(ar1, ar3));
	}

	@Test
	void testDifference ()
	{
		int[] ar1 = {10, 30, -8, 20};
		int[] ar2 = {0, -3, 7, 11};
		int[] ar3 = {0, -8, 20, 10};
		int[] exp1 = {10, 30, -8, 20};
		int[] exp2 = {30};
		assertArrayEquals(exp1, Main.difference(ar1, ar2));
		assertArrayEquals(exp2, Main.difference(ar1, ar3));
	}
}