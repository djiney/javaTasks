package telran.lessons._36.components;

import telran.lessons._36.dto.persons.*;

import java.time.LocalDate;

public class PersonsGenerator extends AbstractGenerator<Object>
{
	private static final long N_PERSONS = 100;
	private static final int PROB_EMPLOYEE = 60;

	private static final int MAX_CITIES = 15;
	private static final int MAX_STREETS = 15;

	private static final int MIN_CHILD_YEAR = 2002;
	private static final int MAX_CHILD_YEAR = 2020;

	private static final int MIN_EMPLOYEE_YEAR = 1960;
	private static final int MAX_EMPLOYEE_YEAR = 2002;

	private static final int MAX_NAMES = 15;
	private static final int MAX_COMPANIES = 15;

	private static final int MIN_SALARY = 10_000;
	private static final int MAX_SALARY = 1_000_000;
	private static final int SALARY_STEP = 1000;

	private static final int MAX_GARDENS = 10;

	protected String getName()
	{
		return "persons.data";
	}

	protected long getCount()
	{
		return N_PERSONS;
	}

	protected Person generateModel()
	{
		return getChance() <= PROB_EMPLOYEE ? getEmployee() : getChild();
	}

	private Person getEmployee()
	{
		Employee person = new Employee();

		person.copy(getPerson());

		person.setBirthDate(getBirthDate(MIN_EMPLOYEE_YEAR, MAX_EMPLOYEE_YEAR));
		person.setCompany("company" + getRandomNumber(1, MAX_COMPANIES));
		person.setTitle(randomEnum(Titles.class));
		person.setSalary(getSalary());

		return person;
	}

	private int getSalary()
	{
		return (getRandomNumber(MIN_SALARY, MAX_SALARY) / SALARY_STEP) * SALARY_STEP;
	}

	private Person getChild()
	{
		Child person = new Child();

		person.copy(getPerson());

		person.setBirthDate(getBirthDate(MIN_CHILD_YEAR, MAX_CHILD_YEAR));
		person.setGarden("garden" + getRandomNumber(1, MAX_GARDENS));

		return person;
	}

	private Person getPerson()
	{
		return new Person(
			getRandomNumber(100_000, 1_000_000),
			getAddress(),
			"name" + getRandomNumber(1, MAX_NAMES),
			null
		);
	}

	private LocalDate getBirthDate(int min, int max)
	{
		int year = getRandomNumber(min, max);
		int dayYear = getRandomNumber(1, 365);
		return LocalDate.ofYearDay(year, dayYear);
	}

	private Address getAddress()
	{
		return new Address(
			"City" + getRandomNumber(1, MAX_CITIES),
			"Street" + getRandomNumber(1, MAX_STREETS),
			getRandomNumber(1, 100),
			getRandomNumber(1, 1000)
		);
	}
}
