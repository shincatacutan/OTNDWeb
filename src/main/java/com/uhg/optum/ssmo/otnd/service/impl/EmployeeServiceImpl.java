package com.uhg.optum.ssmo.otnd.service.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.EmployeeDao;
import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.service.EmployeeService;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee getEmployee(String ntID) {
		return employeeDao.getEmployee(ntID);
	}

	@Override
	public void addEmployee(Employee employee) throws SQLException{
		employeeDao.addEmployee(employee);

	}

	@Override
	public void updateEmployee(Employee employee) throws SQLException{
		employeeDao.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) throws SQLException {
		employeeDao.deleteEmployee(employee);

	}

	@Override
	public List<Employee> getEmployees(Employee lanID) throws SQLException {
		// TODO Auto-generated method stub
		return employeeDao.getEmployees(lanID);
	}

	@Override
	public Set<String> getAllUsernames() {
		Set<String> usernames = new HashSet<String>();
		for(Employee e: employeeDao.getAllUsernames()){
			usernames.add(e.getNetworkID());
		}
		return usernames;
	}

}
