# Employee Class
```
package com.aditya.hibernate.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="date_of_joining")
	private Date doj;

	@Column(name="salary")
	private Double salary;
	
	@ElementCollection
	private Collection<Address> addresses = new HashSet<>();
	
	

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

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}
	
	


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + ", addresses=" + addresses + "]";
	}

	
}


```
# Address Class
```
package com.aditya.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
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
public class ClientTest {
	public static void main(String[] args) {
			EmployeeService employeeService= new EmployeeServiceImpl();
			
			createEmployee(employeeService);
			//getEmployeeById(employeeService);
			//updateEmployee(employeeService);
			//deleteEmployeeById(employeeService);
			
	}

	
	
	
	
	private static void deleteEmployeeById(EmployeeService employeeService) {
		employeeService.deleteEmloyeeById(2);
		
	}

	private static void updateEmployee(EmployeeService employeeService) {
		employeeService.updateEmployeeById(1, 56000.00);
	}

	private static void getEmployeeById(EmployeeService employeeService) {
		Employee employee= employeeService.getEmployeeById(3);
		System.out.println(employee);
	}

	private static void createEmployee(EmployeeService employeeService) {
		employeeService.addEmployee(getEmployee());
	}

	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Priya Srivastva");
		employee.setEmail("priya@gmail.com");
		employee.setSalary(50000.00);
		employee.setDoj(new Date());
		
		Address homeaddress= new Address();
		homeaddress.setCity("Patna");
		homeaddress.setPincode(8000001L);
		homeaddress.setState("Bihar");
		homeaddress.setStreet("JaKkkanpur");
		
		employee.setHomeaddress(homeaddress);
		
		Address officeaddress= new Address();
		officeaddress.setCity("Patna1");
		officeaddress.setPincode(8000002L);
		officeaddress.setState("Bihar1");
		officeaddress.setStreet("JaKkkanpur1");
		
		employee.setOfficeaddress(officeaddress);
		return employee;
	}
}

```
# Output
```
Hibernate: 
 create table Employee_addresses (
   Employee_employee_id integer not null,
   city_name varchar(50),
   pin_code bigint,
   state_name varchar(255),
   street_name varchar(50)
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
    alter table employee_table 
       drop index UK_2casspobvavvi9s23312f9mhm
Hibernate: 
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    alter table Employee_addresses 
       add constraint FKio6h60n7giag98ce5amynjdt8 
       foreign key (Employee_employee_id) 
       references employee_table (employee_id)
Hibernate: 
    insert into
        employee_table
        (date_of_joining, email, employee_name, salary) 
    values
        (?, ?, ?, ?)
Employee is added with id:1
Hibernate: 
    insert into
        Employee_addresses
        (Employee_employee_id, city_name, pin_code, state_name, street_name) 
    values(?, ?, ?, ?, ?)
Hibernate: 
    insert into
        Employee_addresses
        (Employee_employee_id, city_name, pin_code, state_name, street_name) 
    values (?, ?, ?, ?, ?)

```


