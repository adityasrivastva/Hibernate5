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

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="employee_address_table",joinColumns=@JoinColumn(name="employee_id"),inverseJoinColumns=@JoinColumn(name="address_id"))
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

# Client Get Details
```
package com.aditya.hibernate.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.aditya.hibernate.model.Address;
import com.aditya.hibernate.model.Employee;
import com.aditya.hibernate.util.HibernateUtil;
public class FetchDataClientTest {
	public static void main(String[] args) {
		getEmployeeAndAdressByEmployeeId();
		//getEmployeeAndAdressByAddressId();
	    
	  }

	

	private static void getEmployeeAndAdressByEmployeeId() {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Employee employee2 = session.get(Employee.class , 1);
	    	System.out.println(employee2);
	    	/*if(employee2 != null){
	    		employee2.getAddressList().forEach(System.out::println);
	    	}*/
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 	private static void getEmployeeAndAdressByAddressId() {

	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	Address address = session.get(Address.class, 1);
	    	System.out.println(address);
	    	if(address != null){
	    		//System.out.println(address.getEmployee());
	    	}
	    	System.out.println();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}
	  
	 * */
}
```
# Output
```
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
    select
        employee0_.employee_id as employee1_2_0_,
        employee0_.date_of_joining as date_of_2_2_0_,
        employee0_.email as email3_2_0_,
        employee0_.employee_name as employee4_2_0_,
        employee0_.salary as salary5_2_0_,
        addresslis1_.employee_id as employee1_1_1_,
        address2_.address_id as address_2_1_1_,
        address2_.address_id as address_1_0_2_,
        address2_.city_name as city_nam2_0_2_,
        address2_.pin_code as pin_code3_0_2_,
        address2_.state_name as state_na4_0_2_,
        address2_.street_name as street_n5_0_2_ 
    from
        employee_table employee0_ 
    left outer join
        employee_address_table addresslis1_ 
            on employee0_.employee_id=addresslis1_.employee_id 
    left outer join
        address_table address2_ 
            on addresslis1_.address_id=address2_.address_id 
    where
        employee0_.employee_id=?
Employee [employeeId=1, employeeName=Barry Bingel, email=barry2.cs2017@gmail.com, doj=2018-06-05 13:53:33.0, salary=50000.0


```

