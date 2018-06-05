# Many-To-Many Mapping


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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "employee_table")
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false, unique = true, length = 11)
	private Integer employeeId;

	@Column(name = "employee_name", length = 100, nullable = false)
	private String employeeName;

	@Column(name = "email", unique = false)
	private String email;

	@Column(name = "date_of_joining")
	private Date doj;

	@Column(name = "salary")
	private Double salary;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Address> addressList = new ArrayList<>();

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
				+ doj + ", salary=" + salary ;
	}

}


```
# Address Class
```
package com.aditya.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    
    @ManyToMany   
    private Collection<Employee> empList=new ArrayList<>();
    
    
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Collection<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(Collection<Employee> empList) {
		this.empList = empList;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode;
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
    
    create table address_table_employee_table (
       Address_address_id integer not null,
        empList_employee_id integer not null
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
    
    alter table address_table_employee_table 
       add constraint FKtepwod5ljute3v0fgp0rnhjtf 
       foreign key (empList_employee_id) 
       references employee_table (employee_id)
Hibernate: 
    
    alter table address_table_employee_table 
       add constraint FK6un5l437sy739djjfuftyc9u7 
       foreign key (Address_address_id) 
       references address_table (address_id)
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
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
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
        address_table_employee_table
        (Address_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (Address_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (Address_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (Address_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (Address_address_id, empList_employee_id) 
    values
        (?, ?)
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
        address_table_employee_table
        (Address_address_id, empList_employee_id) 
    values
        (?, ?)

```


# Case 2 Lazy loading


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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "employee_table")
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false, unique = true, length = 11)
	private Integer employeeId;

	@Column(name = "employee_name", length = 100, nullable = false)
	private String employeeName;

	@Column(name = "email", unique = false)
	private String email;

	@Column(name = "date_of_joining")
	private Date doj;

	@Column(name = "salary")
	private Double salary;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="empList")
	private List<Address> addressList = new ArrayList<>();

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
				+ doj + ", salary=" + salary ;
	}

}



```
# Address Class
```
package com.aditya.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    
    @ManyToMany/*(mappedBy="addressList") */  
    private Collection<Employee> empList=new ArrayList<>();
    
    
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Collection<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(Collection<Employee> empList) {
		this.empList = empList;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode;
	}
	
    
    
}



```
# Client Test Class
```
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
    
    create table address_table_employee_table (
       addressList_address_id integer not null,
        empList_employee_id integer not null
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
    
    alter table address_table_employee_table 
       add constraint FKtepwod5ljute3v0fgp0rnhjtf 
       foreign key (empList_employee_id) 
       references employee_table (employee_id)
Hibernate: 
    
    alter table address_table_employee_table 
       add constraint FK3cg39lmioo80fh1nhx8qbtfa9 
       foreign key (addressList_address_id) 
       references address_table (address_id)
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
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
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
        address_table_employee_table
        (addressList_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (addressList_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (addressList_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (addressList_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (addressList_address_id, empList_employee_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        address_table_employee_table
        (addressList_address_id, empList_employee_id) 
    values
        (?, ?)
```


# Case 3 Eager loading


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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "employee_table")
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false, unique = true, length = 11)
	private Integer employeeId;

	@Column(name = "employee_name", length = 100, nullable = false)
	private String employeeName;

	@Column(name = "email", unique = false)
	private String email;

	@Column(name = "date_of_joining")
	private Date doj;

	@Column(name = "salary")
	private Double salary;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Address> addressList = new ArrayList<>();

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
				+ doj + ", salary=" + salary ;
	}

}



```
# Address Class
```
package com.aditya.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    
    @ManyToMany(mappedBy="addressList")   
    private Collection<Employee> empList=new ArrayList<>();
    
    
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Collection<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(Collection<Employee> empList) {
		this.empList = empList;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode;
	}
	
    
    
}



```
# Client Test Class
```
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
       empList_employee_id integer not null,
        addressList_address_id integer not null
    ) engine=MyISAM
Hibernate: 
    
    alter table employee_table_address_table 
       add constraint FKfi3xkyuujvu17ymv0u5l8ri8c 
       foreign key (addressList_address_id) 
       references address_table (address_id)
Hibernate: 
    
    alter table employee_table_address_table 
       add constraint FKpy7t0m6od8xl0wjanmbj2g3qv 
       foreign key (empList_employee_id) 
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
        address_table
        (city_name, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?)
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
        employee_table_address_table
        (empList_employee_id, addressList_address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (empList_employee_id, addressList_address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (empList_employee_id, addressList_address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (empList_employee_id, addressList_address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (empList_employee_id, addressList_address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_table_address_table
        (empList_employee_id, addressList_address_id) 
    values
        (?, ?)

```