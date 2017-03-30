package com.uhg.optum.ssmo.otnd.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.uhg.optum.ssmo.otnd.entity.Employee;

public interface EmployeeService {
	public Employee getEmployee(String ntID);
	
	public List<Employee> getEmployees(Employee ntID) throws SQLException;

	public void addEmployee(Employee Employee) throws SQLException;

	public void updateEmployee(Employee Employee) throws SQLException;

	public void deleteEmployee(Employee Employee) throws SQLException;
	
	public Set<String> getAllUsernames();
}
