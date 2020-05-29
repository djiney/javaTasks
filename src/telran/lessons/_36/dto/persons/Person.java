package telran.lessons._36.dto.persons;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable
{
	private static final long serialVersionUID = 1L;

	long id;
	Address address;
	String name;
	LocalDate birthDate;

	public Person() {}

	public Person(long id, Address address, String name, LocalDate birthDate)
	{
		this.id = id;
		this.address = address;
		this.name = name;
		this.birthDate = birthDate;
	}

	public long getId()
	{
		return id;
	}

	public Address getAddress()
	{
		return address;
	}

	public String getName()
	{
		return name;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	public void copy(Person person)
	{
		id = person.id;
		address = person.address;
		name = person.name;
		birthDate = person.birthDate;
	}
}
