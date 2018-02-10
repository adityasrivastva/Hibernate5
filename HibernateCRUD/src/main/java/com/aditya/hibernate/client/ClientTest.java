package com.aditya.hibernate.client;

import java.util.Date;

import org.hibernate.Session;

import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class ClientTest {
	public static void main(String[] args) {
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			//Create employee object
			/*Employee employee = new Employee();
			employee.setEmployeeName("Priya Srivastva");
			employee.setEmail("priya@gmail.com");
			employee.setSalary(50000.00);
			employee.setDoj(new Date());*/
			
			//createEmployee(session, getEmployee());
			//getEmployeeById(session);
			//updateEmployee(session);
			deleteEmployeeById(session);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void deleteEmployeeById(Session session) {
		Employee employee= session.get(Employee.class, 2);
		if (employee != null) {
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
			session.close();
			HibernateUtil.shutdown();
		}else {
			System.out.println("Employee doesn't exist with provides id.");
		}	
		
	}

	private static void updateEmployee(Session session) {
		
		Employee employee= session.get(Employee.class, 2);
		if (employee != null) {
			employee.setSalary(90000.00);
			session.beginTransaction();
			session.update(employee);
			session.getTransaction().commit();
			session.close();
			HibernateUtil.shutdown();
			
		}else {
			System.out.println("Employee doesn't exist with provides id.");
		}
	}

	private static void getEmployeeById(Session session) {
		Employee employee= session.get(Employee.class, 20);
		if (employee != null) {
			System.out.println(employee);
		}else {
			System.out.println("Employee doesn't exist with provides id.");
		}		
	}

	private static void createEmployee(Session session, Employee employee) {
		session.beginTransaction();
		Integer id= (Integer) session.save(employee);
		System.out.println("Employee is created with id:" + id);
		session.getTransaction().commit();
	}

	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Aditya Srivastva");
		employee.setEmail("aditya@gmail.com");
		employee.setSalary(50000.00);
		employee.setDoj(new Date());
		return employee;
	}
}
