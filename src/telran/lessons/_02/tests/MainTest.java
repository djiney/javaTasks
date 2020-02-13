package telran.lessons._02.tests;

import telran.lessons._02.Main;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	@org.junit.jupiter.api.Test
	void searchTest()
	{
		int[] array = {14, 3912, 282, 2, 3, 4, 5, 9, 1, 2, 3, 4, 5, 9};

		assertEquals(Main.search(array, 282), 2);
		assertEquals(Main.search(array, 283), -1);
	}

	@org.junit.jupiter.api.Test
	void sortTest()
	{
		int[] array = {3, 4, 5, 9, 1};
		int[] sortedArray = {1, 3, 4, 5, 9};

		Main.sort(array);

		assertArrayEquals(array, sortedArray);
	}

	@org.junit.jupiter.api.Test
	void binarySearchTest()
	{
		int[] array = {14, 3912, 282, 2, 3, 4, 5, 9, 1, 2, 3, 4, 5, 9};
		int[] smallNumberArray = {7};
		int[] bigNumberArray = {9};

		Main.sort(array);

		int expectedIndex = 12;
		int number = array[expectedIndex];

		assertEquals(expectedIndex, Main.binarySearch(array, number));
		assertEquals(-10, Main.binarySearch(array, 8));
		assertTrue(Main.binarySearch(array, -1) < 0);

		assertEquals(-2, Main.binarySearch(smallNumberArray, 8));
		assertEquals(-1, Main.binarySearch(bigNumberArray, 8));
	}

	@org.junit.jupiter.api.Test
	void arraysBinarySearchTest()
	{
		int[] array = {1, 2, 3, 4, 6, 7, 8, 10};
		int[] smallNumberArray = {7};
		int[] bigNumberArray = {9};

		assertEquals(-2, Arrays.binarySearch(smallNumberArray, 8));
		assertEquals(-1, Arrays.binarySearch(bigNumberArray, 8));
		assertEquals(6, Arrays.binarySearch(array, 8));
		assertEquals(-8, Arrays.binarySearch(array, 9));
	}

	@org.junit.jupiter.api.Test
	void arraysCopyTest()
	{
		int[] arraySource = {1, 2, 3, 4, 5, 3, 4, 5};
		int[] arrayDestination = new int[20];
		int[] arrayExpected = {0, 0, 0, 0, 0, 0, 4, 5, 3, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		System.arraycopy(arraySource, 3, arrayDestination, 6, 5);

		assertArrayEquals(arrayDestination, arrayExpected);
	}
}