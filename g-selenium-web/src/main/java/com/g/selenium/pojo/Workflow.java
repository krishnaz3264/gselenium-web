package com.g.selenium.pojo;

import java.util.List;

public class Workflow {
	private Project project;
	private String name;
	private String path;
	private boolean isIgnorable;
	private boolean isPositive;
	private List<Step> steps;

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public Project getProject() {
		return project;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public boolean isIgnorable() {
		return isIgnorable;
	}

	public boolean isPositive() {
		return isPositive;
	}

	public void setIgnorable(boolean isIgnorable) {
		this.isIgnorable = isIgnorable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}
