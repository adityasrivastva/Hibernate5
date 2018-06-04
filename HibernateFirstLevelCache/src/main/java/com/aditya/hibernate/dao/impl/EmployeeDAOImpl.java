/*package com.aditya.hibernate.dao.impl;

import org.hibernate.Session;

import com.aditya.hibernate.dao.EmployeeDAO;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public int addEmployee(Employee employee) {
		Integer id=null;
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			//Begin the Transactions
			session.beginTransaction();
			
			//Save the object 
			 id= (Integer) session.save(employee);
			 System.out.println("Employee is added with id:"+ id);
					
			//Commit the TXs.
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee=null;
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			employee = session.get(Employee.class, employeeId);
			if (employee != null) {
				return employee;
			}else {
				System.out.println("Employee doesn't exist with provides id.");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateEmployeeById(int employeeId, double newSal) {
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			
			Employee employee= session.get(Employee.class, employeeId);
			if (employee != null) {
				employee.setSalary(newSal);
				//Begin the Transactions
				session.beginTransaction();
				session.update(employee);
				//Commit the TXs.
				session.getTransaction().commit();
			}else {
				System.out.println("Employee doesn't exist with provides id.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmloyeeById(int employeeId) {
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			Employee employee= session.get(Employee.class, employeeId);
			if (employee != null) {
				//Begin the Transactions
				session.beginTransaction();
				session.delete(employee);
				//Commit the TXs.
				session.getTransaction().commit();
			}else {
				System.out.println("Employee doesn't exist with provides id.");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
*/