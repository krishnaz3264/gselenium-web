package com.g.selenium.pojo;

import java.util.List;

public class Project {
	private long id;
	private String name;
	private String url;
	private String environment;
	private String browser;
	private List<Project> subProjectList;
	private Project parent;

	public String getBrowser() {
		return browser;
	}

	public String getEnvironment() {
		return environment;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Project getParent() {
		return parent;
	}

	public List<Project> getSubProjectList() {
		return subProjectList;
	}

	public String getUrl() {
		return url;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	public void setSubProjectList(List<Project> subProjectList) {
		this.subProjectList = subProjectList;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
