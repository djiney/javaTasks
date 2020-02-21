package telran.common.test;

import org.junit.jupiter.api.Test;
import telran.common.Array;
import telran.common.comparators.TrickySortingIntComparator;
import telran.common.predicates.EvenNumbersPredicate;
import telran.common.predicates.LongStringPredicate;
import telran.lessons._04.models.Person;
import telran.lessons._04.models.PersonAgeComparator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest
{
	@Test
	void testAddGetSize()
	{
		Array<Integer> array = new Array<>(4);
		Integer[] numbers = {10, -8, 70, 75, 30};

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
		Array<Integer> array = new Array<>(4);
		Integer[] numbers = {10, -8, 70, 75, 30};

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
		Array<Integer> array = new Array<>(4);

		Integer[] numbers = {10, -8, 70, 75, 30};

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

	@Test
	void testBinarySearchWithOutComparator()
	{
		Array<Person> array = new Array<>();

		String[] names = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing", "elit"};

		for (int i = 0; i < names.length; i++) {
			array.add(new Person((i + 1) * 2, 1990 - i, names[i]));
		}

		Person person = new Person(7, 1958, "TestPerson");
		array.add(person);

		array.sort();

		assertEquals(2, array.get(0).getId());
		assertEquals("elit", array.get(names.length).getName());

		int searchResult = array.binarySearch(person);
		assertEquals(person, array.get(searchResult));
	}

	@Test
	void testBinarySearchWithComparator()
	{
		Array<Person> array = new Array<>();

		String[] names = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing", "elit"};

		for (int i = 0; i < names.length; i++) {
			array.add(new Person((i + 1) * 2, 1990 - i, names[i]));
		}

		Person person = new Person(7, 1958, "TestPerson");
		array.add(person);

		array.sort(new PersonAgeComparator());

		assertEquals(7, array.get(0).getId());
		assertEquals("Lorem", array.get(names.length).getName());

		int searchResult = array.binarySearch(person, new PersonAgeComparator());
		assertEquals(person, array.get(searchResult));
	}

	@Test
	public void testRemoveIfPredicate()
	{
		Array<String> array = new Array<>();
		String[] strings = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing", "elit"};
		for (String string : strings) {
			array.add(string);
		}

		String[] filteredStrings = {"sit", "amet", "elit"};

		assertTrue(array.removeIf(new LongStringPredicate()));
		assertFalse(array.removeIf(new LongStringPredicate()));

		for (int i = 0; i < filteredStrings.length; i++) {
			assertEquals(filteredStrings[i], array.get(i));
		}
	}

	@Test
	public void testFilter()
	{
		Array<Integer> array = new Array<>();
		Integer[] numbers = {10, -8, 70, 75, 30};
		for (int number : numbers) {
			array.add(number);
		}

		Integer[] filteredNumbers = {10, -8, 70, 30};
		Array<Integer> filteredArray = array.filter(new EvenNumbersPredicate());

		for (int i = 0; i < filteredNumbers.length; i++) {
			assertEquals(filteredNumbers[i], filteredArray.get(i));
		}
	}

	@Test
	public void testCustomEvenOddComparator()
	{
		Array<Integer> array = new Array<>();

		Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		for (int number : numbers) {
			array.add(number);
		}

		array.sort(new TrickySortingIntComparator());

		Integer[] sortedNumbers = {1, 3, 5, 7, 9, 10, 8, 6, 4, 2};
		for (int i = 0; i < sortedNumbers.length; i++) {
			assertEquals(sortedNumbers[i], array.get(i));
		}
	}
}