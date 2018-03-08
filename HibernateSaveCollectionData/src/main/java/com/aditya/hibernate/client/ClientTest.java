package com.aditya.hibernate.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	
	
	
	
	/*private static void deleteEmployeeById(EmployeeService employeeService) {
		employeeService.deleteEmloyeeById(2);
		
	}

	private static void updateEmployee(EmployeeService employeeService) {
		employeeService.updateEmployeeById(1, 56000.00);
	}

	private static void getEmployeeById(EmployeeService employeeService) {
		Employee employee= employeeService.getEmployeeById(3);
		System.out.println(employee);
	}*/

	private static void createEmployee(EmployeeService employeeService) {
		employeeService.addEmployee(getEmployee());
	}

	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Priya Srivastva");
		employee.setEmail("priya@gmail1.com");
		employee.setSalary(50000.00);
		employee.setDoj(new Date());
		
		Address homeaddress= new Address();
		homeaddress.setCity("Patna");
		homeaddress.setPincode(8000001L);
		homeaddress.setState("Bihar");
		homeaddress.setStreet("JaKkkanpur");
		
		
		
		Address officeaddress= new Address();
		officeaddress.setCity("Patna1");
		officeaddress.setPincode(8000002L);
		officeaddress.setState("Bihar1");
		officeaddress.setStreet("JaKkkanpur1");
		
		employee.getAddresses().add(homeaddress);
		employee.getAddresses().add(officeaddress);
		
		
		return employee;
	}
}
