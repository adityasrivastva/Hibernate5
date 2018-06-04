#Case 1


# Employee Class
```
package com.aditya.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="employee_table")
@DynamicUpdate
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		System.out.println("SB");
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id", nullable=false, unique=true, length=11)
	private Integer employeeId;
	
	@Column(name="employee_name", length=100, nullable=false)
	private String employeeName;
	
	@Column(name="email", unique=false)
	private String email;
	
	@Column(name="date_of_joining")
	private Date doj;

	@Column(name="salary")
	private Double salary;
	
	@OneToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="address_id")
	private List<Address> addressList= new ArrayList<>();

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	
	

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + ", address=" + addressList + "]";
	}

	
	
}


```
# Address Class
```
package com.aditya.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_table")
public class Address {
	
	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "street_name",length=50)
    private String street;
    @Column(name = "city_name",length=50)
    private String city;
    @Column(name = "state_name")
    private String state;
    @Column(name = "pin_code")
    private Long pincode;
    
    
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
    
}

```
# Client Test Class
```
package com.aditya.hibernate.client;

import java.util.Date;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.service.EmployeeService;
import com.aditya.hibernate.service.impl.EmployeeServiceImpl;
public class SaveDataClient {
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
			
			session.persist(employee);
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
    
    create table address_table (
       address_id integer not null auto_increment,
        city_name varchar(50),
        pin_code bigint,
        state_name varchar(255),
        street_name varchar(50),
        primary key (address_id)
    ) engine=MyISAM
Hibernate: 
    
    create table employee_table (
       employee_id integer not null auto_increment,
        date_of_joining datetime,
        email varchar(255),
        employee_name varchar(100) not null,
        salary double precision,
        primary key (employee_id)
    ) engine=MyISAM
Hibernate: 
    
    create table employee_table_address_table (
       Employee_employee_id integer not null,
        addressList_address_id integer not null
    ) engine=MyISAM
Hibernate: 
    
    alter table employee_table_address_table 
       drop index UK_fgacrxqyn7tr010ppd3lfl9iq
Hibernate: 
    
    alter table employee_table_address_table 
       add constraint UK_fgacrxqyn7tr010ppd3lfl9iq unique (addressList_address_id)
Hibernate: 
    
    alter table employee_table_address_table 
       add constraint FKfi3xkyuujvu17ymv0u5l8ri8c 
       foreign key (addressList_address_id) 
       references address_table (address_id)
Hibernate: 
    
    alter table employee_table_address_table 
       add constraint FK7usvkax2fh25lx5cqpntfpfmp 
       foreign key (Employee_employee_id) 
       references employee_table (employee_id)
Hibernate: 
    insert 
    into
        employee_table
        (date_of_joining, email, employee_name, salary) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (Employee_employee_id, addressList_address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (Employee_employee_id, addressList_address_id) 
    values
        (?, ?)
```


#Case 2


# Employee Class
```
package com.aditya.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="employee_table")
@DynamicUpdate
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		System.out.println("SB");
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id", nullable=false, unique=true, length=11)
	private Integer employeeId;
	
	@Column(name="employee_name", length=100, nullable=false)
	private String employeeName;
	
	@Column(name="email", unique=false)
	private String email;
	
	@Column(name="date_of_joining")
	private Date doj;

	@Column(name="salary")
	private Double salary;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "employee_address_table", 	joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))

	private List<Address> addressList= new ArrayList<>();

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	
	

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + ", address=" + addressList + "]";
	}

	
	
}


```
# Address Class
```
package com.aditya.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_table")
public class Address {
	
	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "street_name",length=50)
    private String street;
    @Column(name = "city_name",length=50)
    private String city;
    @Column(name = "state_name")
    private String state;
    @Column(name = "pin_code")
    private Long pincode;
    
    
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
    
}

```
# Client Test Class
```
package com.aditya.hibernate.client;

import java.util.Date;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.service.EmployeeService;
import com.aditya.hibernate.service.impl.EmployeeServiceImpl;
public class SaveDataClient {
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
			
			session.persist(employee);
			session.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}

```
# Output
```
Jun 05, 2018 12:24:24 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate Core {5.2.12.Final}
Jun 05, 2018 12:24:24 AM org.hibernate.cfg.Environment <clinit>
INFO: HHH000206: hibernate.properties not found
Jun 05, 2018 12:24:24 AM org.hibernate.annotations.common.reflection.java.JavaReflectionManager <clinit>
INFO: HCANN000001: Hibernate Commons Annotations {5.0.1.Final}
Jun 05, 2018 12:24:24 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
Jun 05, 2018 12:24:24 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [com.mysql.cj.jdbc.Driver] at URL [jdbc:mysql://localhost:3306/userdb]
Jun 05, 2018 12:24:24 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {user=root, password=****}
Jun 05, 2018 12:24:24 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
Jun 05, 2018 12:24:24 AM org.hibernate.engine.jdbc.connections.internal.PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
Tue Jun 05 00:24:24 IST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Jun 05, 2018 12:24:24 AM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.MySQL5Dialect
SB
Jun 05, 2018 12:24:25 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@607fbe09] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 
    
    create table address_table (
       address_id integer not null auto_increment,
        city_name varchar(50),
        pin_code bigint,
        state_name varchar(255),
        street_name varchar(50),
        primary key (address_id)
    ) engine=MyISAM
Hibernate: 
    
    create table employee_address_table (
       employee_id integer not null,
        address_id integer not null
    ) engine=MyISAM
Hibernate: 
    
    create table employee_table (
       employee_id integer not null auto_increment,
        date_of_joining datetime,
        email varchar(255),
        employee_name varchar(100) not null,
        salary double precision,
        primary key (employee_id)
    ) engine=MyISAM
Hibernate: 
    
    alter table employee_address_table 
       drop index UK_82ypxil54gfsgm9leblfceekm
Hibernate: 
    
    alter table employee_address_table 
       add constraint UK_82ypxil54gfsgm9leblfceekm unique (address_id)
Hibernate: 
    
    alter table employee_address_table 
       add constraint FKhfepimyny20rkf7syat5aek4y 
       foreign key (address_id) 
       references address_table (address_id)
Hibernate: 
    
    alter table employee_address_table 
       add constraint FKsdltnwu6d8hy4t4uvmlju2lt4 
       foreign key (employee_id) 
       references employee_table (employee_id)
Hibernate: 
    insert 
    into
        employee_table
        (date_of_joining, email, employee_name, salary) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        employee_address_table
        (employee_id, address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_address_table
        (employee_id, address_id) 
    values
        (?, ?)

```
