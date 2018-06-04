package com.aditya.hibernate.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;
public class FetchDataClientTest {
	public static void main(String[] args) {
		Employee employee= null;
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			employee= session.get(Employee.class, 1);
			System.out.println(employee);
			Address address= employee.getAddress();
			System.out.println(address);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
}
}