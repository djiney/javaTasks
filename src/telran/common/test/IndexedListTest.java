package telran.common.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.common.Array;
import telran.common.comparators.TrickySortingIntComparator;
import telran.common.interfaces.IndexedList;
import telran.common.predicates.EvenNumbersPredicate;
import telran.common.predicates.LongStringPredicate;
import telran.lessons._04.models.Person;
import telran.lessons._04.models.PersonAgeComparator;

import java.lang.reflect.AnnotatedType;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
public class IndexedListTest
{
	protected Integer[] initialNumbers = {10, -8, 70, 75, 30};
	protected String[] names = {
		  "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing", "elit"
	};

	protected IndexedList<Integer> list;

	@BeforeEach
	public void setUp()
	{
		list = new Array<>(this.initialNumbers);
	}

	@Test
	public void testAddGetSize()
	{
		for (int i = 0; i < list.size(); i++) {
			assertEquals(initialNumbers[i], list.get(i));
		}

		assertNull(list.get(list.size()));
	}

	@Test
	public void testAddByIndex()
	{
		for (int i = 0; i < list.size(); i++) {
			assertEquals(initialNumbers[i], list.get(i));
		}

		for (int number : initialNumbers) {
			list.add(0, number);
		}

		int arrayIndex = 0;
		for (int i = initialNumbers.length - 1; i >= 0; i--) {
			assertEquals(initialNumbers[i], list.get(arrayIndex++));
		}
	}

	@Test
	public void testRemoveSet()
	{
		int number = -12;
		int index = 2;

		assertEquals(70, list.set(index, number));
		assertEquals(number, list.get(index));

		int indexRemove = 3;

		assertEquals(75, list.remove(indexRemove));
		assertEquals(30, list.get(indexRemove));
		assertEquals(4, list.size());

		int firstIndexRemove = 0;
		int expectedResult = 10;

		assertEquals(expectedResult, list.remove(firstIndexRemove));
	}

	protected IndexedList<Person> initPersons() throws Exception
	{
		IndexedList<Person> array = list.getClass().getConstructor().newInstance();

		for (int i = 0; i < names.length; i++) {
			array.add(new Person((i + 1) * 2, 1990 - i, names[i]));
		}

		return array;
	}

	@Test
	public void testRemoveByValue() throws Exception
	{
		IndexedList<Person> array = initPersons();

		Person person = new Person(7, 1958, "TestPerson");
		array.add(person);

		AnnotatedType[] r = IndexedListTest.class.getAnnotatedInterfaces();

		assertEquals(person, array.remove(person));
		assertTrue(array.indexOf(person) < 0);
	}

	@Test
	public void testBinarySearchWithOutComparator() throws Exception
	{
		IndexedList<Person> array = initPersons();

		Person person = new Person(7, 1958, "TestPerson");
		array.add(person);

		array.sort();

		assertEquals(2, array.get(0).getId());
		assertEquals("elit", array.get(names.length).getName());

		int searchResult = array.binarySearch(person);
		assertEquals(person, array.get(searchResult));
	}

	@Test
	public void testBinarySearchWithComparator() throws Exception
	{
		IndexedList<Person> array = initPersons();

		Person person = new Person(7, 1958, "TestPerson");
		array.add(person);

		array.sort(new PersonAgeComparator());

		assertEquals(7, array.get(0).getId());
		assertEquals("Lorem", array.get(names.length).getName());

		int searchResult = array.binarySearch(person, new PersonAgeComparator());
		assertEquals(person, array.get(searchResult));
	}

	@Test
	public void testRemoveIfPredicate() throws Exception
	{
		IndexedList<String> array = list.getClass().getConstructor().newInstance();
		for (String string : names) {
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
		Integer[] filteredNumbers = {10, -8, 70, 30};
		IndexedList<Integer> filteredArray = list.filter(new EvenNumbersPredicate());

		for (int i = 0; i < filteredNumbers.length; i++) {
			assertEquals(filteredNumbers[i], filteredArray.get(i));
		}
	}

	@Test
	public void testCustomEvenOddComparator() throws Exception
	{
		Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		IndexedList<Integer> array = list.getClass()
			  .getConstructor()
			  .newInstance();

		for (Integer value : numbers) {
			array.add(value);
		}

		array.sort(new TrickySortingIntComparator());

		Integer[] sortedNumbers = {1, 3, 5, 7, 9, 10, 8, 6, 4, 2};
		for (int i = 0; i < sortedNumbers.length; i++) {
			assertEquals(sortedNumbers[i], array.get(i));
		}
	}

	@Test
	public void testIterableSum() throws Exception
	{
		IndexedList<Integer> array = list.getClass().getConstructor().newInstance();

		int expectedSum = 0;
		for (int number : initialNumbers) {
			array.add(number);
			expectedSum += number;
		}

		int actualSum = 0;
		for (Integer value: array) {
			actualSum += value;
		}

		assertEquals(expectedSum, actualSum);
	}

	@Test
	public void testIterableRemove()
	{
		Integer[] expectedNumbers = {-8, 75, 77};

		Iterator<Integer> iterator = list.iterator();
		Integer iteratedValue;

		while(iterator.hasNext()){
			iteratedValue = iterator.next();

			if (iteratedValue % 2 == 0 && iteratedValue >= 0) {
				iterator.remove();
			}
		}

		int index = 0;
		for (Integer value: list) {
			assertEquals(expectedNumbers[index++], value);
		}
	}

	@Test
	public void testIterablePredicate()
	{
		list.setPredicate(new EvenNumbersPredicate());

		Integer[] expectedNumbers = {10, -8, 70, 30};

		Iterator<Integer> iterator = list.iterator();
		Integer iteratedValue;

		int index = 0;
		while(iterator.hasNext()){
			iteratedValue = iterator.next();
			assertEquals(expectedNumbers[index++], iteratedValue);
		}

		list.resetPredicate();

		iterator = list.iterator();
		index = 0;

		while(iterator.hasNext()){
			iteratedValue = iterator.next();
			assertEquals(initialNumbers[index++], iteratedValue);
		}
	}
}