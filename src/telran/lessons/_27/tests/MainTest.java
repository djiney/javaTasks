package telran.lessons._27.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._27.NumbersBox;
import telran.lessons._27.implementations.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	int[] numbers = {1, 2, 75, 3, 11, 106, 20001, -251, 11, 72, 3, 11};

	@Test
	void testArrayList()
	{
		testCollection(new ArrayListNumbersBox());
	}

	@Test
	void testLinkedList()
	{
		testCollection(new LinkedListNumbersBox());
	}

	@Test
	void testTreeSet()
	{
		testCollection(new TreeSetNumbersBox());
	}

	@Test
	void testHashSet()
	{
		testCollection(new HashSetNumbersBox());
	}

	private void testCollection(NumbersBox collection)
	{
		testAddition(collection);
		testIterator(collection);
		testSingleRemove(collection);
		testRepeatedRemove(collection);
		testRangeRemove(collection);
	}

	private void testRepeatedRemove(NumbersBox collection)
	{
		int initialSize = collection.size();
		int removed = collection.removeRepeated();

		assertEquals(collection.size(), initialSize - removed);
	}

	private void testRangeRemove(NumbersBox collection)
	{
		assertEquals(3, collection.removeNumbersInRange(3, 72));
	}

	private void testSingleRemove(NumbersBox collection)
	{
		int removeNumber = 20001;

		collection.removeNumber(removeNumber);

		for (Integer value : collection) {
			assertNotEquals(value, removeNumber);
		}
	}

	private void testIterator(NumbersBox collection)
	{
		int[] realNumbers = unique(getNumbers(collection));
		int[] testNumbers = unique(numbers);

		assertEquals(testNumbers.length, realNumbers.length);

		Arrays.sort(realNumbers);
		Arrays.sort(testNumbers);

		for (int i = 0; i < testNumbers.length; i++) {
			assertEquals(testNumbers[i], realNumbers[i]);
		}
	}

	private int[] unique(int[] array)
	{
		return IntStream.of(array)
			.distinct()
			.toArray();
	}

	private int[] getNumbers(NumbersBox collection)
	{
		int[] result = new int[collection.size()];

		int index = 0;
		for (Integer value : collection) {
		    result[index++] = value;
		}

		return result;
	}

	private void testAddition(NumbersBox collection)
	{
		for (Integer number : numbers) {
			collection.addNumber(number);
		}
	}
}