package telran.lessons._010.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.common.interfaces.Set;
import telran.common.predicates.EvenNumbersPredicate;
import telran.common.predicates.LongStringPredicate;
import telran.lessons._010.HashSet;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashSetTest
{
	protected Integer[] initialNumbers = {10, -8, 70, 75, 30};
	protected String[] names = {
			"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing", "elit"
	};
	protected Set<Integer> set;

	@BeforeEach
	public void setUp()
	{
		set = new HashSet<>(initialNumbers);
	}

	@Test
	public void testAdd()
	{
		for (Integer number : initialNumbers) {
			assertTrue(set.contains(number));
		}

		assertEquals(initialNumbers.length, set.size());

		int nonExistingValue = 12738;

		assertFalse(set.contains(nonExistingValue));

		int newNumber = 1235;

		assertTrue(set.add(newNumber));
		assertFalse(set.add(newNumber));
		assertTrue(set.contains(newNumber));
	}

	@Test
	public void testIteration()
	{
		compareWithArray(initialNumbers);
	}

	private void compareWithArray(Integer[] array)
	{
		Integer[] actualNumbers = loadNumbers();

		Arrays.sort(array);
		Arrays.sort(actualNumbers);

		assertArrayEquals(array, actualNumbers);
	}

	@Test
	public void testIterableSum()
	{
		int expectedSum = 0;
		for (int number : initialNumbers) {
			set.add(number);
			expectedSum += number;
		}

		int actualSum = 0;
		for (Integer value: set) {
			actualSum += value;
		}

		assertEquals(expectedSum, actualSum);
	}

	@Test
	public void testIterableRemove()
	{
		Integer[] expectedNumbers = {-8, 75};

		Iterator<Integer> iterator = set.iterator();
		Integer iteratedValue;

		while(iterator.hasNext()){
			iteratedValue = iterator.next();

			if (iteratedValue % 2 == 0 && iteratedValue >= 0) {
				iterator.remove();
			}
		}

		compareWithArray(expectedNumbers);
	}

	private Integer[] loadNumbers()
	{
		Integer[] result = new Integer[set.size()];
		int i = 0;

		for (Integer number : set) {
			result[i++] = number;
		}

		return result;
	}

	@Test
	public void testRemove()
	{
		int number = -8;

		assertEquals(number, set.remove(number));
		assertNull(set.remove(number));
		assertEquals(4, set.size());
	}

	@Test
	public void testRemoveIfPredicate()
	{
		Set<String> stringSet = new HashSet<>(names);
		String[] filteredStrings = {"sit", "amet", "elit"};

		assertTrue(stringSet.removeIf(new LongStringPredicate()));
		assertFalse(stringSet.removeIf(new LongStringPredicate()));

		assertEquals(filteredStrings.length, stringSet.size());

		for (String value : filteredStrings) {
			assertTrue(stringSet.contains(value));
		}
	}

	@Test
	public void testFilter()
	{
		Integer[] filteredNumbers = {10, -8, 70, 30};
		Set<Integer> filteredArray = set.filter(new EvenNumbersPredicate());

		assertEquals(filteredNumbers.length, filteredArray.size());

		for (Integer value : filteredArray) {
			assertTrue(filteredArray.contains(value));
		}
	}

	@Test
	public void testSingleList()
	{
		Integer[] testNumbers = {4, 16, 64, 256};
		set = new HashSet<>(testNumbers);

		Iterator<Integer> iterator = set.iterator();

		assertEquals(testNumbers.length, set.size());

		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}

		assertEquals(0, set.size());
	}
}