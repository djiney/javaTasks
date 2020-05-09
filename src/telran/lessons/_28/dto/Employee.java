package telran.lessons._28.dto;

import java.time.LocalDate;

public class Employee
{
	private final long id;
	private final String name;
	private final LocalDate birthYear;

	private final int salary;
	private final String company;

	public Employee(long id, int salary, String company, LocalDate birthYear, String name)
	{
		this.id = id;
		this.salary = salary;
		this.company = company;
		this.birthYear = birthYear;
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Employee{" +
			"id=" + id +
			", salary=" + salary +
			", company='" + company + '\'' +
			", birthYear=" + birthYear +
			", name='" + name + '\'' +
			'}';
	}

	public long getId()
	{
		return id;
	}

	public int getSalary()
	{
		return salary;
	}

	public String getCompany()
	{
		return company;
	}

	public LocalDate getBirthYear()
	{
		return birthYear;
	}

	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return LocalDate.now().getYear() - birthYear.getYear();
	}
}
