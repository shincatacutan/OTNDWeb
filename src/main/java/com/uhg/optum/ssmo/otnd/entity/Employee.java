package com.uhg.optum.ssmo.otnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	
	@Id
	@Column(name = "ntID")
	private String networkID;
	
	@Column(name = "empID")
	private String empID;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "firstName")
	private String firstName;

	@OneToOne
	@JoinColumn(name = "roleType")
	private Role roleType;
	
	@OneToOne
	@JoinColumn(name = "project")
	private Project project;
	
	
	public Employee(String networkID) {
		super();
		this.networkID = networkID;
	}
	public Employee() {
		super();
	}
	
	public String getNetworkID() {
		return networkID;
	}
	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Role getRoleType() {
		return roleType;
	}
	public void setRoleType(Role roleType) {
		this.roleType = roleType;
	}

	public String getFullName() {
		return  lastName + ", " + firstName;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
}
