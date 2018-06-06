# Many-To-Many Mapping with custom


# HibernateUtil.java

```
package com.aditya.hibernate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.aditya.hibernate.model.Employee;


public class HibernateUtil {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {

		if (sessionFactory == null) {

			try {

				// Create registry builder
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure();
				
				// Create registry
				registry = registryBuilder.build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				sources.addAnnotatedClass(Employee.class);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	public static Date getDoj(String doj) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(doj);
	}

}


//StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
/*
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Map<String, String> settings = new HashMap<>();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/userdb");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "maa2319");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.HBM2DDL_AUTO, "update");

				// Apply Settings
				registryBuilder.applySettings(settings);
*/


```

# Employee Class
```
package com.aditya.hibernate.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="employee_table")
public class Employee extends Person{
	
	@Column(name="salary",columnDefinition="DECIMAL(7,2)")
	//Here 7 is the Integral Part, and 2 is the decimal part of the number so:
	//1234567.12, 80000.29
	private Double salary;
	
	@Column(name="date_of_joining")
	private Date doj;
	
	@Column(name="dept_name",length=30)
	private String deptName;
	
	@Column(name="bonus",precision=6,scale=3)
	//Precision: Total number of significant digits 
	//Scale: Number of digits to the right side of decimal point
	private BigDecimal bonus;
	
	
	
	@Column(name="email",length=30,unique=true)
	private String email;
	
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
}
```

# Person Class
```
package com.aditya.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "person_table")
@Inheritance(strategy=InheritanceType.JOINED)

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "gender", length = 10)
	private String gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
```


# Student Class

```
package com.aditya.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="student_table")
public class Student extends Person {

	@Column(name="school_name",length=50)
	private String schoolName;
	
	@Column(name="fee")
	private float fee;
	
	@Column(name="section_name",length=50)
	private String sectionName;
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}


```

# Client Code

```
package com.aditya.hibernate.client;

import java.math.BigDecimal;
import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.model.Person;
import com.aditya.hibernate.model.Student;
import com.aditya.hibernate.util.HibernateUtil;

public class SaveDataClientTest {
	
	public static void main(String[] args) throws ParseException {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Person person = new Person();
			person.setName("Sudha Verma");
			person.setGender("Female");
			
			Employee employee = new Employee();
			employee.setBonus(new BigDecimal("277.389"));
			employee.setDeptName("IT");
			employee.setDoj(HibernateUtil.getDoj("18/12/2015"));
			employee.setEmail("dipesh.cs@gmail.com");
			employee.setName("Dipesh");
			employee.setSalary(80000.2872);
			employee.setGender("Male");
			
			Student student = new Student();
			student.setName("Shuruti");
			student.setGender("Female");
			student.setFee(20000.00f);
			student.setSchoolName("DPS");
			student.setSectionName("12th Std");
			
			session.beginTransaction();
			session.save(person);
			session.save(student);
			session.save(employee);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	  }
}


```



# Output

```
Hibernate: 
    
    create table employee_table (
       bonus decimal(6,3),
        dept_name varchar(30),
        date_of_joining datetime,
        email varchar(30),
        salary DECIMAL(7,2),
        id integer not null,
        primary key (id)
    ) engine=MyISAM
Hibernate: 
    
    create table person_table (
       id integer not null auto_increment,
        gender varchar(10),
        name varchar(100),
        primary key (id)
    ) engine=MyISAM
Hibernate: 
    
    create table student_table (
       fee float,
        school_name varchar(50),
        section_name varchar(50),
        id integer not null,
        primary key (id)
    ) engine=MyISAM
Hibernate: 
    
    alter table employee_table 
       drop index UK_2casspobvavvi9s23312f9mhm
Hibernate: 
    
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    
    alter table employee_table 
       add constraint FK89dpnpoj5rf8uydnhxrxtvsd0 
       foreign key (id) 
       references person_table (id)
Hibernate: 
    
    alter table student_table 
       add constraint FKrq0k3q93bnumyakrikequqgu0 
       foreign key (id) 
       references person_table (id)
Hibernate: 
    insert 
    into
        person_table
        (gender, name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        person_table
        (gender, name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        student_table
        (fee, school_name, section_name, id) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        person_table
        (gender, name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table
        (bonus, dept_name, date_of_joining, email, salary, id) 
    values
        (?, ?, ?, ?, ?, ?)



```