package com.uhg.optum.ssmo.otnd.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.EmployeeDao;
import com.uhg.optum.ssmo.otnd.entity.Employee;
@Repository("EmployeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	@Override
	public Employee getEmployee(String lanId) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("networkID", lanId));
		return (Employee) criteria.uniqueResult();
	}

	@Override
	public void addEmployee(Employee Employee) throws SQLException {
		persist(Employee);
	}

	@Override
	public void updateEmployee(Employee employee) throws SQLException {
		update(employee);

	}

	@Override
	public void deleteEmployee(Employee employee) throws SQLException{
		delete(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees(Employee emp) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.createAlias("roleType", "roleType");
		criteria.add(Restrictions.eq("roleType.id", emp.getRoleType().getId()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllUsernames() {
		Criteria criteria = getSession().createCriteria(Employee.class);
		return criteria.list();
	}

}
