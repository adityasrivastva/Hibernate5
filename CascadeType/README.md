# Many-To-Many Mapping with custom


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

	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="address_id")
	private Address address;

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

	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    
    @OneToOne(mappedBy="address")
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
				+ pincode;
	}
	
    
    
}



```
# Client Test Class
```
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
	    	Employee employee = session.get(Employee.class, 2);
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

```
# Output
```

Hibernate: 
    
    alter table employee_table 
       add constraint FK4nfdimis4bkn38mvl2btm69p2 
       foreign key (address_id) 
       references address_table (address_id)
Hibernate: 
    select
        employee0_.employee_id as employee1_1_0_,
        employee0_.address_id as address_6_1_0_,
        employee0_.date_of_joining as date_of_2_1_0_,
        employee0_.email as email3_1_0_,
        employee0_.employee_name as employee4_1_0_,
        employee0_.salary as salary5_1_0_,
        address1_.address_id as address_1_0_1_,
        address1_.city_name as city_nam2_0_1_,
        address1_.pin_code as pin_code3_0_1_,
        address1_.state_name as state_na4_0_1_,
        address1_.street_name as street_n5_0_1_ 
    from
        employee_table employee0_ 
    left outer join
        address_table address1_ 
            on employee0_.address_id=address1_.address_id 
    where
        employee0_.employee_id=?
Hibernate: 
    select
        employee0_.employee_id as employee1_1_1_,
        employee0_.address_id as address_6_1_1_,
        employee0_.date_of_joining as date_of_2_1_1_,
        employee0_.email as email3_1_1_,
        employee0_.employee_name as employee4_1_1_,
        employee0_.salary as salary5_1_1_,
        address1_.address_id as address_1_0_0_,
        address1_.city_name as city_nam2_0_0_,
        address1_.pin_code as pin_code3_0_0_,
        address1_.state_name as state_na4_0_0_,
        address1_.street_name as street_n5_0_0_ 
    from
        employee_table employee0_ 
    left outer join
        address_table address1_ 
            on employee0_.address_id=address1_.address_id 
    where
        employee0_.address_id=?
Hibernate: 
    delete 
    from
        employee_table 
    where
        employee_id=?

```
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

	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="address_id")
	private Address address;

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

	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    
    @OneToOne(mappedBy="address")
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
				+ pincode;
	}
	
    
    
}



```
# Client Test Class
```
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
	    	Employee employee = session.get(Employee.class, 2);
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

```

# Output

```
Hibernate: 
    
    alter table employee_table 
       add constraint FK4nfdimis4bkn38mvl2btm69p2 
       foreign key (address_id) 
       references address_table (address_id)
Hibernate: 
    select
        employee0_.employee_id as employee1_1_0_,
        employee0_.address_id as address_6_1_0_,
        employee0_.date_of_joining as date_of_2_1_0_,
        employee0_.email as email3_1_0_,
        employee0_.employee_name as employee4_1_0_,
        employee0_.salary as salary5_1_0_,
        address1_.address_id as address_1_0_1_,
        address1_.city_name as city_nam2_0_1_,
        address1_.pin_code as pin_code3_0_1_,
        address1_.state_name as state_na4_0_1_,
        address1_.street_name as street_n5_0_1_ 
    from
        employee_table employee0_ 
    left outer join
        address_table address1_ 
            on employee0_.address_id=address1_.address_id 
    where
        employee0_.employee_id=?
Hibernate: 
    select
        employee0_.employee_id as employee1_1_1_,
        employee0_.address_id as address_6_1_1_,
        employee0_.date_of_joining as date_of_2_1_1_,
        employee0_.email as email3_1_1_,
        employee0_.employee_name as employee4_1_1_,
        employee0_.salary as salary5_1_1_,
        address1_.address_id as address_1_0_0_,
        address1_.city_name as city_nam2_0_0_,
        address1_.pin_code as pin_code3_0_0_,
        address1_.state_name as state_na4_0_0_,
        address1_.street_name as street_n5_0_0_ 
    from
        employee_table employee0_ 
    left outer join
        address_table address1_ 
            on employee0_.address_id=address1_.address_id 
    where
        employee0_.address_id=?
Hibernate: 
    delete 
    from
        employee_table 
    where
        employee_id=?
Hibernate: 
    delete 
    from
        address_table 
    where
        address_id=?



```


# JPA Cascade Types

The Cascade types supported by the Java Persistence Architecture are as below:
# CascadeType.PERSIST: means that save() or persist() operations cascade to related entities.
# CascadeType.MERGE: means that related entities are merged when the owing entity is merged.
# CascadeType.REFRESH: does the same thing for the refresh() operations.
# CascadeType.REMOVE: removes all the related entities association with this settings when the owing entity is deleted.
# CascadeType.DETACH: detached all the related entities if a "manual detach" occurs
# CascadeType.ALL: is shorthand for all the above cascade operations.