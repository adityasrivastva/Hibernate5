package com.aditya.hibernate.client;

import org.hibernate.Session;

import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class DirtyCheckingClientTest {
	
	public static void main(String[] args) {
		Session session=null;
		try {
			session=HibernateUtil.getSessionFactory().openSession();
			Employee employee= session.get(Employee.class, 1);
			if (employee !=null) {
				session.getTransaction().begin();
				employee.setSalary(200012.00);
				//session.update(employee);
				session.getTransaction().commit();	
				employee.setEmployeeName("Adi");
			}else {
				System.out.println("Employee desn't exist with provided id.");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session !=null) {
				session.close();
			}
		}
	}

}
