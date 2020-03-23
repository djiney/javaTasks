package telran.lessons._11.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.common.predicates.EvenNumbersPredicate;
import telran.lessons._10.tests.HashSetTest;
import telran.lessons._11.TreeSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetTest extends HashSetTest
{
	@BeforeEach
	public void setUp()
	{
		set = new TreeSet<>(initialNumbers);
	}

	@Test
	public void testAdditionalRemove()
	{
		Integer[] numbers = getShuffledArray(10000);
		TreeSet<Integer> set = new TreeSet<>(numbers);

		assertEquals(numbers.length, set.size());

		Integer numberToRemove = 10;
		Integer fakeNumber = 10001;

		assertTrue(set.contains(numberToRemove));
		assertFalse(set.contains(fakeNumber));

		assertEquals(numberToRemove, set.remove(numberToRemove));
		assertFalse(set.contains(numberToRemove));

		assertEquals(numbers.length - 1, set.size());

		// Test root removal
		Integer rootValue = numbers[0];
		assertEquals(rootValue, set.remove(rootValue));

		assertFalse(set.contains(rootValue));
		assertEquals(numbers.length - 2, set.size());
	}

	private Integer[] getShuffledArray(int length)
	{
		Integer[] numbers = new Integer[length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}

		List<Integer> intList = Arrays.asList(numbers);
		Collections.shuffle(intList);
		intList.toArray(numbers);

		return numbers;
	}

	@Test
	public void testRemoveIf()
	{
		for (int i = 0; i < 10000; i++) {
			set.add((int) (Math.random() * Integer.MAX_VALUE));
		}

		assertTrue(set.removeIf(new EvenNumbersPredicate()));
		assertFalse(set.removeIf(new EvenNumbersPredicate()));

		for(int value : set) {
			assertEquals(1, value % 2);
		}
	}

	@Test
	public void testContains()
	{
		Integer[] numbers = {1, 2, 3};
		TreeSet<Integer> set = new TreeSet<>();

		assertFalse(set.contains(4));

		for (Integer value : numbers) {
			assertTrue(set.add(value));
			assertTrue(set.contains(value));
		}
	}

	@Test
	public void testRemoveAdditional()
	{
		Integer[] numbers = {50, 45, 82, 20, 48, 60, 85};
		TreeSet<Integer> set = new TreeSet<>(numbers);
		assertEquals(numbers.length, set.size());

		Integer numberToRemove = 50;
		assertTrue(set.contains(numberToRemove));
		assertEquals(numberToRemove, set.remove(numberToRemove));

		assertEquals(numbers.length - 1, set.size());
		assertFalse(set.contains(numberToRemove));
	}

	@Test
	public void testAdditionalIteration()
	{
		Integer[] numbers = getShuffledArray(1000);
		Integer[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(sortedNumbers);

		TreeSet<Integer> set = new TreeSet<>(numbers);

		int i = 0;
		for (Integer value : set) {
			assertEquals(sortedNumbers[i++], value);
		}
	}

	@Test
	public void testIteratorRemove()
	{
		Integer[] numbers = getShuffledArray(10);

		Integer[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(sortedNumbers);

		TreeSet<Integer> set = new TreeSet<>(numbers);

		Iterator<Integer> iterator = set.iterator();
		Integer value;

		int i = 0;
		while (iterator.hasNext()) {
			value = iterator.next();

			assertEquals(sortedNumbers[i++], value);
			if (value % 2 == 0) {
				iterator.remove();
			}
		}
	}
}