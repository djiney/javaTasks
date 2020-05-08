package telran.lessons._28.service;

import telran.lessons._28.api.EmployeesService;
import telran.lessons._28.dto.Employee;
import telran.lessons._28.dto.EmployeesReturnCodes;

import java.util.*;

public class EmployeesServiceMapsImpl implements EmployeesService
{
	private final HashMap<Long, Employee> employees = new HashMap<>();
	private final HashMap<String, List<Employee>> employeesCompany = new HashMap<>();
	private final TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	private final TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();

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
			updateCollection(employee, employee.getCompany(), newCompany, employeesCompany);
			employee.setCompany(newCompany);
		});
	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary)
	{
		return updateEmployee(id, (employee) -> {
			updateCollection(employee, employee.getSalary(), newSalary, employeesSalary);
			employee.setSalary(newSalary);
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

	private Iterable<Employee> collectFromSubMap(SortedMap<Integer, List<Employee>> subMap)
	{
		Set<Employee> result = new HashSet<>();
		for (List<Employee> list : subMap.values()) {
			result.addAll(list);
		}

		return result;
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

	private <T> void updateCollection(Employee employee, T oldKey, T newKey, Map<T, List<Employee>> collection)
	{
		removeFromCollection(employee, oldKey, collection);
		addToCollection(employee, newKey, collection);
	}

	@FunctionalInterface
	private interface UpdateEmployee {
		void run(Employee employee);
	}
}
