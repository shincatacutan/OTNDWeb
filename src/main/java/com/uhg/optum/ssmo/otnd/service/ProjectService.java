package com.uhg.optum.ssmo.otnd.service;

import java.sql.SQLException;
import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Project;

public interface ProjectService {
	public List<Project> getProjects();
	public void addProject(Project project) throws SQLException;
	public void updateProject(Project project) throws SQLException;
}
