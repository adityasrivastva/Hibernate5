package com.aditya.hibernate.service.impl;

import com.aditya.hibernate.dao.impl.EmployeeDAOImpl;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public int addEmployee(Employee employee) {
		
		return new EmployeeDAOImpl().addEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return new EmployeeDAOImpl().getEmployeeById(employeeId);
	}

	@Override
	public void updateEmployeeById(int employeeId, double newSal) {
		// TODO Auto-generated method stub
		new EmployeeDAOImpl().updateEmployeeById(employeeId, newSal);
		
	}

	@Override
	public void deleteEmloyeeById(int employeeId) {
		new EmployeeDAOImpl().deleteEmloyeeById(employeeId);
		
	}

}
