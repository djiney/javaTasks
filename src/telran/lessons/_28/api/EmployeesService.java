package telran.lessons._28.api;

import telran.lessons._28.dto.Employee;
import telran.lessons._28.dto.EmployeesReturnCodes;

import java.util.List;
import java.util.Map;

public interface EmployeesService
{
	EmployeesReturnCodes addEmployee(Employee employee);

	EmployeesReturnCodes removeEmployee(long id);

	Employee getEmployee(long id);

	Iterable<Employee> getEmployees();

	Iterable<Employee> getEmployeesCompany(String company);

	Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo);

	Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo);

	Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval);

	EmployeesReturnCodes updateCompany(long id, String newCompany);

	EmployeesReturnCodes updateSalary(long id, int newSalary);
}
