package telran.lessons._28.factories;

import telran.lessons._28.dto.Employee;

public class EmployeeFactory
{
	public Employee replaceCompany(Employee oldEmployee, String newCompany)
	{
		return new Employee(
			oldEmployee.getId(),
			oldEmployee.getSalary(),
			newCompany,
			oldEmployee.getBirthYear(),
			oldEmployee.getName()
		);
	}

	public Employee replaceSalary(Employee oldEmployee, int newSalary)
	{
		return new Employee(
			oldEmployee.getId(),
			newSalary,
			oldEmployee.getCompany(),
			oldEmployee.getBirthYear(),
			oldEmployee.getName()
		);
	}
}
