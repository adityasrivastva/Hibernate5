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
			
			Employee employee1= new Employee();
			employee1.setEmployeeName("Barry Bingel");
			employee1.setEmail("barry2.cs2017@gmail.com");
			employee1.setSalary(50000.00);
			employee1.setDoj(new Date());
			
			Employee employee2= new Employee();
			employee2.setEmployeeName("Aditya Srivastva");
			employee2.setEmail("asrivastva@gmail.com");
			employee2.setSalary(70000.00);
			employee2.setDoj(new Date());
			
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
			
			Address address3 = new Address();
			address3.setCity("Blore");
			address3.setPincode(560049L);
			address3.setState("Karnatka");
			address3.setStreet("HBR Layout");
			
			Address address4 = new Address();
			address4.setCity("Blore");
			address4.setPincode(560049L);
			address4.setState("Karnatka");
			address4.setStreet("TC Layout");
			
			employee1.getAddressList().add(address1);
			employee1.getAddressList().add(address2);
			employee1.getAddressList().add(address3);
			
			address1.getEmpList().add(employee1);
			address2.getEmpList().add(employee1);
			address3.getEmpList().add(employee1);
			
			
			employee2.getAddressList().add(address2);
			employee2.getAddressList().add(address4);
			employee2.getAddressList().add(address3);
			
			address2.getEmpList().add(employee2);
			address4.getEmpList().add(employee2);
			address3.getEmpList().add(employee2);
			
			
			
			session.persist(employee1);
			session.persist(employee2);
			
			session.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
