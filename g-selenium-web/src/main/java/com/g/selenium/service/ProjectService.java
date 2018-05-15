package com.g.selenium.service;

import java.util.List;

import com.g.selenium.dao.ProjectDao;
import com.g.selenium.pojo.Project;

public class ProjectService {
	private final ProjectDao dao = new ProjectDao();

	public void create(Project project) {
		dao.create(project);
	}

	public void createSubProject(Project project) {
		dao.createSubProject(project);
	}

	public List<Project> list() {
		return dao.list();
	}

	public List<Project> listSubProjects(Project project) {
		return dao.listSubProjects(project);
	}

	public void update(Project project) {
		dao.update(project);
	}
}
