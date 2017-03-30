package com.uhg.optum.ssmo.otnd.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.ProjectDao;
import com.uhg.optum.ssmo.otnd.entity.Project;
import com.uhg.optum.ssmo.otnd.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDao projectDao;

	@Override
	public List<Project> getProjects() {
		return projectDao.getProjects();
	}

	@Override
	public void addProject(Project project) throws SQLException{
		projectDao.addProject(project);

	}

	@Override
	public void updateProject(Project project) throws SQLException {
		projectDao.updateProject(project);
	}

}
