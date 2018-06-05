package com.aditya.hibernate.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class SaveDataClientTest {
	
	public static void main(String[] args) {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	session.beginTransaction();
	    	Employee employee= new Employee();
			employee.setEmployeeName("Barry Bingel");
			employee.setEmail("barry.cs2017@gmail.com");
			employee.setSalary(50000.00);
			employee.setDoj(new Date());
			
			Address address1 = new Address();
			address1.setCity("Chennai");
			address1.setPincode(9087727L);
			address1.setState("Tamilnadu");
			address1.setStreet("Park Street");
			
			employee.setAddress(address1);
			session.persist(employee);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	  }

}
