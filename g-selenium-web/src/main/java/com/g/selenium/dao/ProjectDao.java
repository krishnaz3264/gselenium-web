package com.g.selenium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.g.selenium.pojo.Project;

public class ProjectDao {
	public void create(Project project) {
		try (Connection connection = DBConnectionFactory.getInstance().getConnection()) {
			final PreparedStatement prepareStatement = connection.prepareStatement("insert into project (name, url, environment, browser) values (?, ?, ?, ?)");
			prepareStatement.setString(1, project.getName());
			prepareStatement.setString(2, project.getUrl());
			prepareStatement.setString(3, project.getEnvironment());
			prepareStatement.setString(4, project.getBrowser());
			int count = prepareStatement.executeUpdate();
			if (count != 1) {
				throw new RuntimeException("Project not created. Please try again.");
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Server internal error.", e);
		}
	}

	public void createSubProject(Project project) {
		try (Connection connection = DBConnectionFactory.getInstance().getConnection()) {
			final PreparedStatement prepareStatement = connection.prepareStatement("insert into project (name, url, environment, browser, parent_project) values (?, ?, ?, ?, ?)");
			prepareStatement.setString(1, project.getName());
			prepareStatement.setString(2, project.getUrl());
			prepareStatement.setString(3, project.getEnvironment());
			prepareStatement.setString(4, project.getBrowser());
			prepareStatement.setLong(5, project.getParent().getId());
			int count = prepareStatement.executeUpdate();
			if (count != 1) {
				throw new RuntimeException("Project not created. Please try again.");
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Server internal error.", e);
		}
	}

	public List<Project> list() {
		try (Connection connection = DBConnectionFactory.getInstance().getConnection()) {
			final PreparedStatement prepareStatement = connection.prepareStatement("select id, name, url, environment, browser from project where parent_project is NULL");
			final ResultSet resultSet = prepareStatement.executeQuery();
			List<Project> projects = null;
			if (resultSet.next()) {
				projects = new LinkedList<>();
				createProject(resultSet, projects);
			}
			while (resultSet.next()) {
				createProject(resultSet, projects);
			}
			return projects;
		} catch (final SQLException e) {
			throw new RuntimeException("Server internal error.", e);
		}
	}

	public List<Project> listSubProjects(Project project) {
		try (Connection connection = DBConnectionFactory.getInstance().getConnection()) {
			final PreparedStatement prepareStatement = connection.prepareStatement("select id, name, url, environment, browser from project where parent_project = ?");
			prepareStatement.setLong(1, project.getId());
			final ResultSet resultSet = prepareStatement.executeQuery();
			List<Project> projects = null;
			if (resultSet.next()) {
				projects = new LinkedList<>();
				createProject(resultSet, projects);
			}
			while (resultSet.next()) {
				createProject(resultSet, projects);
			}
			return projects;
		} catch (final SQLException e) {
			throw new RuntimeException("Server internal error.", e);
		}
	}

	public void update(Project project) {
		try (Connection connection = DBConnectionFactory.getInstance().getConnection()) {
			final PreparedStatement prepareStatement = connection.prepareStatement("update project set name = ?, url = ?, environment = ?, browser = ? where id = ?");
			prepareStatement.setString(1, project.getName());
			prepareStatement.setString(2, project.getUrl());
			prepareStatement.setString(3, project.getEnvironment());
			prepareStatement.setString(4, project.getBrowser());
			prepareStatement.setLong(5, project.getId());
			int count = prepareStatement.executeUpdate();
			if (count != 1) {
				throw new RuntimeException("Project not updated. Pleas try again");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Server internal error.", e);
		}
	}

	private void createProject(final ResultSet resultSet, List<Project> projects) throws SQLException {
		Project project = new Project();
		project.setId(resultSet.getLong(1));
		project.setName(resultSet.getString(2));
		project.setUrl(resultSet.getString(3));
		project.setEnvironment(resultSet.getString(4));
		project.setBrowser(resultSet.getString(5));
		projects.add(project);
	}
}
