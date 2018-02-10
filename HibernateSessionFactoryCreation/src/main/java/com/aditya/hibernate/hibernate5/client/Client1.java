package com.aditya.hibernate.hibernate5.client;

import java.util.Date;

import org.hibernate.Session;

import com.aditya.hibernate.hibernate5.model.Employee;
import com.aditya.hibernate.hibernate5.util.HibernateUtil;

public class Client1 {
	public static void main(String[] args) {
		Employee emp1 = new Employee();
		emp1.setName("Rama");
		emp1.setRole("CEO");
		emp1.setInsertTime(new Date());
		
		
		Employee emp2 = new Employee();
		emp2.setName("Shyama");
		emp2.setRole("CEO");
		emp2.setInsertTime(new Date());
		
		Employee emp3 = new Employee();
		emp3.setName("Mahadeva");
		emp3.setRole("CEO");
		emp3.setInsertTime(new Date());
		//Get Session
		Session session= HibernateUtil.getSessionFactory().openSession();
		//start transaction
		session.beginTransaction();

		//Save the Model object
		
		/*
		 persist() -> This method is used to save an entity/Object into database and return void.
		  It'll throw an exception if an entity is already exists in database
		*/
		session.persist(emp1);
		/*
		 save() -> This method is used to save an entity/Object into database and return generated Primary Key.
		  It'll throw an exception if an entity is already exists in database
		*/
		Integer id= (Integer) session.save(emp2);
		
		
		System.out.println(id);
		
		session.saveOrUpdate(emp3);
		
		
		//Commit transaction
		session.getTransaction().commit();
		//System.out.println("Employee ID="+emp.getId());
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
		
		HibernateUtil.shutdown();
		
	}

}
