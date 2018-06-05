package com.aditya.hibernate.client;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;

public class CriteriaTest {
	public static void main(String[] args) {
		try(Session session= HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query<Employee> query= session.createQuery("from Employee",Employee.class);
			List<Employee> list= query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				System.out.println(employee);
				
			}
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
