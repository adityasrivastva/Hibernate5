package com.aditya.hibernate.service;

import com.aditya.hibernate.model.Employee;

public interface EmployeeService {
	
	public int addEmployee(Employee employee);
	public Employee getEmployeeById(int employeeId);
	public void updateEmployeeById(int employeeId, double newSal);
	public void deleteEmloyeeById(int employeeId);
	

}
