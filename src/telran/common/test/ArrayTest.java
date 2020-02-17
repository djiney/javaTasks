package telran.common.test;

import org.junit.jupiter.api.Test;
import telran.common.Array;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest
{
	@Test
	void testAddGetSize()
	{
		Array array = new Array(4);
		int[] numbers = {10, -8, 70, 75, 30};

		for (int number : numbers) {
			array.add(number);
		}

		for (int i = 0; i < array.size(); i++) {
			assertEquals(numbers[i], array.get(i));
		}

		assertNull(array.get(array.size()));
	}

	@Test
	void testAddByIndex()
	{
		Array array = new Array(4);
		int[] numbers = {10, -8, 70, 75, 30};

		for (int i = 0; i < numbers.length; i++) {
			array.add(i, numbers[i]);
		}

		for (int i = 0; i < array.size(); i++) {
			assertEquals(numbers[i], array.get(i));
		}

		for (int number : numbers) {
			array.add(0, number);
		}

		int arrayIndex = 0;
		for (int i = numbers.length - 1; i >= 0; i--) {
			assertEquals(numbers[i], array.get(arrayIndex++));
		}
	}

	@Test
	void testRemoveSet()
	{
		Array array = new Array(4);

		int[] numbers = {10, -8, 70, 75, 30};

		for (int number : numbers) {
			array.add(number);
		}

		int number = -12;
		int index = 2;

		assertEquals(70, array.set(index, number));
		assertEquals(number, array.get(index));

		int indexRemove = 3;

		assertEquals(75, array.remove(indexRemove));
		assertEquals(30, array.get(indexRemove));
		assertEquals(4, array.size());

		int firstIndexRemove = 0;
		int expectedResult = 10;

		assertEquals(expectedResult, array.remove(firstIndexRemove));
	}
}