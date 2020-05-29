package telran.lessons._36.controllers.persons;

import telran.lessons._36.components.Generator;
import telran.lessons._36.components.PersonsGenerator;
import telran.lessons._36.controllers.AbstractLoader;
import telran.lessons._36.dto.persons.Employee;
import telran.lessons._36.dto.persons.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonRestoreApplication extends AbstractLoader<Person>
{
	Map<String, Integer> cities = new HashMap<>();
	Map<String, Integer> companies = new HashMap<>();
	int averageSalary = 0;

	public static void main(String[] args)
	{
		new PersonRestoreApplication().load();
	}

	@Override
	protected void processList(List<Person> list)
	{
		list.stream()
			.filter(this::filterEmployees)
			.forEach(person -> {
				if (((Employee) person).getSalary() > averageSalary) {
					System.out.println(person);
				}
			});

		printCities();
		printCompanies();
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	private void printCities()
	{
		List<String> mostPopulated = cities.entrySet().stream()
			.collect(Collectors.groupingBy(
				Map.Entry::getValue,
				Collectors.mapping(Map.Entry::getKey, Collectors.toList())
			))
			.entrySet().stream()
			.max(Map.Entry.comparingByKey())
			.get()
			.getValue();

		System.out.printf("\nMost populated: %s\n", mostPopulated);

	}

	private void printCompanies()
	{
		System.out.printf("\nCompanies with average salary: \n%s\n", companies);
	}

	private boolean filterEmployees(Person person)
	{
		collectPersonData(person);
		if (person instanceof Employee) {
			collectEmployeeData((Employee) person);
			return true;
		}

		return false;
	}

	private void collectPersonData(Person person)
	{
		cities.merge(person.getAddress().getCity(), 1, Integer::sum);
	}

	private void collectEmployeeData(Employee employee)
	{
		companies.merge(employee.getCompany(), employee.getSalary(), this::calcAverage);
		averageSalary = calcAverage(averageSalary, employee.getSalary());
	}

	private Integer calcAverage(int v1, int v2)
	{
		return (v1 + v2) / 2;
	}

	@Override
	protected Generator<Object> getGenerator()
	{
		return new PersonsGenerator();
	}
}
