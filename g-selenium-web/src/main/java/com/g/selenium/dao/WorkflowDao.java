package com.g.selenium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.g.selenium.pojo.Project;
import com.g.selenium.pojo.Step;
import com.g.selenium.pojo.Workflow;

public class WorkflowDao {
	public void create(Workflow workflow) {
		try (Connection connection = DBConnectionFactory.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement("insert into step () values ()");
			for (Step step : workflow.getSteps()) {
				preparedStatement.setString(1, step.getSelector());
				preparedStatement.setString(2, step.getName());
				preparedStatement.setString(3, step.getAction());
				preparedStatement.setString(4, step.getValue());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Server internal error. Please try again later.", e);
		}
	}

	public List<Workflow> list(Project project) {
		// TODO Auto-generated method stub
		return null;
	}
}
