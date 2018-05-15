gSeleniumWebApp.service('GSeleniumWebAppService', function($http, $location) {
	
	var url = window.location.origin+"/"+window.location.pathname.substring(1).substring(0, window.location.pathname.substring(1).indexOf('/'));

	function sendRequest(request, callback) {

		$http(request).then(function(response) {
			if (response.status === 401) {
				callback(false, {
					messageCode : 401,
					messageDescription : "Your session has timed out, please login again.",
				});
				$location.path("/login");
			} else if (response.status === 205) {
				callback(true, {
					messageCode : 205,
					messageDescription : "You have been successfully logged out.",
				});
			} else if (response.data == null || response.data == undefined || JSON.stringify(response.data) === "{}") {
				callback(false, {
					messageCode : 9988,
					messageDescription : "No response from server",
				});
			} else {
				callback(true, response.data);
			}
		}, function(response) {
			if (response.status === 401) {
				callback(false, {
					messageCode : 401,
					messageDescription : "Session expired. Please login again.",
				});
				$location.path("/login");
			} else {
				callback(false, {
					messageCode : 9998,
					messageDescription : "The server encountered an error. Please try again later.",
				});
			}
		});
	}

	this.create = function(payload, callback) {

		var request = {
			method : "POST",
			url : url+"/rest/workflow/create",
			data : payload
		};

		sendRequest(request, callback);
	}
	
	this.getProjectList = function (callback) {
		
		var request = {
			method : "GET",
			url : url+"/rest/project/list"
		};
		
		sendRequest(request, callback);
	}
	
	this.createProject = function (payload, callback) {

		var request = {
			method : "POST",
			url : url+"/rest/project/create",
			data : payload
		};

		sendRequest(request, callback);
	}
	
	this.updateProject = function (payload, callback) {

		var request = {
			method : "POST",
			url : url+"/rest/project/update",
			data : payload
		};

		sendRequest(request, callback);
	}
	
	this.getWorkflowList = function (payload, callback) {
		
		var request = {
			method : "POST",
			url : url+"/rest/workflow/list",
			data : payload
		};

		sendRequest(request, callback);
	}
	
	this.createSubProject = function (payload, callback) {

		var request = {
			method : "POST",
			url : url+"/rest/project/child/create",
			data : payload
		};

		sendRequest(request, callback);
	}
	
	this.getSubProjectList = function (payload, callback) {
		
		var request = {
			method : "POST",
			url : url+"/rest/project/child/list",
			data : payload
		};
		
		sendRequest(request, callback);
	}

});
