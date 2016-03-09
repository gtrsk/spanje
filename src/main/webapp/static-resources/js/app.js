(
angular.module('abcapp', ['ngRoute'])
.config(function($locationProvider, $routeProvider) {
	$locationProvider.html5Mode(true);
	$routeProvider.when("/strange", {
		templateUrl : "static-resources/strange.html"
	});
	
	$routeProvider.when("/login", {
		templateUrl : "static-resources/login.html"
	});
	
	$routeProvider.when("/home", {
		templateUrl : "static-resources/generalhome.html"
	});
	
	
	$routeProvider.when("/about", {
		templateUrl : "static-resources/about.html"
	});
	
	
}))();