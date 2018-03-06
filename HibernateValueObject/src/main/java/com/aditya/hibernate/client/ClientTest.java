package com.aditya.hibernate.client;

import java.util.Date;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.service.EmployeeService;
import com.aditya.hibernate.service.impl.EmployeeServiceImpl;
public class ClientTest {
	public static void main(String[] args) {
			EmployeeService employeeService= new EmployeeServiceImpl();
			
			createEmployee(employeeService);
			//getEmployeeById(employeeService);
			//updateEmployee(employeeService);
			//deleteEmployeeById(employeeService);
			
	}

	
	
	
	
	private static void deleteEmployeeById(EmployeeService employeeService) {
		employeeService.deleteEmloyeeById(2);
		
	}

	private static void updateEmployee(EmployeeService employeeService) {
		employeeService.updateEmployeeById(1, 56000.00);
	}

	private static void getEmployeeById(EmployeeService employeeService) {
		Employee employee= employeeService.getEmployeeById(3);
		System.out.println(employee);
	}

	private static void createEmployee(EmployeeService employeeService) {
		employeeService.addEmployee(getEmployee());
	}

	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Priya Srivastva");
		employee.setEmail("priya@gmail.com");
		employee.setSalary(50000.00);
		employee.setDoj(new Date());
		
		Address address= new Address();
		address.setCity("Patna");
		address.setPincode(8000001L);
		address.setState("Bihar");
		address.setStreet("JaKkkanpur");
		employee.setAddress(address);
		return employee;
	}
}
