package com.g.selenium.service;

import java.util.List;

import com.g.selenium.dao.WorkflowDao;
import com.g.selenium.pojo.Project;
import com.g.selenium.pojo.Workflow;

public class WorkflowService {
	private final WorkflowDao dao = new WorkflowDao();

	public void create(Workflow workflow) {
		dao.create(workflow);
	}

	public List<Workflow> list(Project project) {
		return dao.list(project);
	}
}
