package com.uhg.optum.ssmo.otnd.dao;

import java.sql.SQLException;
import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Employee;


public interface EmployeeDao {
	public Employee getEmployee(String ntId);
	public List<Employee> getEmployees(Employee emp) throws SQLException;
	public void addEmployee (Employee emp) throws SQLException;
	public void updateEmployee (Employee emp) throws SQLException;
	public void deleteEmployee (Employee emp) throws SQLException;
	public List<Employee> getAllUsernames();
}
