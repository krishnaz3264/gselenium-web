gSeleniumWebApp.controller("ProjectController", function($scope, $location,
		$modal, $window, GSeleniumWebAppService) {
	
	var projects;
	
	$scope.environments = ["Prod", "Pre Prod", "QA", "Dev"];
	$scope.browsers = ["Chrome", "Firefox", "IE", "All"];
	
	$scope.init = function () {
		$scope.newProject = {};
		$scope.newProject.environment = $scope.environments[0];
		$scope.newProject.browser = $scope.browsers[0];
		$scope.newProject.name = undefined;
		$scope.newProject.url = undefined;
		
		getProjectList();
	}
	
	$scope.create = function () {
		
		GSeleniumWebAppService.createProject($scope.newProject, function (isSuccessful, response) {
			if(isSuccessful) {
				getProjectList();
			}
		});
	}
	
	$scope.edit = function (project) {
		
		project.disabled = "";
		project.editAndConfigureButtonStyle = {display: "none"};
		project.cancelAndUpdateButtonStyle = {display: ""};
	}
	
	$scope.cancel = function(project) {
		
		project.disabled = "disabled";
		project.editAndConfigureButtonStyle = {display: ""};
		project.cancelAndUpdateButtonStyle = {display: "none"};
		
		for(var i in projects) {
			if(projects[i].id == project.id) {
				project.name = projects[i].name;
				project.url = projects[i].url;
				project.environment = projects[i].environment;
				project.browser = projects[i].browser;
			}
		}
	}
	
	$scope.configure = function (project) {
		$location.path("/workflow").search({id: project.id});
	}
	
	$scope.update = function (project, isSubProject) {
		
		var payload = {};
		payload.id = project.id;
		payload.name = project.name;
		payload.url = project.url;
		payload.environment = project.environment;
		payload.browser = project.browser;
		
		GSeleniumWebAppService.updateProject(payload, function (isSuccessful, response) {
			
			if(isSuccessful) {
				if(isSubProject) {
					getSubProjectList(project);
				} else {
					getProjectList();
				}
			}
		});
	}
	
	$scope.expandOrCollapseSubProjectsSection = function (project) {
		if (project.showSubProjects.display === '') {
			project.showSubProjects = {display: 'none'};
		} else {
			project.showSubProjects = {display: ''};
			getSubProjectList(project);
		}
	}
	
	$scope.createSubProject = function (project) {
		var payload = {};
		payload.name = project.subProjects[0].name;
		payload.url = project.subProjects[0].url;
		payload.environment = project.subProjects[0].environment;
		payload.browser = project.subProjects[0].browser;
		payload.parent = {};
		payload.parent.id = project.id;
		
		GSeleniumWebAppService.createSubProject(payload, function (isSuccessful, response) {
			if(isSuccessful) {
				getSubProjectList(project);
			}
		});
	}
	
	function getProjectList() {
		
		GSeleniumWebAppService.getProjectList(function (isSuccessful, response) {
			if(isSuccessful) {
				$scope.projects = {};
				projects = response;
				$scope.projects = angular.copy(response);
				$scope.projects.forEach(function (project) {
					project.editAndConfigureButtonStyle = {display: ""};
					project.cancelAndUpdateButtonStyle = {display: "none"};
					project.disabled = "disabled";
					project.environments = angular.copy($scope.environments);
					project.browsers = angular.copy($scope.browsers);
					project.showSubProjects = {display: 'none'};
				});
			}
		});
	}
	
	function getSubProjectList (project) {
		var payload = {id: project.id};
		GSeleniumWebAppService.getSubProjectList(payload, function (isSuccessful, response) {
			if(isSuccessful) {
				project.subProjects = [];
				var emptySubProject = {};
				emptySubProject.name = "";
				emptySubProject.url = "";
				emptySubProject.environments = angular.copy($scope.environments);
				emptySubProject.browsers = angular.copy($scope.browsers);
				emptySubProject.environment = emptySubProject.environments[0];
				emptySubProject.browser = emptySubProject.browsers[0];
				emptySubProject.editAndConfigureButtonStyle = {display: 'none'};
				emptySubProject.cancelAndUpdateButtonStyle = {display: 'none'};
				emptySubProject.createSubProjectButtonStyle = {display: ''};
				project.subProjects.push(emptySubProject);
				if(response.length > 0) {
					response.forEach(function (subProject) {
						subProject.editAndConfigureButtonStyle = {display: ""};
						subProject.cancelAndUpdateButtonStyle = {display: "none"};
						subProject.createSubProjectButtonStyle = {display: 'none'};
						subProject.disabled = "disabled";
						subProject.environments = angular.copy($scope.environments);
						subProject.browsers = angular.copy($scope.browsers);
						project.subProjects.push(subProject);
					});
				}
			}
		});
	}
	
	$scope.init();
});
