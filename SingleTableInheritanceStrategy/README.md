# Many-To-Many Mapping with custom


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
@DiscriminatorValue(value="employee_type")
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
	
	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", doj=" + doj + ", deptName=" + deptName + ", bonus=" + bonus
				+ ", email=" + email + "]"+ super.toString();
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
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//Default One
@DiscriminatorColumn(name="record_type", discriminatorType=DiscriminatorType.STRING)//String(By default), Char, Integer, Class
@DiscriminatorValue(value="person_type")
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
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender + "]";
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
@DiscriminatorValue(value="student_type")
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
	
	@Override
	public String toString() {
		return "Student [schoolName=" + schoolName + ", fee=" + fee + ", sectionName=" + sectionName + "]"+ super.toString();
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

# Output

```
Hibernate: 
    
    create table person_table (
       record_type varchar(31) not null,
        id integer not null auto_increment,
        gender varchar(10),
        name varchar(100),
        bonus decimal(6,3),
        dept_name varchar(30),
        date_of_joining datetime,
        email varchar(30),
        salary DECIMAL(7,2),
        fee float,
        school_name varchar(50),
        section_name varchar(50),
        primary key (id)
    ) engine=MyISAM
Hibernate: 
    
    alter table person_table 
       drop index UK_8y9kcan1s2c52b99srqjajjpi
Hibernate: 
    
    alter table person_table 
       add constraint UK_8y9kcan1s2c52b99srqjajjpi unique (email)
Hibernate: 
    insert 
    into
        person_table
        (gender, name, record_type) 
    values
        (?, ?, 'person_type')
Hibernate: 
    insert 
    into
        person_table
        (gender, name, fee, school_name, section_name, record_type) 
    values
        (?, ?, ?, ?, ?, 'student_type')
Hibernate: 
    insert 
    into
        person_table
        (gender, name, bonus, dept_name, date_of_joining, email, salary, record_type) 
    values
        (?, ?, ?, ?, ?, ?, ?, 'employee_type')

```

# FetchDataClient.java
```
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


```

# Output:

```
Hibernate: 
    select
        person0_.id as id2_0_0_,
        person0_.gender as gender3_0_0_,
        person0_.name as name4_0_0_,
        person0_.bonus as bonus5_0_0_,
        person0_.dept_name as dept_nam6_0_0_,
        person0_.date_of_joining as date_of_7_0_0_,
        person0_.email as email8_0_0_,
        person0_.salary as salary9_0_0_,
        person0_.fee as fee10_0_0_,
        person0_.school_name as school_11_0_0_,
        person0_.section_name as section12_0_0_,
        person0_.record_type as record_t1_0_0_ 
    from
        person_table person0_ 
    where
        person0_.id=?
Employee [salary=80000.29, doj=2015-12-18 00:00:00.0, deptName=IT, bonus=277.389, email=dipesh.cs@gmail.com]Person [id=3, name=Dipesh, gender=Male]


```