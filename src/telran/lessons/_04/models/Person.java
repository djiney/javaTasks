package telran.lessons._04.models;

import java.util.Objects;

public class Person
{
	private long id;
	private int birthYear;
	private String name;

	public Person() {}

	public Person(long id, int birthYear, String name)
	{
		this.id = id;
		this.birthYear = birthYear;
		this.name = name;
	}

	public long getId()
	{
		return id;
	}

	public int getBirthYear()
	{
		return birthYear;
	}

	public String getName()
	{
		return name;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return id == person.id &&
			  birthYear == person.birthYear &&
			  Objects.equals(name, person.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, birthYear, name);
	}
}
