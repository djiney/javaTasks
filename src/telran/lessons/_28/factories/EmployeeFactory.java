package telran.lessons._28.factories;

import telran.lessons._28.dto.Employee;

public class EmployeeFactory
{
	public Employee replaceCompany(Employee oldEmployee, String newCompany)
	{
		return replaceData(oldEmployee, newCompany, oldEmployee.getSalary());
	}

	public Employee replaceSalary(Employee oldEmployee, int newSalary)
	{
		return replaceData(oldEmployee, oldEmployee.getCompany(), newSalary);
	}

	private Employee replaceData(Employee oldEmployee, String newCompany, int newSalary)
	{
		return new Employee(
			oldEmployee.getId(),
			newSalary,
			newCompany,
			oldEmployee.getBirthYear(),
			oldEmployee.getName()
		);
	}
}
