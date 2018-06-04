package com.aditya.hibernate.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class SaveDataClientTest {
	
	public static void main(String[] args) {
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Employee employee= new Employee();
			employee.setEmployeeName("Barry Bingel");
			employee.setEmail("barry2.cs2017@gmail.com");
			employee.setSalary(50000.00);
			employee.setDoj(new Date());
			
			Address address1 = new Address();
			address1.setCity("Chennai");
			address1.setPincode(9087727L);
			address1.setState("Tamilnadu");
			address1.setStreet("Park Street");
			
			Address address2 = new Address();
			address2.setCity("Blore");
			address2.setPincode(560049L);
			address2.setState("Karnatka");
			address2.setStreet("Btm Layout");
			
			employee.getAddressList().add(address1);
			employee.getAddressList().add(address2);
			
			address1.setEmployee(employee);
			address2.setEmployee(employee);
			
			session.persist(employee);
			session.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
