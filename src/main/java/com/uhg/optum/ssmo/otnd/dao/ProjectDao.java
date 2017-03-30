package com.uhg.optum.ssmo.otnd.dao;

import java.sql.SQLException;
import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Project;

public interface ProjectDao {
	public List<Project> getProjects();
	public void addProject(Project project) throws SQLException;
	public void updateProject(Project project) throws SQLException;
}
