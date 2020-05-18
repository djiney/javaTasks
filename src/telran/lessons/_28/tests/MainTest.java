package telran.lessons._28.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.lessons._28.api.EmployeesService;
import telran.lessons._28.dto.Employee;
import telran.lessons._28.dto.EmployeesReturnCodes;
import telran.lessons._28.service.EmployeesServiceMapsImpl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest
{
	EmployeesService service;
	Employee rina;

	@BeforeEach
	void initService()
	{
		service = new EmployeesServiceMapsImpl();
		rina = new Employee(8, 90000, "Motorola", LocalDate.of(1987, 10,  8), "Rina");

		Employee[] data = {
			new Employee(1, 10000, "Nokia",    LocalDate.of(1991, 11, 17), "John"),
			new Employee(2,  8000, "Samsung",  LocalDate.of(1998, 5,   5), "Joe"),
			new Employee(3,  4000, "Nokia",    LocalDate.of(1988, 4,  14), "Mike"),
			new Employee(4, 20000, "Nokia",    LocalDate.of(1987, 12, 30), "Elly"),
			new Employee(5, 22000, "Samsung",  LocalDate.of(1982, 1,  22), "Dina"),
			new Employee(6, 12000, "Motorola", LocalDate.of(1980, 3,   1), "Don"),
			new Employee(7, 18000, "Motorola", LocalDate.of(1984, 8,  12), "Rick"),
			rina,
		};

		for (Employee employee : data) {
			assertEquals(EmployeesReturnCodes.OK, service.addEmployee(employee));
		}
	}

	@Test
	void testAdd()
	{
		assertEquals(EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS, service.addEmployee(rina));
	}

	@Test
	void testGroupSalary()
	{
		Map<String, List<Employee>> map = service.getEmployeesGroupedBySalary(1000);
		assertTrue(map.containsKey("20000 - 20999"));
		assertEquals(1, map.get("20000 - 20999").size());

		map = service.getEmployeesGroupedBySalary(80000);
		assertTrue(map.containsKey("0 - 79999"));
		assertEquals(7, map.get("0 - 79999").size());

		assertTrue(map.containsKey("80000 - 159999"));
		assertEquals(1, map.get("80000 - 159999").size());
	}

	@Test
	void testRemoveAndGet()
	{
		long idToRemove = rina.getId();
		Employee employee = service.getEmployee(idToRemove);

		assertNotNull(employee);
		assertEquals(rina, employee);

		assertEquals(EmployeesReturnCodes.OK, service.removeEmployee(idToRemove));
		assertNull(service.getEmployee(idToRemove));
		assertEquals(EmployeesReturnCodes.EMPLOYEE_NOT_FOUND, service.removeEmployee(idToRemove));
	}

	@Test
	void testGetAll()
	{
		Collection<Employee> result = (Collection<Employee>) service.getEmployees();
		assertEquals(8, result.size());
	}

	@Test
	void testUpdateSalary()
	{
		assertEquals(EmployeesReturnCodes.EMPLOYEE_NOT_FOUND, service.updateSalary(9000, 3000));
		assertEquals(EmployeesReturnCodes.OK, service.updateSalary(rina.getId(), 3000));

		Collection<Employee> result = (Collection<Employee>) service.getEmployeesSalary(1000, 3500);
		assertEquals(1, result.size());

		assertEquals(EmployeesReturnCodes.OK, service.updateSalary(rina.getId(), 130000));
		result = (Collection<Employee>) service.getEmployeesSalary(100000, 200000);
		assertEquals(1, result.size());

		result = (Collection<Employee>) service.getEmployeesSalary(1000, 3500);
		assertEquals(0, result.size());
	}

	@Test
	void testUpdateCompany()
	{
		assertEquals(EmployeesReturnCodes.EMPLOYEE_NOT_FOUND, service.updateCompany(9000, "Nokla"));
		assertEquals(EmployeesReturnCodes.OK, service.updateCompany(rina.getId(), "Nokla"));

		Collection<Employee> result = (Collection<Employee>) service.getEmployeesCompany("Nokla");
		assertEquals(1, result.size());

		assertEquals(EmployeesReturnCodes.OK, service.updateCompany(rina.getId(), "Nokia"));
		result = (Collection<Employee>) service.getEmployeesCompany("Nokia");
		assertEquals(4, result.size());

		result = (Collection<Employee>) service.getEmployeesCompany("Nokla");
		assertEquals(0, result.size());
	}

	@Test
	void testGetCompany()
	{
		Collection<Employee> result = (Collection<Employee>) service.getEmployeesCompany("Nokia");
		assertEquals(3, result.size());

		result = (Collection<Employee>) service.getEmployeesCompany("Nokla");
		assertEquals(0, result.size());
	}

	@Test
	void testGetAges()
	{
		Collection<Employee> result = (Collection<Employee>) service.getEmployeesAges(25, 35);
		assertEquals(4, result.size());

		result = (Collection<Employee>) service.getEmployeesAges(10, 12);
		assertEquals(0, result.size());
	}

	@Test
	void testGetSalary()
	{
		Collection<Employee> result = (Collection<Employee>) service.getEmployeesSalary(10000, 20000);
		assertEquals(3, result.size());

		result = (Collection<Employee>) service.getEmployeesSalary(1000, 3000);
		assertEquals(0, result.size());
	}
}
