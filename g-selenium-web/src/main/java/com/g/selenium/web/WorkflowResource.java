package com.g.selenium.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.g.selenium.pojo.Project;
import com.g.selenium.pojo.Workflow;
import com.g.selenium.service.WorkflowService;

@Path("workflow")
public class WorkflowResource {
	private final WorkflowService service = new WorkflowService();

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Workflow create(final Workflow workflow) {
		service.create(workflow);
		return workflow;
	}

	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Workflow> list(final Project project) {
		return service.list(project);
	}
}
