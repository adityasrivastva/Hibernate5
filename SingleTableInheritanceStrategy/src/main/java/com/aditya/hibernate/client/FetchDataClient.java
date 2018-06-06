package com.aditya.hibernate.client;

import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.model.Person;
import com.aditya.hibernate.model.Student;
import com.aditya.hibernate.util.HibernateUtil;

public class FetchDataClient {

	public static void main(String[] args) throws ParseException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Person person = session.get(Person.class, 3);
			if (person instanceof Person && !(person instanceof Employee) && !(person instanceof Student)) {
				System.out.println(person);
			} else if (person instanceof Person && (person instanceof Employee)) {
				Employee employee = (Employee) person;
				System.out.println(employee);
			} else if (person instanceof Person && (person instanceof Student)) {
				Student student = (Student) person;
				System.out.println(student);
			}

			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
