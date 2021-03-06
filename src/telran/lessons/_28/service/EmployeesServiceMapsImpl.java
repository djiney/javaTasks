package telran.lessons._28.service;

import telran.lessons._28.api.EmployeesService;
import telran.lessons._28.dto.Employee;
import telran.lessons._28.dto.EmployeesReturnCodes;
import telran.lessons._28.factories.EmployeeFactory;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeesServiceMapsImpl implements EmployeesService
{
	private final HashMap<Long, Employee> employees = new HashMap<>();
	private final HashMap<String, List<Employee>> employeesCompany = new HashMap<>();
	private final TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	private final TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();

	private final EmployeeFactory factory = new EmployeeFactory();

	@Override
	public EmployeesReturnCodes addEmployee(Employee employee)
	{
		Employee result = employees.putIfAbsent(employee.getId(), employee);
		if (result != null) {
			return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		}

		addToCollection(employee, employee.getCompany(), employeesCompany);
		addToCollection(employee, employee.getAge(),     employeesAge);
		addToCollection(employee, employee.getSalary(),  employeesSalary);

		return EmployeesReturnCodes.OK;
	}

	@Override
	public EmployeesReturnCodes removeEmployee(long id)
	{
		return updateEmployee(id, (employee) -> {
			employees.remove(id);
			removeFromCollection(employee, employee.getCompany(), employeesCompany);
			removeFromCollection(employee, employee.getAge(),     employeesAge);
			removeFromCollection(employee, employee.getSalary(),  employeesSalary);
		});
	}

	@Override
	public EmployeesReturnCodes updateCompany(long id, String newCompany)
	{
		return updateEmployee(id, (employee) -> {
			removeEmployee(id);
			addEmployee(factory.replaceCompany(employee, newCompany));
		});
	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary)
	{
		return updateEmployee(id, (employee) -> {
			removeEmployee(id);
			addEmployee(factory.replaceSalary(employee, newSalary));
		});
	}

	@Override
	public Employee getEmployee(long id)
	{
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployees()
	{
		return employees.values();
	}

	@Override
	public Iterable<Employee> getEmployeesCompany(String company)
	{
		return employeesCompany.getOrDefault(company, new ArrayList<>());
	}

	@Override
	public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo)
	{
		return collectFromSubMap(employeesAge.subMap(ageFrom, ageTo));
	}

	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo)
	{
		return collectFromSubMap(employeesSalary.subMap(salaryFrom, salaryTo));
	}

	@Override
	public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval)
	{
		return employees.values().stream()
			.collect(
				Collectors.groupingBy(
					v -> getInterval(interval, v.getSalary()),
					() -> new TreeMap<>(Comparator
						.comparing(String::length)
						.thenComparing(String::compareTo)),
					Collectors.toList()
				)
			);
	}

	private String getInterval(int interval, long salary)
	{
		long remainder = salary % interval;
		return String.format("%d - %d", salary - remainder, salary + interval - remainder - 1);
	}

	private Iterable<Employee> collectFromSubMap(SortedMap<Integer, List<Employee>> subMap)
	{
		return subMap
			.values()
			.stream()
			.flatMap(Collection::stream)
			.collect(Collectors.toSet());
	}

	private EmployeesReturnCodes updateEmployee(long id, UpdateEmployee callback)
	{
		Employee employee = employees.get(id);
		if (employee == null) {
			return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
		}

		callback.run(employee);

		return EmployeesReturnCodes.OK;
	}

	private <T> void removeFromCollection(Employee employee, T key, Map<T, List<Employee>> collection)
	{
		List<Employee> list = collection.get(key);
		list.remove(employee);
	}

	private <T> void addToCollection(Employee employee, T key, Map<T, List<Employee>> collection)
	{
		List<Employee> list = collection.getOrDefault(key, new ArrayList<>());
		list.add(employee);
		collection.putIfAbsent(key, list);
	}

	@FunctionalInterface
	private interface UpdateEmployee {
		void run(Employee employee);
	}
}
