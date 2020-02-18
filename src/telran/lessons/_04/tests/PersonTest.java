package telran.lessons._04.tests;

import org.junit.jupiter.api.Test;
import telran.common.Array;
import telran.lessons._04.models.Person;
import telran.lessons._04.models.PersonAgeComparator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest
{
	@Test
	void testPerson()
	{
		Person person = new Person();
		assertNotNull(person);
		assertNull(person.getName());
	}

	@Test
	void testConstructor()
	{
		String name = "מיכאל";
		int id = 1;
		int year = 1991;

		Person person = new Person(id, year, name);

		assertNotNull(person);
		assertEquals(name, person.getName());
		assertEquals(id, person.getId());
		assertEquals(year, person.getBirthYear());
	}

	@Test
	void getEqualObjects()
	{
		Person person0 = new Person(1, 2, "QIUIIU");
		Person person1 = new Person(1, 2, "QIUIIU");

		assertEquals(person0, person1);
	}

	@Test
	void setName()
	{
		Person person = new Person();

		String name = "מיכאל";
		person.setName(name);
		assertEquals(name, person.getName());
	}

	@Test
	void testSorting()
	{
		Person[] persons = {
			  new Person(123, 1980, "QIUIIU"),
			  new Person(100, 1970, "QIUIIU"),
		};

		Arrays.sort(persons);

		assertEquals(100, persons[0].getId());
		assertEquals(123, persons[1].getId());
	}

	@Test
	void testComparatorSorting()
	{
		Person[] persons = {
			  new Person(123, 1980, "QIUIIU"),
			  new Person(100, 1970, "QIUIIU"),
		};

		Arrays.sort(persons, new PersonAgeComparator());

		assertEquals(100, persons[0].getId());
		assertEquals(123, persons[1].getId());
	}
}