package com.g.selenium.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.g.selenium.pojo.Project;
import com.g.selenium.service.ProjectService;

@Path("project")
public class ProjectResource {
	private final ProjectService service = new ProjectService();

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(final Project project) {
		service.create(project);
		return Response.status(Status.CREATED).build();
	}

	@Path("child/create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSubProject(final Project project) {
		service.createSubProject(project);
		return Response.status(Status.CREATED).build();
	}

	@Path("list")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> list() {
		return service.list();
	}

	@Path("child/list")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> listSubProjects(Project project) {
		return service.listSubProjects(project);
	}

	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void update(final Project project) {
		service.update(project);
	}
}
