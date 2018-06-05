package com.aditya.hibernate.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class CascadeDelete {
	public static void main(String[] args) {
		deleteEmployeeById();
	    
	  }

	private static void deleteEmployeeById() {

	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Employee employee = session.get(Employee.class, 1);
	    	if(employee!= null){
	    		session.beginTransaction();
	    		session.delete(employee);
	    		session.getTransaction().commit();
	    	}
	    	
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}
}
