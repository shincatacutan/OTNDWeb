package com.uhg.optum.ssmo.otnd.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.ProjectDao;
import com.uhg.optum.ssmo.otnd.entity.Project;

@Repository
public class ProjectDaoImpl extends AbstractDao implements ProjectDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjects() {
		Criteria criteria = getSession().createCriteria(Project.class);
		return (List<Project>)criteria.list();
	}

	@Override
	public void addProject(Project project) throws SQLException {
		persist(project);
		
	}

	@Override
	public void updateProject(Project project) throws SQLException {
		update(project);
		
	}

}
