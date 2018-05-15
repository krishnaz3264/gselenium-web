var gSeleniumWebApp = angular.module('gSeleniumWebApp', [ 'ngRoute', 'ui.bootstrap' ]);

gSeleniumWebApp.config(function($routeProvider, $locationProvider) {

	$locationProvider.hashPrefix("");

	$routeProvider.when('/workflow', {
		templateUrl : 'workflow.html',
		controller : "WorkflowController"
	}).when('/project', {
		templateUrl : 'project.html',
		controller : "ProjectController"
	}).when('/dashboard', {
		templateUrl : 'dashboard.html'
	}).otherwise({
		redirectTo : "dashboard"
	});
});
