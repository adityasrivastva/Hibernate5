package com.aditya.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
	
	@Embedded
	@AttributeOverrides(value=
			{@AttributeOverride(name="street", column= @Column(name= "home_street_name", length=50)),
	        @AttributeOverride(name="city", column=@Column(name="home_city_name", length=50)),
	        @AttributeOverride(name="state", column=@Column(name="home_state_name")),
	        @AttributeOverride(name="pincode", column=@Column(name="home_pin_code")),
	    })
	private Address homeaddress;
	@Embedded
	@AttributeOverrides(value=
			{@AttributeOverride(name="street", column= @Column(name= "office_street_name", length=50)),
	        @AttributeOverride(name="city", column=@Column(name="office_city_name", length=50)),
	        @AttributeOverride(name="state", column=@Column(name="office_state_name")),
	        @AttributeOverride(name="pincode", column=@Column(name="office_pin_code")),
	    })
	private Address officeaddress;

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
	
	
	
	

	public Address getHomeaddress() {
		return homeaddress;
	}

	public void setHomeaddress(Address homeaddress) {
		this.homeaddress = homeaddress;
	}

	public Address getOfficeaddress() {
		return officeaddress;
	}

	public void setOfficeaddress(Address officeaddress) {
		this.officeaddress = officeaddress;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + ", homeaddress=" + homeaddress + ", officeaddress=" + officeaddress
				+ "]";
	}

	
	
	
}
