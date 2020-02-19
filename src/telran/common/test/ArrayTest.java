package telran.common.test;

import org.junit.jupiter.api.Test;
import telran.common.Array;
import telran.lessons._04.models.Person;
import telran.lessons._04.models.PersonAgeComparator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest
{
	@Test
	void testAddGetSize()
	{
		Array<Integer> array = new Array<Integer>(4);
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
		Array<Integer> array = new Array<Integer>(4);
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
		Array<Integer> array = new Array<Integer>(4);

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

	@Test
	void testBinarySearchWithOutComparator()
	{
		Array<Person> array = new Array<Person>();

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
		Array<Person> array = new Array<Person>();

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
}