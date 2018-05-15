gSeleniumWebApp.controller("WorkflowController", function($scope, $location,
		$modal, $window, GSeleniumWebAppService) {

	var actionSequenceTable = document.getElementById("actionSequence");
	var selectors = [ "ID", "Class", "XPath" ];
	var actions = [ "Send Keys", "Click" ];

	$scope.workflow = {};
	$scope.workflow.steps = [];

	$scope.addNewStep = function() {

		var step = {};
		step.selectors = angular.copy(selectors);
		step.actions = angular.copy(actions);
		step.selector = step.selectors[0];
		step.action = step.actions[0];
		$scope.workflow.steps.push(step);
	}
	
	$scope.create = function () {
		
		var payload = {};
		payload.project = $location.search();
		payload.name = $scope.workflow.name;
		payload.path = $scope.workflow.path;
		payload.isIgnorable = $scope.workflow.isIgnorable;
		payload.isPositive = $scope.workflow.isPositive;
		payload.steps = [];
		
		for(var i in $scope.workflow.steps) {
			var step = {};
			step.selector = $scope.workflow.steps[i].selector;
			step.name = $scope.workflow.steps[i].name;
			step.action = $scope.workflow.steps[i].action;
			step.value = $scope.workflow.steps[i].value;
			step.delay = $scope.workflow.steps[i].delay;
			payload.steps.push(step);
		}
		console.log(payload);
		GSeleniumWebAppService.create(payload, function (isSuccessful, response) {
			console.log(response);
		});
		
	}
	
	$scope.onValueFocus = function () {
		event.target.style.height = "64px";
	}
	
	$scope.onValueFocusLost = function () {
		event.target.style.height = "32px";
	}
	
	function list() {
		
		GSeleniumWebAppService.getWorkflowList($location.search(), function (isSuccessful, response) {
			if(isSuccessful) {
				console.log(1233);
			}
		});
	}
	
	$scope.addNewStep();

	list();
});
