package telran.lessons._04.models;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person>
{
	@Override
	public int compare(Person firstPerson, Person secondPerson)
	{
		if (firstPerson.getBirthYear() == secondPerson.getBirthYear()) {
			return 0;
		}

		return firstPerson.getBirthYear() > secondPerson.getBirthYear() ? 1 : -1;
	}
}
