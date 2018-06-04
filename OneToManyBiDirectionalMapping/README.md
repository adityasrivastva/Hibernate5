# Many Side we have to add FK column using @ManyToOne annotation


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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
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
import javax.persistence.JoinColumn;
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
    
    @ManyToOne
    @JoinColumn(name="employee_id") // Optional
    private Employee employee;
    
    
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + ", employee=" + employee + "]";
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
			
			address1.setEmployee(employee);
			address2.setEmployee(employee);
			
			
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
        employee_id integer,
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
    
    alter table address_table 
       add constraint FKff2mf17w32xkm3emotdt6of59 
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
        (city_name, employee_id, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, employee_id, pin_code, state_name, street_name) 
    values
        (?, ?, ?, ?, ?)


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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @ManyToOne
    @JoinColumn(name="employee_id") // Optional
    private Employee employee;
    
    
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + ", employee=" + employee + "]";
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
public class FetchDataClientTest {
	public static void main(String[] args) {
		getEmployeeAndAdressByEmployeeId();
		//getEmployeeAndAdressByAddressId();
	    
	  }

	private static void getEmployeeAndAdressByAddressId() {

	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Address address = session.get(Address.class, 1);
	    	System.out.println(address);
	    	if(address != null){
	    		System.out.println(address.getEmployee());
	    	}
	    	System.out.println();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}

	private static void getEmployeeAndAdressByEmployeeId() {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Employee employee2 = session.get(Employee.class , 1);
	    	System.out.println(employee2);
	    	
	    	//Lazy Loading
	    	if(employee2 != null){
	    		employee2.getAddressList().forEach(System.out::println);
	    	}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}

```
# Output
```
Hibernate: 
    select
        employee0_.employee_id as employee1_1_0_,
        employee0_.date_of_joining as date_of_2_1_0_,
        employee0_.email as email3_1_0_,
        employee0_.employee_name as employee4_1_0_,
        employee0_.salary as salary5_1_0_ 
    from
        employee_table employee0_ 
    where
        employee0_.employee_id=?
Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0
Hibernate: 
    select
        addresslis0_.employee_id as employee6_0_0_,
        addresslis0_.address_id as address_1_0_0_,
        addresslis0_.address_id as address_1_0_1_,
        addresslis0_.city_name as city_nam2_0_1_,
        addresslis0_.employee_id as employee6_0_1_,
        addresslis0_.pin_code as pin_code3_0_1_,
        addresslis0_.state_name as state_na4_0_1_,
        addresslis0_.street_name as street_n5_0_1_ 
    from
        address_table addresslis0_ 
    where
        addresslis0_.employee_id=?
Address [id=1, street=Park Street, city=Chennai, state=Tamilnadu, pincode=9087727, employee=Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0]
Address [id=2, street=Btm Layout, city=Blore, state=Karnatka, pincode=560049, employee=Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0]


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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy="employee", fetch=FetchType.EAGER)
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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @ManyToOne
    @JoinColumn(name="employee_id") // Optional
    private Employee employee;
    
    
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + ", employee=" + employee + "]";
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
public class FetchDataClientTest {
	public static void main(String[] args) {
		getEmployeeAndAdressByEmployeeId();
		//getEmployeeAndAdressByAddressId();
	    
	  }

	private static void getEmployeeAndAdressByAddressId() {

	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Address address = session.get(Address.class, 1);
	    	System.out.println(address);
	    	if(address != null){
	    		System.out.println(address.getEmployee());
	    	}
	    	System.out.println();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}

	private static void getEmployeeAndAdressByEmployeeId() {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Employee employee2 = session.get(Employee.class , 1);
	    	System.out.println(employee2);
	    	
	    	//Lazy Loading
	    	if(employee2 != null){
	    		employee2.getAddressList().forEach(System.out::println);
	    	}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}

```
# Output
```
Hibernate: 
    select
        employee0_.employee_id as employee1_1_0_,
        employee0_.date_of_joining as date_of_2_1_0_,
        employee0_.email as email3_1_0_,
        employee0_.employee_name as employee4_1_0_,
        employee0_.salary as salary5_1_0_,
        addresslis1_.employee_id as employee6_0_1_,
        addresslis1_.address_id as address_1_0_1_,
        addresslis1_.address_id as address_1_0_2_,
        addresslis1_.city_name as city_nam2_0_2_,
        addresslis1_.employee_id as employee6_0_2_,
        addresslis1_.pin_code as pin_code3_0_2_,
        addresslis1_.state_name as state_na4_0_2_,
        addresslis1_.street_name as street_n5_0_2_ 
    from
        employee_table employee0_ 
    left outer join
        address_table addresslis1_ 
            on employee0_.employee_id=addresslis1_.employee_id 
    where
        employee0_.employee_id=?
Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0
Address [id=1, street=Park Street, city=Chennai, state=Tamilnadu, pincode=9087727, employee=Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0]
Address [id=2, street=Btm Layout, city=Blore, state=Karnatka, pincode=560049, employee=Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0]

```

# 4. EmployeeByAddressId

# Client Test Class
```
package com.aditya.hibernate.client;

import java.util.Date;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.service.EmployeeService;
import com.aditya.hibernate.service.impl.EmployeeServiceImpl;
public class FetchDataClientTest {
	public static void main(String[] args) {
		getEmployeeAndAdressByEmployeeId();
		//getEmployeeAndAdressByAddressId();
	    
	  }

	private static void getEmployeeAndAdressByAddressId() {

	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Address address = session.get(Address.class, 1);
	    	System.out.println(address);
	    	if(address != null){
	    		System.out.println(address.getEmployee());
	    	}
	    	System.out.println();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}

	private static void getEmployeeAndAdressByEmployeeId() {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Employee employee2 = session.get(Employee.class , 1);
	    	System.out.println(employee2);
	    	
	    	//Lazy Loading
	    	if(employee2 != null){
	    		employee2.getAddressList().forEach(System.out::println);
	    	}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}

```
# Output
```
Hibernate: 
    select
        address0_.address_id as address_1_0_0_,
        address0_.city_name as city_nam2_0_0_,
        address0_.employee_id as employee6_0_0_,
        address0_.pin_code as pin_code3_0_0_,
        address0_.state_name as state_na4_0_0_,
        address0_.street_name as street_n5_0_0_,
        employee1_.employee_id as employee1_1_1_,
        employee1_.date_of_joining as date_of_2_1_1_,
        employee1_.email as email3_1_1_,
        employee1_.employee_name as employee4_1_1_,
        employee1_.salary as salary5_1_1_ 
    from
        address_table address0_ 
    left outer join
        employee_table employee1_ 
            on address0_.employee_id=employee1_.employee_id 
    where
        address0_.address_id=?
Hibernate: 
    select
        addresslis0_.employee_id as employee6_0_0_,
        addresslis0_.address_id as address_1_0_0_,
        addresslis0_.address_id as address_1_0_1_,
        addresslis0_.city_name as city_nam2_0_1_,
        addresslis0_.employee_id as employee6_0_1_,
        addresslis0_.pin_code as pin_code3_0_1_,
        addresslis0_.state_name as state_na4_0_1_,
        addresslis0_.street_name as street_n5_0_1_ 
    from
        address_table addresslis0_ 
    where
        addresslis0_.employee_id=?
Address [id=1, street=Park Street, city=Chennai, state=Tamilnadu, pincode=9087727, employee=Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0]
Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 01:20:06.0, salary=50000.0


```

